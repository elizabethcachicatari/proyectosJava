package pe.escuelaconductores.app_servicios.controller;

import static java.util.Objects.isNull;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.escuelaconductores.app_servicios.dto.EntidadDTO;
import pe.escuelaconductores.app_servicios.dto.EntidadUbigeoDTO;
import pe.escuelaconductores.app_servicios.entity.EntidadEntity;
import pe.escuelaconductores.app_servicios.services.EntidadService;
import pe.escuelaconductores.app_servicios.services.ServiceException;
import pe.escuelaconductores.app_servicios.util.Authz;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/entidades")
public class EntidadController {
	
	private Map<String, String> map = new HashMap<>();
	private final String MSG_INTERNAL_ERROR = "Se ha producido un error interno"; //500
	private final String MSG_BAD_REQUEST 	= "Operación no valida"; //400
	private final String MSG_NOT_FOUND 	= "No se encontraron registros"; //404
	
	private final EntidadService entidadService;
	private final Authz authz;

	public EntidadController(EntidadService entidadService, Authz authz) {
		this.entidadService = entidadService;
		this.authz=authz;
	}
	
	
	
	@GetMapping("/all")
	public ResponseEntity<?> ListAll() {

		try {
			List<EntidadEntity> listEntidadEntity = this.entidadService.findByNombre("");
			if (listEntidadEntity.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(listEntidadEntity);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	
	/* ---------------------------------HATEOAS--------------------------------*/	
	@GetMapping("/listAllHateoas")
	public ResponseEntity<?> findAll() {
	    try {
	        List<EntidadEntity> listEntidadEntity = this.entidadService.findByNombre("");

	        if (listEntidadEntity.isEmpty()) {
	            return ResponseEntity.noContent().build();
	        } else {
	            List<EntityModel<EntidadEntity>> entidadModels = listEntidadEntity.stream()
	                .map(entidad -> {
	                    EntityModel<EntidadEntity> model = EntityModel.of(entidad);

	                    model.add(linkTo(methodOn(EntidadController.class)
	                        .findById(entidad.getIdEntidad())).withSelfRel().withType("GET"));

	                    model.add(linkTo(methodOn(EntidadController.class)
	                        .update(entidad.getIdEntidad(), null)).withRel("update").withType("PUT"));

	                    model.add(linkTo(methodOn(EntidadController.class)
	                        .delete(entidad.getIdEntidad())).withRel("delete").withType("DELETE"));

	                    model.add(linkTo(methodOn(EntidadController.class)
	                        .findAll()).withRel("entidades").withType("GET"));

	                    return model;
	                })
	                .collect(Collectors.toList());

	            CollectionModel<EntityModel<EntidadEntity>> collectionModel = CollectionModel.of(entidadModels,
	                linkTo(methodOn(EntidadController.class).findAll()).withSelfRel().withType("GET"));

	            return ResponseEntity.ok(collectionModel);
	        }
	    } catch (Exception e) {
	        map.put("error", MSG_INTERNAL_ERROR);
	        return ResponseEntity.internalServerError().body(map);
	    }
	}

 /*
	@GetMapping ("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {

		try {
			Optional<EntidadEntity> optEntidadEntity = this.entidadService.findById(id);
			if (optEntidadEntity.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(optEntidadEntity);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
		
	}
*/
 	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
	    try {
	        Optional<EntidadEntity> optEntidadEntity = this.entidadService.findById(id);

	        if (optEntidadEntity.isEmpty()) {
	            return ResponseEntity.noContent().build();
	        } else {
	            EntidadEntity entidad = optEntidadEntity.get();

	            EntityModel<EntidadEntity> model = EntityModel.of(entidad,                
	                linkTo(methodOn(EntidadController.class).update(id, null)).withRel("update").withType("PUT"),
	                linkTo(methodOn(EntidadController.class).delete(id)).withRel("delete").withType("DELETE"),
	                linkTo(methodOn(EntidadController.class).findAll()).withRel("entidades").withType("GET")
	            );

	            return ResponseEntity.ok(model);
	        }
	    } catch (Exception e) {
	        map.put("error", MSG_INTERNAL_ERROR);
	        return ResponseEntity.internalServerError().body(map);
	    }
	}
		
	@GetMapping("findBySecurity/{id}")
	public ResponseEntity<?> findByIdAuthz(@PathVariable("id") Long id, @RequestHeader("authorization") String authorization) {
		if (!authz.validate(authorization)) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
	    try {
	        Optional<EntidadEntity> optEntidadEntity = this.entidadService.findById(id);

	        if (optEntidadEntity.isEmpty()) {
	            return ResponseEntity.noContent().build();
	        } else {
	            EntidadEntity entidad = optEntidadEntity.get();

	            EntityModel<EntidadEntity> model = EntityModel.of(entidad,                
	                linkTo(methodOn(EntidadController.class).update(id, null)).withRel("update").withType("PUT"),
	                linkTo(methodOn(EntidadController.class).delete(id)).withRel("delete").withType("DELETE"),
	                linkTo(methodOn(EntidadController.class).findAll()).withRel("entidades").withType("GET")
	            );

	            return ResponseEntity.ok(model);
	        }
	    } catch (Exception e) {
	        map.put("error", MSG_INTERNAL_ERROR);
	        return ResponseEntity.internalServerError().body(map);
	    }
	}
	
	@GetMapping("/findByName")
	public ResponseEntity<?> findByNombre(@RequestParam String nombre){
		try {
			List<EntidadEntity> listEntidadEntity = this.entidadService.findByNombre(nombre);
			if (listEntidadEntity.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(listEntidadEntity);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	@GetMapping("finByRuc/{ruc}")
	public ResponseEntity<?> findByRuc(@PathVariable ("ruc") String ruc){
		try {
			Optional<String> optEntidadEntity = this.entidadService.findByRuc(ruc);
			 
			if (optEntidadEntity.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				System.out.println("RUC registrado para:"+ optEntidadEntity);
				return ResponseEntity.ok(optEntidadEntity);
			}
		} catch (Exception e) {			
			map.put("Error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	// --------------------------------------Insertar y validaciones-------------------------------------------------------

	private boolean campoInvalido(String campo) {
	    return campo == null || campo.isBlank();
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody EntidadEntity entidadEntity){

		try {
			
			//Validaciones
			if (entidadEntity == null ||
				campoInvalido(entidadEntity.getNombre()) ||
				campoInvalido(entidadEntity.getRuc())||
				campoInvalido(entidadEntity.getDireccion())) {

				    map.put("Alerta","Debe ingresar los campos obligatorios: Nombre, RUC y Dirección.");
				    return ResponseEntity.badRequest().body(map);
				}

                 
            if (!entidadEntity.getRuc().matches("\\d{11}")) {
                    map.put("Mensaje: ", "El RUC ingresado debe tener 11 dígitos.");
                    return ResponseEntity.badRequest().body(map);
            }
                
            Optional<String> rucExiste = entidadService.findByRuc(entidadEntity.getRuc());
            if (rucExiste.isPresent()) {
                    map.put("Mensaje: ", "El RUC ingresado ya se encuentra registrado como : " + rucExiste.get());
                    return ResponseEntity.badRequest().body(map);
            }
			
			EntidadEntity oEntidadEntity = this.entidadService.save(entidadEntity);
			if (isNull(oEntidadEntity)) {
				map.put("alerta", MSG_BAD_REQUEST);
				return ResponseEntity.badRequest().body(map);
			} else {
				return new ResponseEntity<>(oEntidadEntity,HttpStatus.CREATED);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable  Long id, @RequestBody EntidadEntity entidadEntity){

		try {
			EntidadEntity oEntidadEntity = this.entidadService.update(id, entidadEntity);

			if (isNull(oEntidadEntity)) {
				map.put("alerta", MSG_BAD_REQUEST);
				return ResponseEntity.badRequest().body(map);
			} else {
				return ResponseEntity.ok(oEntidadEntity);
			}

		} catch (ServiceException se) {
			map.put("error", se.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);

		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
		
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> updateContacto(@PathVariable  Long id, @RequestBody EntidadEntity entidadEntity){

		try {
			EntidadEntity oEntidadEntity = this.entidadService.updateContacto(id,entidadEntity.getDireccion(),entidadEntity.getCorreo(),entidadEntity.getTelefono() );
			if (isNull(oEntidadEntity)) {
				map.put("Alerta", MSG_BAD_REQUEST);
				return ResponseEntity.badRequest().body(map);
			} else {
				return ResponseEntity.ok(oEntidadEntity);
			}
		}  catch (ServiceException e) {	 
			map.put("Error", e.getMessage());
			return ResponseEntity.badRequest().body(map);
		} catch (Exception e) {
			 
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable  Long id){

		try {
			this.entidadService.delete(id);
			return ResponseEntity.ok().build();
		} catch (ServiceException e) {
			map.put("error", e.getMessage());
			return ResponseEntity.badRequest().body(map);
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	// --------------------------------------Paginación y ordenamiento-------------------------------------------------------
	
	@GetMapping("/listAllOrderPagination")
	public ResponseEntity<?> getAllPagination(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size,
			@RequestParam(value = "field", defaultValue = "idEntidad") String field,
			@RequestParam(value = "order", defaultValue = "ASC") String order) {
		
		Map<String, String> resp = new HashMap<String, String>();
		try {
			
	        Direction direction;
	        try {
	            direction = Direction.valueOf(order.toUpperCase());
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.badRequest().body(Map.of("error", "El valor del parámetro 'order' debe ser 'ASC' o 'DESC'"));
	        }
	        
			PageRequest pageable = PageRequest.of((page - 1), size, Direction.valueOf(order.toUpperCase()), field);
			
			Page<EntidadEntity> listEntidadEntity = entidadService.findAllPagination(pageable);
			
			if (listEntidadEntity.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(listEntidadEntity);
			}
		} catch (Exception e) {
			resp.put("error", "Error al listar productos paginados");

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
		}
	}
	
	@GetMapping("/findUbigeoOrderPagination")
	public ResponseEntity<?> findByUbigeoPagination(
			@RequestParam String departamento,
		    @RequestParam String provincia,
		    @RequestParam String distrito,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size,
			@RequestParam(value = "field", defaultValue = "idEntidad") String field,
			@RequestParam(value = "order", defaultValue = "ASC") String order) {
		
		Map<String, String> resp = new HashMap<String, String>();
		try {
			
	        Direction direction;
	        try {
	            direction = Direction.valueOf(order.toUpperCase());
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.badRequest().body(Map.of("Error", "El valor del parámetro 'order' debe ser 'ASC' o 'DESC'"));
	        }
	        
			PageRequest pageable = PageRequest.of((page - 1), size, Direction.valueOf(order.toUpperCase()), field);					 
			Page<EntidadUbigeoDTO> listEntidadEntity = entidadService.findByUbigeoPagination(departamento, provincia, distrito, pageable);
 
			if (listEntidadEntity.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(listEntidadEntity);
			}
		} catch (Exception e) {
			resp.put("Error", "Error al listar entidades paginados");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
		}
	}
	
	@GetMapping("/findNameOrderPagination")
	public ResponseEntity<?> findByNombreEstablecimento(
			@RequestParam String nombre,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size,
			@RequestParam(value = "field", defaultValue = "idEntidad") String field,
			@RequestParam(value = "order", defaultValue = "ASC") String order) {
		
		Map<String, String> resp = new HashMap<String, String>();
		try {
			
	        Direction direction;
	        try {
	            direction = Direction.valueOf(order.toUpperCase());
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.badRequest().body(Map.of("Error", "El valor del parámetro 'order' debe ser 'ASC' o 'DESC'"));
	        }
	        
			PageRequest pageable = PageRequest.of((page - 1), size, Direction.valueOf(order.toUpperCase()), field);					 
			Page<EntidadUbigeoDTO> listEntidadEntity = entidadService.findByNombreEstablecimento(nombre, pageable);
 
			if (listEntidadEntity.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(listEntidadEntity);
			}
		} catch (Exception e) {
			resp.put("Error", "Error al listar entidades paginados");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
		}
	}
	
	// --------------------------------------Store Procedure-------------------------------------------------------
	
    @PostMapping("/saveSP")
    public ResponseEntity<?> saveSP(@RequestBody EntidadDTO dto) {
    	 
    	try {             
			//Validaciones
			if (dto == null ||
				campoInvalido(dto.getNombre()) ||
				campoInvalido(dto.getRuc())||
				campoInvalido(dto.getDireccion())) {
				Map<String, Object> map = new HashMap<>();
				    map.put("Alerta","Debe ingresar los campos obligatorios: Nombre, RUC y Dirección.");
				    return ResponseEntity.badRequest().body(map);
				}

                 
            if (!dto.getRuc().matches("\\d{11}")) {
            	Map<String, Object> map = new HashMap<>();
                    map.put("Mensaje: ", "El RUC ingresado debe tener 11 dígitos.");
                    return ResponseEntity.badRequest().body(map);
            }
                
            Optional<String> rucExiste = entidadService.findByRuc(dto.getRuc());
            if (rucExiste.isPresent()) {
            	Map<String, Object> map = new HashMap<>();
                    map.put("Mensaje: ", "El RUC ingresado ya se encuentra registrado como : " + rucExiste.get());
                    return ResponseEntity.badRequest().body(map);
            }
            
            Map<String, Object> map = new HashMap<>();
	        entidadService.saveSP(
	        		dto.getRuc(),
	        		dto.getNombre(),
	        		dto.getDireccion(), 
	        		dto.getCorreo(),
	        		dto.getTelefono(), 
	        		dto.getIdUbigeo(), 
	        		dto.getIdTipoEntidad(), 
	        		dto.getIdEstadoEntidad()
	        		);
	        map.put("Mensaje", "Entidad insertada correctamente");
	        return new ResponseEntity<>(map, HttpStatus.CREATED);
	        
    	} catch (Exception e) {
    			//e.printStackTrace();
    			map.put("error", MSG_INTERNAL_ERROR);
    	        return ResponseEntity.internalServerError().body(map);
    	  } 
    }
    
	
	@GetMapping("/findByUbigeoSP")
	public ResponseEntity<?> buscarSP(
			@RequestParam String departamento,
			@RequestParam String provincia,
			@RequestParam String distrito, 
			@RequestParam Long tipoEntidad ){

		try {
			List<EntidadUbigeoDTO> entidades = entidadService.buscarSP(departamento, provincia, distrito, tipoEntidad);
			
			if (entidades.isEmpty()) {
				map.put("Alerta", MSG_NOT_FOUND);				 
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
			} else {
				return ResponseEntity.ok(entidades);
			}
		}  catch (ServiceException e) {	 
			map.put("Error", e.getMessage());
			return ResponseEntity.badRequest().body(map);
		} catch (Exception e) {
			 
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	@GetMapping("/findByNameSP")
	public ResponseEntity<?> buscarNombreSP(@RequestParam String nombre, @RequestParam Long tipoEntidad){

		try {
			List<EntidadUbigeoDTO> entidades = entidadService.buscarNombreSP(nombre, tipoEntidad);
			
			if (entidades.isEmpty()) {
				map.put("Alerta", MSG_NOT_FOUND);				 
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
			} else {
				return ResponseEntity.ok(entidades);
			}
		}  catch (ServiceException e) {	 
			map.put("Error", e.getMessage());
			return ResponseEntity.badRequest().body(map);
		} catch (Exception e) {
			 
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	
	
}
