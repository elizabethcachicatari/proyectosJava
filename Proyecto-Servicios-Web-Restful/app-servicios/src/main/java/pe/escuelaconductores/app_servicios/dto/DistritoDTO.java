package pe.escuelaconductores.app_servicios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistritoDTO {
    private Long idUbigeo;
    private String codDistrito;
    private String nomDistrito;
}
