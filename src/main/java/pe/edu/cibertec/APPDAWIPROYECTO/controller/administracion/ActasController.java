package pe.edu.cibertec.APPDAWIPROYECTO.controller.administracion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.Actas;
import pe.edu.cibertec.APPDAWIPROYECTO.model.request.ActasDto;
import pe.edu.cibertec.APPDAWIPROYECTO.model.response.ResultadoResponse;
import pe.edu.cibertec.APPDAWIPROYECTO.service.ActasService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/administracion/actas")
public class ActasController {

    private ActasService actasService;
    @GetMapping("/frmactas")
    public String frmMantActas(Model model){
        model.addAttribute("listaactas",
                actasService.listarActas());
        return "backoffice/administracion/frmactas";
    }

    @PostMapping("/registrar")
    @ResponseBody
    public ResultadoResponse registrarActas(@RequestBody ActasDto objActas){
        return actasService.registrarActualizarActas(objActas);
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<Actas> listarActas(){
        return actasService.listarActas();
    }
}
