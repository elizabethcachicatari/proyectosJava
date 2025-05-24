package pe.escuelaconductores.app_servicios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.escuelaconductores.app_servicios.dto.DepartamentoDTO;
import pe.escuelaconductores.app_servicios.dto.DistritoDTO;
import pe.escuelaconductores.app_servicios.dto.ProvinciaDTO;
import pe.escuelaconductores.app_servicios.entity.UbigeoEntity;

@Repository    
public interface UbigeoRepository extends JpaRepository<UbigeoEntity, Long>{
	   
	//Obtiene la lista de los ubigeos activos Estado=1
	@Query("SELECT u FROM UbigeoEntity u WHERE u.estado = :estado") 
	List<UbigeoEntity> listUbigeos(@Param("estado") String estado);
 
	@Query("SELECT DISTINCT new pe.escuelaconductores.app_servicios.dto.DepartamentoDTO(u.codDepartamento, u.nomDepartamento)"+
	    		"FROM UbigeoEntity u WHERE u.estado = '1' ORDER BY u.nomDepartamento")  
	List<DepartamentoDTO> obtenerDepartamentos();

	 @Query("SELECT DISTINCT new pe.escuelaconductores.app_servicios.dto.ProvinciaDTO(u.codProvincia, u.nomProvincia) " +
	    	       "FROM UbigeoEntity u WHERE u.nomDepartamento = :departamento AND u.estado = '1' ORDER BY u.nomProvincia")
	 List<ProvinciaDTO> obtenerProvinciasPorDepartamento(@Param("departamento") String departamento);

	 @Query("SELECT DISTINCT new pe.escuelaconductores.app_servicios.dto.DistritoDTO(u.idUbigeo, u.codDistrito, u.nomDistrito) " +
	           "FROM UbigeoEntity u WHERE u.nomDepartamento = :departamento AND u.nomProvincia = :provincia AND u.estado = '1' ORDER BY u.nomDistrito")
	 List<DistritoDTO> obtenerDistritosPorProvincia(@Param("departamento") String departamento, @Param("provincia") String provincia);	   
	   
	 @Query("SELECT u.idUbigeo FROM UbigeoEntity u WHERE u.nomDepartamento = :departamento AND u.nomProvincia = :provincia AND u.nomDistrito = :distrito")
	 Long ObtenerIdUbigeo(@Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito);
	

}
