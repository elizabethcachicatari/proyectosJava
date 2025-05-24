package pe.escuelaconductores.app_servicios.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.escuelaconductores.app_servicios.entity.EstadoEntidadEntity;
import pe.escuelaconductores.app_servicios.services.EstadoEntidadService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/estados")
public class EstadoEntidadController {

	private Map<String, String> map = new HashMap<>();
	private final String MSG_INTERNAL_ERROR = "Se ha producido un error interno";
	//private final String MSG_BAD_REQUEST 	= "Operación no valida";
	
	private final EstadoEntidadService estadoEntidadService;

	public EstadoEntidadController(EstadoEntidadService estadoEntidadService) {
		this.estadoEntidadService = estadoEntidadService;
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<?> listAll() {

		try {
			List<EstadoEntidadEntity> listEstados = this.estadoEntidadService.listAll();
			if (listEstados.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(listEstados);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
}
