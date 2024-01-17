package pe.edu.cibertec.APPDAWIPROYECTO.controller.administracion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.TipoActas;
import pe.edu.cibertec.APPDAWIPROYECTO.model.response.ResultadoResponse;
import pe.edu.cibertec.APPDAWIPROYECTO.service.TipoActasService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/administracion/tipoactas")
public class TipoActasController {
    private TipoActasService tipoactasService;
    @GetMapping("/frmtipoactas")
    public String index(Model model){
        model.addAttribute("listatipoactas",tipoactasService.listarTipoActas());
        return "backoffice/administracion/frmtipoactas";
    }
    @PostMapping("/registrar")
    @ResponseBody
    public ResultadoResponse registrarTipoActas(
            @RequestBody TipoActas objTipoActas
    ){
        return tipoactasService.registrarTipoActas(objTipoActas);
    }

    @DeleteMapping("/eliminar")
    @ResponseBody
    public ResultadoResponse eliminarTipoActas(
            @RequestBody TipoActas objTipoActas
    ){
        return tipoactasService.eliminarTipoActas(objTipoActas.getId_tipoacta());
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<TipoActas> listarTipoActas(){
        return tipoactasService.listarTipoActas();
    }
}
