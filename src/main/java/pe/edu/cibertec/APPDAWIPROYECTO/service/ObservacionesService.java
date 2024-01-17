package pe.edu.cibertec.APPDAWIPROYECTO.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.Observaciones;
import pe.edu.cibertec.APPDAWIPROYECTO.repository.ObservacionesRepository;
import pe.edu.cibertec.APPDAWIPROYECTO.model.response.ResultadoResponse;

import java.util.List;

@Service
@AllArgsConstructor
public class ObservacionesService {

    private ObservacionesRepository observacionesRespository;

    public List<Observaciones> listarObservaciones(){
        return observacionesRespository.findAll();
    }

    public ResultadoResponse registrarObservaciones(Observaciones observaciones){
        Observaciones nuevasObservaciones = new Observaciones();
        if(observaciones.getId_observacion() != null && observaciones.getId_observacion() > 0){
            nuevasObservaciones.setId_observacion(observaciones.getId_observacion());
        }
        nuevasObservaciones.setFecha(observaciones.getFecha());
        nuevasObservaciones.setObservacion1(observaciones.getObservacion1());
        nuevasObservaciones.setObservacion2(observaciones.getObservacion2());
        nuevasObservaciones.setObservacion3(observaciones.getObservacion3());
        String mensaje = "Observaciones registradas";
        boolean respuesta = true;
        try{
            observacionesRespository.save(nuevasObservaciones);
        }catch (Exception ex){
            mensaje = "Observaciones NO registradas";
            respuesta = false;
        }
        return ResultadoResponse.builder().respuesta(respuesta)
                .mensaje(mensaje).build();
    }

    public ResultadoResponse eliminarObservaciones(Integer id_observacion){
        String mensaje = "Observaciones eliminadas";
        boolean respuesta = true;
        try {
            observacionesRespository.deleteById(id_observacion);
        }catch (Exception ex){
            mensaje = "Observaciones NO eliminadas";
            respuesta = false;
        }
        return ResultadoResponse.builder().respuesta(respuesta)
                .mensaje(mensaje).build();
    }

}
