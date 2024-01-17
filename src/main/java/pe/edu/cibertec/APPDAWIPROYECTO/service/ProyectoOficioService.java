package pe.edu.cibertec.APPDAWIPROYECTO.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.OrdenInspeccion;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.ProyectoOficio;
import pe.edu.cibertec.APPDAWIPROYECTO.model.request.ProyectoOficioDto;
import pe.edu.cibertec.APPDAWIPROYECTO.model.response.ResultadoResponse;
import pe.edu.cibertec.APPDAWIPROYECTO.repository.ProyectoOficioRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ProyectoOficioService {

    private ProyectoOficioRepository proyectoOficioRepository;

    public List<ProyectoOficio> listarProyectos(){
        return proyectoOficioRepository.findAll();
    }

    public ResultadoResponse registrarActualizarProyecto(ProyectoOficioDto proyectoOficioDto){
        String mensaje = "Proyecto registrado correctamente";
        boolean respuesta = true;
        try {
            ProyectoOficio nuevoProyectoOficio = new ProyectoOficio();
            if(proyectoOficioDto.getId_orden() > 0){
                nuevoProyectoOficio.setId_proyecto(proyectoOficioDto.getId_proyecto());
            }
            nuevoProyectoOficio.setAsunto(proyectoOficioDto.getAsunto());
            nuevoProyectoOficio.setInformacionrequerida(proyectoOficioDto.getInformacionrequerida());
            nuevoProyectoOficio.setReferencia(proyectoOficioDto.getReferencia());
            OrdenInspeccion ordenInspeccion  = new OrdenInspeccion();
            ordenInspeccion.setId_orden(proyectoOficioDto.getId_orden());
            nuevoProyectoOficio.setOrdenInspeccion(ordenInspeccion);
            proyectoOficioRepository.save(nuevoProyectoOficio);
        }catch (Exception ex){
            mensaje = "Proyecto NO registrado";
            respuesta = false;
        }
        return ResultadoResponse.builder().respuesta(respuesta)
                .mensaje(mensaje).build();
    }

}
