package pe.escuelaconductores.app_servicios.services;

import java.util.List;

import pe.escuelaconductores.app_servicios.dto.DepartamentoDTO;
import pe.escuelaconductores.app_servicios.dto.DistritoDTO;
import pe.escuelaconductores.app_servicios.dto.ProvinciaDTO;
import pe.escuelaconductores.app_servicios.dto.UbigeoDepartamentoDTO;
import pe.escuelaconductores.app_servicios.entity.UbigeoEntity;

public interface UbigeoService {

    List<UbigeoEntity> listado()  throws ServiceException;
    List<UbigeoDepartamentoDTO> listUbigeos() throws ServiceException;
    
    List<DepartamentoDTO> obtenerDepartamentos() throws ServiceException;
    List<ProvinciaDTO> obtenerProvinciasPorDepartamento(String departamento) throws ServiceException;
    List<DistritoDTO> obtenerDistritosPorProvincia(String departamento, String provincia) throws ServiceException;
     
    Long ObtenerIdUbigeo(String departamento, String provincia, String distrito) throws ServiceException;
}
