package pe.edu.cibertec.APPDAWIPROYECTO.controller.administracion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.Rol;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.APPDAWIPROYECTO.service.RolService;
import pe.edu.cibertec.APPDAWIPROYECTO.model.response.ResultadoResponse;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/administracion/rol")
public class RolController {
    private RolService rolService;
    @GetMapping("/frmrol")
    public String index(Model model){
        model.addAttribute("listaroles",
                rolService.listarRoles());
        return "backoffice/administracion/frmrol";
    }

    @PostMapping("/registrar")
    @ResponseBody
    public ResultadoResponse registrarRol(
            @RequestBody Rol objRol
    ){
        return rolService.registrarRol(objRol);
    }

    @DeleteMapping("/eliminar")
    @ResponseBody
    public ResultadoResponse eliminarRol(
            @RequestBody Rol objRol
    ){
        return rolService.eliminarRol(objRol.getIdrol());
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<Rol> listarRoles(){
        return rolService.listarRoles();
    }
}
