package pe.edu.cibertec.APPDAWIPROYECTO.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.Rol;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.TipoMemorandun;
import pe.edu.cibertec.APPDAWIPROYECTO.model.response.ResultadoResponse;
import pe.edu.cibertec.APPDAWIPROYECTO.repository.TipoMemorandunRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoMemorandunService {

    private TipoMemorandunRepository tipoMemorandunRepository;

    public List<TipoMemorandun> listarTipoMemorandun(){

        return tipoMemorandunRepository.findAll();
    }

    public ResultadoResponse registrarTipoMemo(TipoMemorandun tipoMemorandun){
        TipoMemorandun nuevoTipoMemo = new TipoMemorandun();
        if(tipoMemorandun.getId_tipomemorandum() > 0 ||
                tipoMemorandun.getId_tipomemorandum() !=null){
            nuevoTipoMemo.setId_tipomemorandum(tipoMemorandun.getId_tipomemorandum());
        }
        nuevoTipoMemo.setDescripcion(tipoMemorandun.getDescripcion());
        String mensaje = "Tipo de Memorandun  registrado";
        boolean respuesta = true;
        try{
            tipoMemorandunRepository.save(nuevoTipoMemo);
        }catch (Exception ex){
            mensaje = "Tipo Memorandun NO registrado";
            respuesta = false;
        }
        return ResultadoResponse.builder().respuesta(respuesta)
                .mensaje(mensaje).build();
    }

    public ResultadoResponse eliminarTipoMemo(Integer id_tipomemorandum){
        String mensaje = "Tipo de Memorandun eliminado";
        boolean respuesta = true;
        try {
            tipoMemorandunRepository.deleteById(id_tipomemorandum);
        }catch (Exception ex){
            mensaje = "Tipo de Memorandun NO eliminado ";
            respuesta = false;
        }
        return ResultadoResponse.builder().respuesta(respuesta)
                .mensaje(mensaje).build();
    }
}
