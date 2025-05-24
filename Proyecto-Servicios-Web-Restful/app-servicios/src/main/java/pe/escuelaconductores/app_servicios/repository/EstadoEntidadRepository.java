package pe.escuelaconductores.app_servicios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.escuelaconductores.app_servicios.entity.EstadoEntidadEntity;

@Repository 
public interface EstadoEntidadRepository extends JpaRepository<EstadoEntidadEntity, Long> {

	@Query(value = "select p from EstadoEntidadEntity p where p.estado='1' order by p.idEstadoEntidad" ) // JPQL ( Java Persistence Query Language)
	List<EstadoEntidadEntity> listAll();
	
}
