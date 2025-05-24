package pe.escuelaconductores.app_servicios.services;

import java.util.Optional;

import pe.escuelaconductores.app_servicios.entity.UsuarioEntity;

public interface UsuarioService {

	 Optional<UsuarioEntity> findByUsuarioClave(String usuario, String clave) throws ServiceException;
}
