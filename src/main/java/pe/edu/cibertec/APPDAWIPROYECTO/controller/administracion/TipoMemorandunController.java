package pe.edu.cibertec.APPDAWIPROYECTO.controller.administracion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.Rol;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.TipoMemorandun;
import pe.edu.cibertec.APPDAWIPROYECTO.model.response.ResultadoResponse;
import pe.edu.cibertec.APPDAWIPROYECTO.service.TipoMemorandunService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/administracion/tipomemorandun")
public class TipoMemorandunController {
    private TipoMemorandunService tipoMemorandunService;
    @GetMapping("/frmTipoMemorandun")
    public String index(Model model){
        model.addAttribute("listatipomemorandun",
                tipoMemorandunService.listarTipoMemorandun());
        return "backoffice/administracion/frmTipoMemorandun";
    }
    @PostMapping("/registrar")
    @ResponseBody
    public ResultadoResponse registrarTipoMemo(
            @RequestBody TipoMemorandun objTipoMemo
    ){
        return tipoMemorandunService.registrarTipoMemo(objTipoMemo);
    }

    @DeleteMapping("/eliminar")
    @ResponseBody
    public ResultadoResponse eliminarTipoMemo(
            @RequestBody TipoMemorandun objTipoMemo
    ){
        return tipoMemorandunService.eliminarTipoMemo(objTipoMemo.getId_tipomemorandum());
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<TipoMemorandun> listarTipoMemorandun(){
        return
                tipoMemorandunService.listarTipoMemorandun();
    }

}
