package pe.edu.cibertec.APPDAWIPROYECTO.controller.administracion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.OrdenInspeccion;
import pe.edu.cibertec.APPDAWIPROYECTO.service.OrdenInspeccionService;
import pe.edu.cibertec.APPDAWIPROYECTO.model.response.ResultadoResponse;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/administracion/ordeninspeccion")
public class OrdenInspeccionController {
    private OrdenInspeccionService ordenInspeccionService;

    @GetMapping("/frmordeninspeccion")
    public String index(Model model){
        model.addAttribute("listaordeninspeccion",
                ordenInspeccionService.listarOrdenInspeccion());
        return "backoffice/administracion/frmordeninspeccion";
    }

    @PostMapping("/registrar")
    @ResponseBody
    public ResultadoResponse registrarOrdenInspeccion(
            @RequestBody OrdenInspeccion objOrdenInspeccion
    ){
        return ordenInspeccionService.registrarOrdenInspeccion(objOrdenInspeccion);
    }

    @DeleteMapping("/eliminar")
    @ResponseBody
    public ResultadoResponse eliminarOrdenInspeccion(
            @RequestBody OrdenInspeccion objOrdenInspeccion
    ){
        return ordenInspeccionService.eliminarOrdenInspeccion(objOrdenInspeccion.getId_orden());
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<OrdenInspeccion> listarOrdenInspeccion(){
        return ordenInspeccionService.listarOrdenInspeccion();
    }
}
