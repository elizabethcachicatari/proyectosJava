package pe.escuelaconductores.app_servicios.controller;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.escuelaconductores.app_servicios.services.ServiceException;
import pe.escuelaconductores.app_servicios.services.UbigeoService;
import pe.escuelaconductores.app_servicios.dto.DepartamentoDTO;
import pe.escuelaconductores.app_servicios.dto.DistritoDTO;
import pe.escuelaconductores.app_servicios.dto.ProvinciaDTO;
import pe.escuelaconductores.app_servicios.dto.UbigeoDepartamentoDTO;
import pe.escuelaconductores.app_servicios.entity.UbigeoEntity;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ubigeos")
public class UbigeoController {
	
	private Map<String, String> map = new HashMap<>();
	private final String MSG_INTERNAL_ERROR = "Se ha producido un error interno";
	//private final String MSG_BAD_REQUEST 	= "Operación no valida";
	
	private final UbigeoService ubigeoService;

	public UbigeoController(UbigeoService ubigeoService) {this.ubigeoService = ubigeoService;}
	
	
	@GetMapping("/all")
	public ResponseEntity<?> listado() {

		try {
			List<UbigeoEntity> listUbigeoEntity = this.ubigeoService.listado();
			if (listUbigeoEntity.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(listUbigeoEntity);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
 
	//Ubigeos en estructura para consulta en combos
	@GetMapping("/lista")
    public ResponseEntity<?> listUbigeos() {
        try {
            List<UbigeoDepartamentoDTO> departamentos = ubigeoService.listUbigeos();
            return ResponseEntity.ok(Map.of("departamentos", departamentos));
           
        } catch (ServiceException e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
    
	@GetMapping("/departamentos")
	public ResponseEntity<?> obtenerDepartamentos() {

		try {
			List<DepartamentoDTO> listUbigeoEntity = this.ubigeoService.obtenerDepartamentos();
			if (listUbigeoEntity.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(listUbigeoEntity);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	@GetMapping("/provincias_departamento")
	public ResponseEntity<?> obtenerProvinciasPorDepartamento(@RequestParam String departamento) {

		try {
			List<ProvinciaDTO> listUbigeoEntity = this.ubigeoService.obtenerProvinciasPorDepartamento(departamento);
			if (listUbigeoEntity.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(listUbigeoEntity);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
 
	@GetMapping("/distritos_provincia")
	public ResponseEntity<?> obtenerDistritosPorProvincia(@RequestParam String departamento, @RequestParam String provincia) {
		try {
			List<DistritoDTO> listUbigeoEntity = ubigeoService.obtenerDistritosPorProvincia(departamento, provincia);
			if (listUbigeoEntity.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(listUbigeoEntity);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	@GetMapping("/findIdUbigeo")
	public ResponseEntity<?> ObtenerIdUbigeo(@RequestParam String departamento, @RequestParam String provincia, @RequestParam String distrito) {
		try {
			Long idUbigeo= this.ubigeoService.ObtenerIdUbigeo(departamento, provincia, distrito);
			if (idUbigeo==null) {
				return ResponseEntity.noContent().build();
			} else {
	            Map<String, Object> response = new HashMap<>();
	            response.put("idUbigeo", idUbigeo);
	            return ResponseEntity.ok(response);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
}
