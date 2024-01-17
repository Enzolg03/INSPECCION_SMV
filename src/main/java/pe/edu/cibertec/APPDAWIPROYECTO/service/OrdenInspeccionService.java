package pe.edu.cibertec.APPDAWIPROYECTO.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.OrdenInspeccion;
import pe.edu.cibertec.APPDAWIPROYECTO.repository.OrdenInspeccionRepository;
import pe.edu.cibertec.APPDAWIPROYECTO.model.response.ResultadoResponse;

import java.util.List;

@Service
@AllArgsConstructor
public class OrdenInspeccionService {

    private OrdenInspeccionRepository ordenInspeccionRepository;

    public List<OrdenInspeccion> listarOrdenInspeccion(){
        return ordenInspeccionRepository.findAll();
    }

    public ResultadoResponse registrarOrdenInspeccion(OrdenInspeccion ordenInspeccion){
        OrdenInspeccion nuevaOrdenInspeccion = new OrdenInspeccion();
        if(ordenInspeccion.getId_orden() != null && ordenInspeccion.getId_orden() > 0){
            nuevaOrdenInspeccion.setId_orden(ordenInspeccion.getId_orden());
        }
        nuevaOrdenInspeccion.setNumeroruc(ordenInspeccion.getNumeroruc());
        nuevaOrdenInspeccion.setNombreempresa(ordenInspeccion.getNombreempresa());
        nuevaOrdenInspeccion.setDireccion(ordenInspeccion.getDireccion());
        nuevaOrdenInspeccion.setEmail(ordenInspeccion.getEmail());
        nuevaOrdenInspeccion.setResponsable(ordenInspeccion.getResponsable());
        String mensaje = "Orden de Inspeccion registrada";
        boolean respuesta = true;
        try{
            ordenInspeccionRepository.save(nuevaOrdenInspeccion);
        }catch (Exception ex){
            mensaje = "Orden de Inspeccion NO registrada";
            respuesta = false;
        }
        return ResultadoResponse.builder().respuesta(respuesta)
                .mensaje(mensaje).build();
    }

    public ResultadoResponse eliminarOrdenInspeccion(Integer id_orden){
        String mensaje = "Orden de Inspeccion eliminada";
        boolean respuesta = true;
        try {
            ordenInspeccionRepository.deleteById(id_orden);
        }catch (Exception ex){
            mensaje = "Orden de Inspeccion NO eliminada";
            respuesta = false;
        }
        return ResultadoResponse.builder().respuesta(respuesta)
                .mensaje(mensaje).build();
    }

}
