package pe.escuelaconductores.app_servicios.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.escuelaconductores.app_servicios.entity.TipoEntidadEntity;
import pe.escuelaconductores.app_servicios.services.TipoEntidadService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tipos")
public class TipoEntidadController {

	private Map<String, String> map = new HashMap<>();
	private final String MSG_INTERNAL_ERROR = "Se ha producido un error interno";
	 
	
	private final TipoEntidadService tipoEntidadService;

	public TipoEntidadController(TipoEntidadService tipoEntidadService) {
		this.tipoEntidadService = tipoEntidadService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> listAll() {

		try {
			List<TipoEntidadEntity> listTiposEntidad = this.tipoEntidadService.listAll();
			if (listTiposEntidad.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.ok(listTiposEntidad);
			}
		} catch (Exception e) {
			map.put("error", MSG_INTERNAL_ERROR);
			return ResponseEntity.internalServerError().body(map);
		}
	}
	
	
}
