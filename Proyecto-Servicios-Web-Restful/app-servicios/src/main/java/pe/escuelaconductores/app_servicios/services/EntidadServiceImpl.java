package pe.escuelaconductores.app_servicios.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.ParameterMode;

import pe.escuelaconductores.app_servicios.entity.UbigeoEntity;
import pe.escuelaconductores.app_servicios.entity.EntidadEntity;
import pe.escuelaconductores.app_servicios.entity.EstadoEntidadEntity;
import pe.escuelaconductores.app_servicios.entity.TipoEntidadEntity;

import pe.escuelaconductores.app_servicios.repository.EntidadRepository;
import pe.escuelaconductores.app_servicios.repository.UbigeoRepository;
import pe.escuelaconductores.app_servicios.repository.EstadoEntidadRepository;
import pe.escuelaconductores.app_servicios.repository.TipoEntidadRepository;

import pe.escuelaconductores.app_servicios.dto.EntidadUbigeoDTO;

@Service
@Transactional
public class EntidadServiceImpl implements EntidadService {

	private final EntidadRepository entidadRepository;
	private final UbigeoRepository ubigeoRepository;
	private final TipoEntidadRepository tipoEntidadRepository;
	private final EstadoEntidadRepository estadoEntidadRepository;
	
	@PersistenceContext
	private EntityManager entityManager; // Para la lectura manual de las filas del Object[]
	
	//Constructor de la clase EntidadServiceImpl
	public EntidadServiceImpl(EntidadRepository entidadRepository, UbigeoRepository ubigeoRepository, EstadoEntidadRepository estadoEntidadRepository,TipoEntidadRepository tipoEntidadRepository) {
		this.entidadRepository = entidadRepository;
		this.ubigeoRepository = ubigeoRepository;
		this.tipoEntidadRepository = tipoEntidadRepository;
		this.estadoEntidadRepository = estadoEntidadRepository;		
	}
 
	@Override
	public List<EntidadEntity> findAll() throws ServiceException {
		try {
			return this.entidadRepository.findByNombre("");
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<EntidadEntity> findById(Long id) throws ServiceException {
		try {

			return this.entidadRepository.findById(id);
		} catch (Exception e) {
	        throw new ServiceException("Error al encontrar la entidad con id: " + id, e);   
	    }
	}

	@Override
	public List<EntidadEntity> findByNombre(String nombre) throws ServiceException {
		try {
			String val = (nombre == null) ? "" : nombre.trim();
			return this.entidadRepository.findByNombre("%" + val + "%");
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// --------------------------------------Insertar y validación RUC-------------------------------------------------------
	@Override
	public Optional<String> findByRuc(String ruc) throws ServiceException {
		try { 
			return entidadRepository.findByRuc(ruc);
		} catch (Exception e) {
		//	e.printStackTrace(); 
			throw new ServiceException(e);
		}
	}

	@Override
	public EntidadEntity save(EntidadEntity entidadEntity) throws ServiceException {
		try {
			entidadEntity.setEstado("1");
			entidadEntity.setCreateDate(LocalDateTime.now());
			entidadEntity.setUserCreate("Admin");
			return this.entidadRepository.save(entidadEntity);
			} catch (Exception e) {
				throw new ServiceException(e);
		}
	}


	@Override
	public EntidadEntity update(Long id, EntidadEntity entidadEntity) throws ServiceException {
		try {

			Optional<EntidadEntity> optEntidadEntity = entidadRepository.findById(id);

			if (optEntidadEntity.isPresent()) {
				
				//System.out.println("Ingresa al optional: "+id);
				EntidadEntity oEntidadEntity = optEntidadEntity.get(); // Extrae el optional y lo guarda en oEntidadEntity
			
	            Long idUbigeo = entidadEntity.getIdUbigeo().getIdUbigeo();
	            UbigeoEntity ubigeo = ubigeoRepository.findById(idUbigeo)
	                .orElseThrow(() -> new ServiceException("Ubigeo con ID " + idUbigeo + " no existe"));

	            Long idTipoEntidad = entidadEntity.getIdTipoEntidad().getIdTipoEntidad();
	            TipoEntidadEntity tipoEntidad = tipoEntidadRepository.findById(idTipoEntidad)
	                .orElseThrow(() -> new ServiceException("Tipo de establecimiento con ID " + idTipoEntidad + " no existe"));

	            Long idEstadoEntidad = entidadEntity.getIdEstadoEntidad().getIdEstadoEntidad();
	            EstadoEntidadEntity estadoEntidad = estadoEntidadRepository.findById(idEstadoEntidad)
	                .orElseThrow(() -> new ServiceException("Estado de establecimiento con ID " + idEstadoEntidad + " no existe"));	            
  
				//Copia los datos del nuevo objeto entidadEntity en oEntidadEntity
				//oEntidadEntity.setIdEntidad(id);
				oEntidadEntity.setRuc(entidadEntity.getRuc());
				oEntidadEntity.setNombre(entidadEntity.getNombre());
				oEntidadEntity.setDireccion(entidadEntity.getDireccion());
				oEntidadEntity.setCorreo(entidadEntity.getCorreo());
				oEntidadEntity.setTelefono(entidadEntity.getTelefono());
				oEntidadEntity.setIdUbigeo(ubigeo);
				oEntidadEntity.setIdTipoEntidad(tipoEntidad);
				oEntidadEntity.setIdEstadoEntidad(estadoEntidad);				
				oEntidadEntity.setUpdateDate(LocalDateTime.now());
				oEntidadEntity.setUserUpdate("Admin");
				
				//Guarda la entidad actualizada.
				return this.entidadRepository.save(oEntidadEntity);
			}
			else {
	            	throw new ServiceException("Entidad con ID " + id + " no encontrada");
				}
			 
		} catch (Exception e) {
			throw new ServiceException(e);
			}
	}

	@Override
	public EntidadEntity updateContacto(Long id, String direccion, String correo, String telefono) throws ServiceException {
		try {

			Optional<EntidadEntity> optEntidadEntity = entidadRepository.findById(id);

			if (optEntidadEntity.isEmpty()) {
				throw new ServiceException(String.format("No existe el establecimiento con el ID: %d", id));
			}
			EntidadEntity oEntidadEntity = optEntidadEntity.get();
			oEntidadEntity.setDireccion(direccion);
			oEntidadEntity.setCorreo(correo);
			oEntidadEntity.setTelefono(telefono);
			
			oEntidadEntity.setUpdateDate(LocalDateTime.now());
			oEntidadEntity.setUserUpdate("Admin");
			
			return this.entidadRepository.save(oEntidadEntity);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	@Override
	public void delete(Long id) throws ServiceException {
		try {

			Optional<EntidadEntity> optEntidadEntity = entidadRepository.findById(id);

			if (optEntidadEntity.isEmpty()) {
				throw new ServiceException(String.format("No existe la entidad con el id %s", id));
			}
			entidadRepository.updateEstado(id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	
	// --------------------------------------Paginación y ordenamiento-------------------------------------------------------
	
		@Override
		public Page<EntidadEntity> findAllPagination(Pageable pageable) throws ServiceException{
		    try {
		    	return entidadRepository.findAllPagination(pageable);
		    
		    } catch (Exception e) {
		        throw new ServiceException("Error al listar con paginación", e);
		    }
		}

		@Override
		public Page<EntidadUbigeoDTO> findByUbigeoPagination(String departamento, String provincia, String distrito, Pageable pageable) throws ServiceException {
			 
		    try {
		        return entidadRepository.findByUbigeoPagination(departamento, provincia, distrito, pageable);
		    } catch (Exception e) {
		        throw new ServiceException("Error al buscar entidades por ubigeo con paginación", e);
		    }
	 
		}
	 
		@Override
		public Page<EntidadUbigeoDTO> findByNombreEstablecimento(String nombre, Pageable pageable) throws ServiceException {
			 
		    try {
		        return entidadRepository.findByNombreEstablecimento(nombre, pageable);
		    } catch (Exception e) {
		        throw new ServiceException("Error al buscar entidades por nombre con paginación", e);
		    }
	 
		}

		

	// --------------------------------------Procedimientos almacenados-------------------------------------------------------
	
		@Override
		public void saveSP(String ruc, String nombre, String direccion, String correo,String telefono, Long idUbigeo, Long idTipoEntidad, Long idEstadoEntidad) throws ServiceException {
		    try {
				entidadRepository.insertarEntidad(ruc,nombre,direccion,correo,telefono,idUbigeo,idTipoEntidad,idEstadoEntidad);
		    } catch (Exception e) {
		    	e.printStackTrace();
		        throw new ServiceException(e);
		    }
		}

		
	@Override
	public List<EntidadUbigeoDTO> buscarSP(String departamento, String provincia, String distrito, Long tipoEntidad) throws ServiceException {
	    try {
	        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("PKG_ENTIDAD.BUSCAR_ENTIDAD_UBIGEO");

	        query.registerStoredProcedureParameter("p_departamento", String.class, ParameterMode.IN);
	        query.registerStoredProcedureParameter("p_provincia", String.class, ParameterMode.IN);
	        query.registerStoredProcedureParameter("p_distrito", String.class, ParameterMode.IN);
	        query.registerStoredProcedureParameter("p_tipoentidad", Long.class, ParameterMode.IN);
	        query.registerStoredProcedureParameter("p_resultado", void.class, ParameterMode.REF_CURSOR);

	        query.setParameter("p_departamento", departamento);
	        query.setParameter("p_provincia", provincia);
	        query.setParameter("p_distrito", distrito);
	        query.setParameter("p_tipoentidad", tipoEntidad);

	         System.out.println("Ejecutando SP con: " + departamento + "/" + provincia + "/" + distrito + " tipo:" + tipoEntidad);
	        
	        query.execute();

	        
	        List<Object[]> resultados = query.getResultList();
	        List<EntidadUbigeoDTO> entidades = new ArrayList<>();

	        for (Object[] row : resultados) {
	        	
	            EntidadUbigeoDTO dto = new EntidadUbigeoDTO();
	            
	            dto.setIdEntidad(((Number) row[0]).longValue());
	            dto.setRuc((String) row[1]);
	            dto.setNombre((String) row[2]);
	            dto.setDireccion((String) row[3]);
	            dto.setCorreo((String) row[4]);
	            dto.setTelefono((String) row[5]);

	            dto.setDepartamento((String) row[6]);
	            dto.setProvincia((String) row[7]);
	            dto.setDistrito((String) row[8]);

	            dto.setTipoEntidad((String) row[9]);
	            dto.setEstadoEntidad((String) row[10]);

	            entidades.add(dto);
	        }

	        return entidades;

	    } catch (Exception e) {
	    	 e.printStackTrace();
	        throw new ServiceException("Error al buscar entidades por ubigeo", e);
	    }
	}
 


	@Override
	public List<EntidadUbigeoDTO> buscarNombreSP(String nombre, Long tipoEntidad) throws ServiceException {
	 
	    try {
	        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("PKG_ENTIDAD.BUSCAR_ENTIDAD_POR_NOMBRE");

	        query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
	        query.registerStoredProcedureParameter("p_tipoentidad", Long.class, ParameterMode.IN);
	        query.registerStoredProcedureParameter("p_resultado", void.class, ParameterMode.REF_CURSOR);

	        query.setParameter("p_nombre", nombre);
	        query.setParameter("p_tipoentidad", tipoEntidad);

	        System.out.println("Ejecutando SP con: " + nombre + " Tipo:" + tipoEntidad);
	        
	        query.execute();

	        
	        List<Object[]> resultados = query.getResultList();
	        List<EntidadUbigeoDTO> entidades = new ArrayList<>();

	        for (Object[] row : resultados) {
	        	
	            EntidadUbigeoDTO dto = new EntidadUbigeoDTO();
	            
	            dto.setIdEntidad(((Number) row[0]).longValue());
	            dto.setRuc((String) row[1]);
	            dto.setNombre((String) row[2]);
	            dto.setDireccion((String) row[3]);
	            dto.setCorreo((String) row[4]);
	            dto.setTelefono((String) row[5]);

	            dto.setDepartamento((String) row[6]);
	            dto.setProvincia((String) row[7]);
	            dto.setDistrito((String) row[8]);

	            dto.setTipoEntidad((String) row[9]);
	            dto.setEstadoEntidad((String) row[10]);

	            entidades.add(dto);
	        }

	        return entidades;

	    } catch (Exception e) {
	    	 e.printStackTrace();
	        throw new ServiceException("Error al buscar entidades por Nombre", e);
	    }
	}


 

	

	
}
