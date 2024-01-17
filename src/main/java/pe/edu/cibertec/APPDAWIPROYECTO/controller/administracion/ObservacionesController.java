package pe.edu.cibertec.APPDAWIPROYECTO.controller.administracion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.Observaciones;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.Rol;
import pe.edu.cibertec.APPDAWIPROYECTO.model.response.ResultadoResponse;
import pe.edu.cibertec.APPDAWIPROYECTO.service.ObservacionesService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/administracion/observaciones")
public class ObservacionesController {
    private ObservacionesService observacionesService;
    @GetMapping("/frmobservaciones")
    public String index(Model model){
        model.addAttribute("listaobservaciones",
                observacionesService.listarObservaciones());
        return "backoffice/administracion/frmobservaciones";
    }
    @PostMapping("/registrar")
    @ResponseBody
    public ResultadoResponse registrarObservaciones(
            @RequestBody Observaciones objObs
    ){
        return observacionesService.registrarObservaciones(objObs);
    }
    @DeleteMapping("/eliminar")
    @ResponseBody
    public ResultadoResponse eliminarObservaciones(
            @RequestBody Observaciones objObs
    ){
        return observacionesService.eliminarObservaciones(objObs.getId_observacion());
    }
    @GetMapping("/listar")
    @ResponseBody
    public List<Observaciones> listarObservaciones(){
        return observacionesService.listarObservaciones();
    }
}
