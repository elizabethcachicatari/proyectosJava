package pe.escuelaconductores.app_servicios.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import pe.escuelaconductores.app_servicios.entity.UsuarioEntity;
import pe.escuelaconductores.app_servicios.repository.UbigeoRepository;
import pe.escuelaconductores.app_servicios.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	private final UsuarioRepository usuarioRepository;
	
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
    	this.usuarioRepository = usuarioRepository;
    	}
    
	@Override
	public Optional<UsuarioEntity> findByUsuarioClave(String usuario, String clave) throws ServiceException {
		      
		try {
			return usuarioRepository.validar(usuario, clave);			 
		} catch (Exception e) {
			throw new ServiceException(e);
			}
	}
}
