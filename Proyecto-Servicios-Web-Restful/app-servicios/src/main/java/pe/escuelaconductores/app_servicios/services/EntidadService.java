package pe.escuelaconductores.app_servicios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.escuelaconductores.app_servicios.dto.EntidadUbigeoDTO;
import pe.escuelaconductores.app_servicios.entity.EntidadEntity;

public interface EntidadService {

	Optional<EntidadEntity> findById(Long id) throws ServiceException;
	List<EntidadEntity> findAll() throws ServiceException;	
	
	List<EntidadEntity> findByNombre(String nombre) throws ServiceException;
	
	EntidadEntity save(EntidadEntity entidadEntity) throws ServiceException;	
	Optional<String> findByRuc(String ruc) throws ServiceException;// Para validar que el RUC no exista
	void saveSP(String ruc, 
			String nombre, 
			String direccion, 
			String correo, 
			String telefono, 
			Long idUbigeo, 
			Long idTipoEntidad, 
			Long idEstadoEntidad)throws ServiceException;
	
	EntidadEntity update(Long id, EntidadEntity entidadEntity) throws ServiceException;
	EntidadEntity updateContacto(Long id, String direccion, String correo, String telefono) throws ServiceException;	
	
    
	List<EntidadUbigeoDTO> buscarSP(String departamento, String provincia, String distrito, Long tipoEntidad)throws ServiceException;    
    List<EntidadUbigeoDTO> buscarNombreSP(String nombre, Long tipoEntidad)throws ServiceException;
    
    void delete(Long id) throws ServiceException;
     
    //Paginación y ordenamiento
    Page<EntidadEntity> findAllPagination(Pageable pageable)throws ServiceException;
    Page<EntidadUbigeoDTO> findByUbigeoPagination(String departamento, String provincia, String distrito, Pageable pageable)throws ServiceException;
    Page<EntidadUbigeoDTO> findByNombreEstablecimento(String nombre, Pageable pageable)throws ServiceException;
 
}
