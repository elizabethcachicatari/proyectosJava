package pe.escuelaconductores.app_servicios.services;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.escuelaconductores.app_servicios.entity.TipoEntidadEntity;
import pe.escuelaconductores.app_servicios.repository.TipoEntidadRepository;

@Service
public class TipoEntidadServiceImpl implements TipoEntidadService {

	private final TipoEntidadRepository tipoEntidadRepository;

    public TipoEntidadServiceImpl(TipoEntidadRepository tipoEntidadRepository) {
    	this.tipoEntidadRepository = tipoEntidadRepository;
    	}
    
	@Override
	public List<TipoEntidadEntity> listAll() throws ServiceException {
		try {
			return this.tipoEntidadRepository.listAll();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
