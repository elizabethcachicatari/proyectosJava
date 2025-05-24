package pe.escuelaconductores.app_servicios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.escuelaconductores.app_servicios.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{
    @Query(value = "select u from UsuarioEntity u where u.usuario =:usuario " +
            " and u.clave =:clave and u.estado='1'" ) // JPQL ( Java Persistence Query Language)
    Optional<UsuarioEntity> validar(@Param("usuario") String usuario, @Param("clave") String clave);
}
