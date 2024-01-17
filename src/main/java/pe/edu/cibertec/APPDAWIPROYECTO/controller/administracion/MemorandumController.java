package pe.edu.cibertec.APPDAWIPROYECTO.controller.administracion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.Memorandum;
import pe.edu.cibertec.APPDAWIPROYECTO.model.request.MemorandumDto;
import pe.edu.cibertec.APPDAWIPROYECTO.model.response.ResultadoResponse;
import pe.edu.cibertec.APPDAWIPROYECTO.service.MemorandumService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/administracion/memorandum")
public class MemorandumController {

    private MemorandumService memorandumService;
    @GetMapping("/frmmemorandum")
    public String frmMantMemorandum(Model model){
        model.addAttribute("listamemorandum",
                memorandumService.listarMemorandum());
        return "backoffice/administracion/frmmemorandum";
    }

    @PostMapping("/registrar")
    @ResponseBody
    public ResultadoResponse registrarmemorandum(
            @RequestBody MemorandumDto objMemorandum
    ){
        return memorandumService.registrarActualizarMemorandum(objMemorandum);
    }
    @GetMapping("/listar")
    @ResponseBody
    public List<Memorandum> listarMemorandum(){
        return memorandumService.listarMemorandum();
    }
}
