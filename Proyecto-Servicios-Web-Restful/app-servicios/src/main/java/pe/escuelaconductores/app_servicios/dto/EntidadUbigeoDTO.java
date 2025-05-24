package pe.escuelaconductores.app_servicios.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntidadUbigeoDTO extends RepresentationModel<EntidadUbigeoDTO>{
    private Long idEntidad;
    private String ruc;
    private String nombre;
    private String direccion;
    private String correo;
    private String telefono;

    private String departamento;
    private String provincia;
    private String distrito;

    private String tipoEntidad;
    private String estadoEntidad;
}
