package pe.escuelaconductores.app_servicios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntidadDTO {
	  private String ruc;
	    private String nombre;
	    private String direccion;
	    private String correo;
	    private String telefono;
	    private Long idUbigeo;
	    private Long idTipoEntidad;
	    private Long idEstadoEntidad;
}
