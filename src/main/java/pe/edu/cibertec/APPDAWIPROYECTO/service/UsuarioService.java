package pe.edu.cibertec.APPDAWIPROYECTO.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.Rol;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.Usuario;
import pe.edu.cibertec.APPDAWIPROYECTO.repository.RolRepository;
import pe.edu.cibertec.APPDAWIPROYECTO.repository.UsuarioRepository;
import pe.edu.cibertec.APPDAWIPROYECTO.model.response.ResultadoResponse;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder =
            new BCryptPasswordEncoder();

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario findByUsername(String username){
        return usuarioRepository.findByNomusuario(username);
    }

    public ResultadoResponse saveUserWithRole(Usuario usuario, String rol){
        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
        Rol usuarioRol = rolRepository.findByNomrol(rol);
        usuario.setActivo(true);
        usuario.setRoles(new HashSet<>(Arrays.asList(usuarioRol)));
        String mensaje = "Usuario registrado";
        boolean respuesta = true;
        try{
            usuarioRepository.save(usuario);
        }catch (Exception ex){
            mensaje = "Usuario NO registrado";
            respuesta = false;
        }
        return ResultadoResponse.builder().respuesta(respuesta)
                .mensaje(mensaje).build();
    }

    public ResultadoResponse eliminarUsuario(Integer idusuario){
        String mensaje = "Usuario eliminado";
        boolean respuesta = true;
        try {
            usuarioRepository.deleteById(idusuario);
        }catch (Exception ex){
            mensaje = "Usuario NO eliminado";
            respuesta = false;
        }
        return ResultadoResponse.builder().respuesta(respuesta)
                .mensaje(mensaje).build();
    }


}
