package pe.escuelaconductores.app_servicios.services;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.escuelaconductores.app_servicios.entity.EstadoEntidadEntity;
import pe.escuelaconductores.app_servicios.repository.EstadoEntidadRepository;

@Service
public class EstadoEntidadServiceImpl implements EstadoEntidadService{

	private final EstadoEntidadRepository estadoEntidadRepository;

    public EstadoEntidadServiceImpl(EstadoEntidadRepository estadoEntidadRepository) {
    	this.estadoEntidadRepository = estadoEntidadRepository;
    	}
    
	@Override
	public List<EstadoEntidadEntity> listAll()throws ServiceException {
		try {
			return this.estadoEntidadRepository.listAll();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

 

}
