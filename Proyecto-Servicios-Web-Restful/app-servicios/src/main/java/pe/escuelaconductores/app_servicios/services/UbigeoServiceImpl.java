package pe.escuelaconductores.app_servicios.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import pe.escuelaconductores.app_servicios.dto.DepartamentoDTO;
import pe.escuelaconductores.app_servicios.dto.DistritoDTO;
import pe.escuelaconductores.app_servicios.dto.ProvinciaDTO;
import pe.escuelaconductores.app_servicios.dto.UbigeoDepartamentoDTO;
import pe.escuelaconductores.app_servicios.dto.UbigeoProvinciaDTO;
import pe.escuelaconductores.app_servicios.entity.UbigeoEntity;
import pe.escuelaconductores.app_servicios.repository.UbigeoRepository;

@Service
public class UbigeoServiceImpl implements UbigeoService{
	
	private final UbigeoRepository ubigeoRepository;

	//Crea el constructor de la clase UbigeoServiceImpl para inyectar una dependencia de tipo UbigeoRepository
    public UbigeoServiceImpl(UbigeoRepository ubigeoRepository) {
    	this.ubigeoRepository = ubigeoRepository;
    	}
    
	@Override
	public List<UbigeoEntity> listado() throws ServiceException {
		try {
			return this.ubigeoRepository.findAll();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	
    @Override
    public List<UbigeoDepartamentoDTO> listUbigeos() throws ServiceException {
    	 try {
		    	List<UbigeoEntity> lista = ubigeoRepository.listUbigeos("1");
		
		        Map<String, Map<String, List<String>>> estructura  = new LinkedHashMap<>();
		
		        for (UbigeoEntity ubigeo : lista) {
		        	estructura 
		                .computeIfAbsent(ubigeo.getNomDepartamento(), d -> new LinkedHashMap<>())
		                .computeIfAbsent(ubigeo.getNomProvincia(), p -> new ArrayList<>())
		                .add(ubigeo.getNomDistrito());
		        }
		
		        List<UbigeoDepartamentoDTO> departamentos = new ArrayList<>();
		        for (Map.Entry<String, Map<String, List<String>>> depEntry : estructura.entrySet()) {
		            List<UbigeoProvinciaDTO> provincias = new ArrayList<>();
		            for (Map.Entry<String, List<String>> provEntry : depEntry.getValue().entrySet()) {
		                provincias.add(new UbigeoProvinciaDTO(provEntry.getKey(), provEntry.getValue()));
		            }
		            departamentos.add(new UbigeoDepartamentoDTO(depEntry.getKey(), provincias));
		        }
		
		        return departamentos;
         
    	 } catch (Exception e) {
             throw new ServiceException("Error al construir estructura de ubigeos", e);
    	 	}
      
    }
    
    
	@Override
	public List<DepartamentoDTO> obtenerDepartamentos() throws ServiceException {
		try {
			return this.ubigeoRepository.obtenerDepartamentos();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ProvinciaDTO> obtenerProvinciasPorDepartamento(String departamento) throws ServiceException {
	 
		try {
			String valDepartamento = (departamento == null) ? "" : departamento.trim().toUpperCase();
			System.out.println(">>> Buscando provincias para: " + valDepartamento);
			return this.ubigeoRepository.obtenerProvinciasPorDepartamento(valDepartamento);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<DistritoDTO> obtenerDistritosPorProvincia(String departamento, String provincia) throws ServiceException {
 
		try {
			String valDepartamento = (departamento == null) ? "" : departamento.trim().toUpperCase();
			String valProvincia = (provincia == null) ? "" : provincia.trim();
			System.out.println(">>> Buscando Distritor para: " + valDepartamento +" - "+valProvincia);
			return this.ubigeoRepository.obtenerDistritosPorProvincia(valDepartamento, valProvincia);
		} catch (Exception e) {
			
			throw new ServiceException(e);
		}		
	}

	@Override
	public Long ObtenerIdUbigeo(String departamento, String provincia, String distrito) throws ServiceException {
	
		try {
			String valDepartamento = (departamento == null) ? "" : departamento.trim().toUpperCase();
			String valProvincia = (provincia == null) ? "" : provincia.trim().toUpperCase();
			String valDistrito = (distrito == null) ? "" : distrito.trim().toUpperCase();
			
			return this.ubigeoRepository.ObtenerIdUbigeo(valDepartamento, valProvincia, valDistrito);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}

}
