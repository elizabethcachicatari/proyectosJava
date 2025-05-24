package pe.escuelaconductores.app_servicios.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UbigeoProvinciaDTO {
	
    private String nombre;
    private List<String> distritos;
}
