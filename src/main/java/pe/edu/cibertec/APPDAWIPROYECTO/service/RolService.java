package pe.edu.cibertec.APPDAWIPROYECTO.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.Rol;
import pe.edu.cibertec.APPDAWIPROYECTO.repository.RolRepository;
import pe.edu.cibertec.APPDAWIPROYECTO.model.response.ResultadoResponse;

import java.util.List;

@Service
@AllArgsConstructor
public class RolService {

    private RolRepository rolRepository;

    public List<Rol> listarRoles(){
        return rolRepository.findAll();
    }

    public ResultadoResponse registrarRol(Rol rol){
        Rol nuevoRol = new Rol();
        if(rol.getIdrol() > 0){
            nuevoRol.setIdrol(rol.getIdrol());
        }
        nuevoRol.setNomrol(rol.getNomrol());
        String mensaje = "Rol registrado";
        boolean respuesta = true;
        try{
            rolRepository.save(nuevoRol);
        }catch (Exception ex){
            mensaje = "Rol NO registrado";
            respuesta = false;
        }
        return ResultadoResponse.builder().respuesta(respuesta)
                .mensaje(mensaje).build();
    }

    public ResultadoResponse eliminarRol(Integer idrol){
        String mensaje = "Rol eliminado";
        boolean respuesta = true;
        try {
            rolRepository.deleteById(idrol);
        }catch (Exception ex){
            mensaje = "Rol NO eliminado";
            respuesta = false;
        }
        return ResultadoResponse.builder().respuesta(respuesta)
                .mensaje(mensaje).build();
    }

}

