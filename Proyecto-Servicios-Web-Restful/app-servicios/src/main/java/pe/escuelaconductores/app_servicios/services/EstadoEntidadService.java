package pe.escuelaconductores.app_servicios.services;

import java.util.List;

import pe.escuelaconductores.app_servicios.entity.EstadoEntidadEntity;


public interface EstadoEntidadService {
	 List<EstadoEntidadEntity> listAll()  throws ServiceException;
}
