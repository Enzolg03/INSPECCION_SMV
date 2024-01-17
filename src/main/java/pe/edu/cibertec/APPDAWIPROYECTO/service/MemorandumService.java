package pe.edu.cibertec.APPDAWIPROYECTO.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.Memorandum;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.TipoMemorandun;
import pe.edu.cibertec.APPDAWIPROYECTO.model.request.MemorandumDto;
import pe.edu.cibertec.APPDAWIPROYECTO.model.response.ResultadoResponse;
import pe.edu.cibertec.APPDAWIPROYECTO.repository.MemorandumRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class MemorandumService {

    private MemorandumRepository memorandumRepository;

    public List<Memorandum> listarMemorandum(){
        return memorandumRepository.findAll();
    }

    public ResultadoResponse registrarActualizarMemorandum(MemorandumDto memorandumDto){
        String mensaje = "Memorandum registrado correctamente";
        boolean respuesta = true;
        try {
            Memorandum nuevoMemorandum = new Memorandum();
            if(memorandumDto.getId_tipomemorandum() > 0){
                nuevoMemorandum.setId_memorandum(memorandumDto.getId_memorandum());
            }
            nuevoMemorandum.setDestinatario(memorandumDto.getDestinatario());
            nuevoMemorandum.setEmisor(memorandumDto.getEmisor());
            nuevoMemorandum.setAsunto(memorandumDto.getAsunto());
            nuevoMemorandum.setReferencia(memorandumDto.getReferencia());
            nuevoMemorandum.setFecha(memorandumDto.getFecha());

            TipoMemorandun tipoMemorandun  = new TipoMemorandun();
            tipoMemorandun.setId_tipomemorandum(memorandumDto.getId_tipomemorandum());
            nuevoMemorandum.setTipoMemorandun(tipoMemorandun);
            memorandumRepository.save(nuevoMemorandum);
        }catch (Exception ex){
            mensaje = "Memorandum NO registrado";
            respuesta = false;
        }
        return ResultadoResponse.builder().respuesta(respuesta)
                .mensaje(mensaje).build();
    }
}
