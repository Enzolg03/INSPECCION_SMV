package pe.edu.cibertec.APPDAWIPROYECTO.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.TipoActas;
import pe.edu.cibertec.APPDAWIPROYECTO.model.response.ResultadoResponse;
import pe.edu.cibertec.APPDAWIPROYECTO.repository.TipoActasRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoActasService {

    private TipoActasRepository tipoactasRepository;

    public List<TipoActas> listarTipoActas(){
        return tipoactasRepository.findAll();
    }
    public ResultadoResponse registrarTipoActas(TipoActas tipoactas){
        TipoActas nuevoTipoActas = new TipoActas();
        if(tipoactas.getId_tipoacta() > 0){
            nuevoTipoActas.setId_tipoacta(tipoactas.getId_tipoacta());
        }
        nuevoTipoActas.setDescripcion(tipoactas.getDescripcion());
        String mensaje = "Tipo de Actas registrado";
        boolean respuesta = true;
        try{
            tipoactasRepository.save(nuevoTipoActas);
        }catch (Exception ex){
            mensaje = "Tipo de Actas NO registrado";
            respuesta = false;
        }
        return ResultadoResponse.builder().respuesta(respuesta)
                .mensaje(mensaje).build();
    }

    public ResultadoResponse eliminarTipoActas(Integer id_tipoacta){
        String mensaje = "Tipo de Acta eliminado";
        boolean respuesta = true;
        try {
            tipoactasRepository.deleteById(id_tipoacta);
        }catch (Exception ex){
            mensaje = "Tipo de Acta NO eliminado";
            respuesta = false;
        }
        return ResultadoResponse.builder().respuesta(respuesta)
                .mensaje(mensaje).build();
    }
}

