package pe.escuelaconductores.app_servicios.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.escuelaconductores.app_servicios.entity.TipoEntidadEntity;

@Repository 
public interface TipoEntidadRepository extends JpaRepository<TipoEntidadEntity, Long> {

	@Query(value = "select p from TipoEntidadEntity p where p.estado='1' order by p.idTipoEntidad" ) // JPQL 
	List<TipoEntidadEntity> listAll();
}
