package pe.escuelaconductores.app_servicios.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import pe.escuelaconductores.app_servicios.dto.EntidadUbigeoDTO;
import pe.escuelaconductores.app_servicios.entity.EntidadEntity;

@Repository 
public interface EntidadRepository extends JpaRepository<EntidadEntity, Long>{

	@Query(value = "select p from EntidadEntity p where upper(p.nombre) like upper(:nombre) and p.estado='1' order by p.idEntidad" ) // JPQL ( Java Persistence Query Language)
	List<EntidadEntity> findByNombre(@Param("nombre") String nombre);
	
	@Modifying
	@Query(value = "update entidad set estado='0' where identidad=:id", nativeQuery = true )  
	void updateEstado(@Param("id") Long id);
	
	@Query(value = ""
			+ "SELECT p.nombre "
			+ "FROM EntidadEntity p "
			+ "WHERE p.ruc=:ruc "
			+ "ORDER BY p.idEntidad" )  
	Optional<String> findByRuc(@PathVariable("ruc") String ruc);
	
	
    @Procedure(name = "Entidad.insertarEntidad")
    void insertarEntidad(
    		@Param("p_ruc") String ruc, 
    		@Param("p_nombre") String nombre, 
    		@Param("p_direccion") String direccion, 
    		@Param("p_correo") String correo, 
    		@Param("p_telefono") String telefono, 
    		@Param("p_idubigeo") Long idUbigeo, 
    		@Param("p_idtipoentidad") Long idTipoEntidad, 
    		@Param("p_idestadoentidad") Long idEstadoEntidad);
        
    @Query("""
    	    SELECT new pe.escuelaconductores.app_servicios.dto.EntidadUbigeoDTO(
				e.idEntidad,
				        e.ruc,
				        e.nombre,
				        e.direccion,
				        e.correo,
				        e.telefono,
				        u.nomDepartamento,
				        u.nomProvincia,
				        u.nomDistrito,
				        te.nombreTipoEntidad,
				        ee.nombreEstadoEntidad
				    )
				    FROM EntidadEntity e
				    JOIN e.idUbigeo u
				    JOIN e.idTipoEntidad te
				    JOIN e.idEstadoEntidad ee
				    WHERE UPPER(u.nomDepartamento) LIKE CONCAT('%', UPPER(:departamento), '%')
				      AND UPPER(u.nomProvincia) LIKE CONCAT('%', UPPER(:provincia), '%')
				      AND UPPER(u.nomDistrito) LIKE CONCAT('%', UPPER(:distrito), '%')
				""")
				Page<EntidadUbigeoDTO> findByUbigeoPagination(
				    @Param("departamento") String departamento,
				    @Param("provincia") String provincia,
				    @Param("distrito") String distrito,
				    Pageable pageable
				);
    
    @Query("""
    	    SELECT new pe.escuelaconductores.app_servicios.dto.EntidadUbigeoDTO(
				e.idEntidad,
				        e.ruc,
				        e.nombre,
				        e.direccion,
				        e.correo,
				        e.telefono,
				        u.nomDepartamento,
				        u.nomProvincia,
				        u.nomDistrito,
				        te.nombreTipoEntidad,
				        ee.nombreEstadoEntidad
				    )
				    FROM EntidadEntity e
				    JOIN e.idUbigeo u
				    JOIN e.idTipoEntidad te
				    JOIN e.idEstadoEntidad ee
				    WHERE UPPER(e.nombre) LIKE CONCAT('%', UPPER(:nombre), '%')
				""")
				Page<EntidadUbigeoDTO> findByNombreEstablecimento(
				    @Param("nombre") String nombre,
				    Pageable pageable
				);
    
    @Query("select p from EntidadEntity p where p.estado='1'")
	Page<EntidadEntity> findAllPagination(Pageable pageable);
    
    /*
    @Procedure(name = "Entidad.buscarEntidadPorUbigeo")
    List<EntidadEntity> buscarEntidadPorUbigeo(@Param("p_departamento") String departamento, @Param("p_provincia") String provincia, @Param("p_distrito") String distrito); 
    
    @Procedure(name = "Entidad.buscarEntidadPorNombre")
    List<EntidadEntity> buscarEntidadPorNombre(@Param("p_nombre") String nombre);
     */
    
}
