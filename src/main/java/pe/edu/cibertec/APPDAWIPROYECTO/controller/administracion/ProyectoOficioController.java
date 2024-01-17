package pe.edu.cibertec.APPDAWIPROYECTO.controller.administracion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.ProyectoOficio;
import pe.edu.cibertec.APPDAWIPROYECTO.model.request.ProyectoOficioDto;
import pe.edu.cibertec.APPDAWIPROYECTO.model.response.ResultadoResponse;
import pe.edu.cibertec.APPDAWIPROYECTO.service.ProyectoOficioService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/administracion/proyectooficio")
public class ProyectoOficioController {

    private ProyectoOficioService proyectoOficioService;
    @GetMapping("/frmproyectooficio")
    public String frmMantProyecto(Model model){
        model.addAttribute("listaproyectos",
                proyectoOficioService.listarProyectos());
        return "backoffice/administracion/frmproyectooficio";
    }

    @PostMapping("/registrar")
    @ResponseBody
    public ResultadoResponse registrarSala(
            @RequestBody ProyectoOficioDto objProyecto
    ){
        return proyectoOficioService.registrarActualizarProyecto(objProyecto);
    }
    @GetMapping("/listar")
    @ResponseBody
    public List<ProyectoOficio> listarProyectos(){
        return proyectoOficioService.listarProyectos();
    }
}
