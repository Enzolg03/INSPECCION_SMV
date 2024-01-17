package pe.edu.cibertec.APPDAWIPROYECTO.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.Actas;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.TipoActas;
import pe.edu.cibertec.APPDAWIPROYECTO.model.request.ActasDto;
import pe.edu.cibertec.APPDAWIPROYECTO.model.response.ResultadoResponse;
import pe.edu.cibertec.APPDAWIPROYECTO.repository.ActasRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ActasService {

    private ActasRepository actasRepository;

    public List<Actas> listarActas(){
        return actasRepository.findAll();
    }

    public ResultadoResponse registrarActualizarActas(ActasDto actasDto){
        String mensaje = "Acta registrado correctamente";
        boolean respuesta = true;
        try {
            Actas nuevaActa = new Actas();
            if(actasDto.getId_acta() > 0){
                nuevaActa.setId_acta(actasDto.getId_acta());
            }
            nuevaActa.setDestinatario(actasDto.getDestinatario());
            nuevaActa.setEmisor(actasDto.getEmisor());
            nuevaActa.setFecha(actasDto.getFecha());
            nuevaActa.setDocumento(actasDto.getDocumento());

            TipoActas tipoActas  = new TipoActas();
            tipoActas.setId_tipoacta(actasDto.getId_tipoacta());
            nuevaActa.setTipoActas(tipoActas);
            System.out.println(nuevaActa);
            actasRepository.save(nuevaActa);
        }catch (Exception ex){
            mensaje = "Acta NO registrado";
            respuesta = false;
        }
        return ResultadoResponse.builder().respuesta(respuesta)
                .mensaje(mensaje).build();
    }
}
