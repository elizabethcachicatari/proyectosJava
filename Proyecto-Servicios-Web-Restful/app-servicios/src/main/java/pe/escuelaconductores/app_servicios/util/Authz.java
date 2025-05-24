package pe.escuelaconductores.app_servicios.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pe.escuelaconductores.app_servicios.entity.UsuarioEntity;
import pe.escuelaconductores.app_servicios.repository.UsuarioRepository;

import java.util.Base64;
import java.util.Optional;

@Service
public class Authz {
/*
    @Value("${seg.user}")
    private String segUser;

    @Value("${seg.pass}")
    private String segPass;
*/
    private final UsuarioRepository usuarioRepository;

    public Authz(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean validate(String authz){

        String credenciales=authz.substring(6);

        byte[] decodedBytes = Base64.getDecoder().decode(credenciales);
        String decode = new String(decodedBytes);

        String []argCred=decode.split(":");

        String user=argCred[0];
        String password=argCred[1];

        System.out.println("segPass => "+user);
        System.out.println("segPass => "+password);
        System.out.println("codificada => "+AppClasicEncrypt.encrypt(password));

        Optional<UsuarioEntity> optUsuarioEntity=usuarioRepository.validar(user, password);
        return optUsuarioEntity.isPresent();

 
    }
}
