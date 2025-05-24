package pe.escuelaconductores.app_servicios.controller;

import java.util.HashMap;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.escuelaconductores.app_servicios.dto.LoginDTO;
import pe.escuelaconductores.app_servicios.entity.UsuarioEntity;
import pe.escuelaconductores.app_servicios.services.UsuarioService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/login")
public class UsuarioController {

	private Map<String, String> map = new HashMap<>();

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody LoginDTO login) {
		try {
			Optional<UsuarioEntity> user = this.usuarioService.findByUsuarioClave(login.getUsuario(), login.getClave());
			if (user.isPresent()) {				
		        String token = "Bearer-" + UUID.randomUUID();  
		        return ResponseEntity.ok(Map.of("token", token));
			} else {
				 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
			}
		} catch (Exception e) {
			map.put("error", "Error interno");
			return ResponseEntity.internalServerError().body(map);
		}
		
		
	}
	
}
