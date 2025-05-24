package pe.escuelaconductores.app_servicios.services;

import java.util.List;

import pe.escuelaconductores.app_servicios.entity.TipoEntidadEntity;

public interface TipoEntidadService {
	List<TipoEntidadEntity> listAll() throws ServiceException;
}
