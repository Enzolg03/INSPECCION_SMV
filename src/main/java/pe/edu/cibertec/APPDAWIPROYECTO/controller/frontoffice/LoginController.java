package pe.edu.cibertec.APPDAWIPROYECTO.controller.frontoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.Rol;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.Usuario;
import pe.edu.cibertec.APPDAWIPROYECTO.model.response.ResultadoResponse;
import pe.edu.cibertec.APPDAWIPROYECTO.service.RolService;
import pe.edu.cibertec.APPDAWIPROYECTO.service.UsuarioService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class LoginController {
    private UsuarioService usuarioService;
    private RolService rolService;
    @GetMapping("/login")
    public String login(){
        return "frontoffice/auth/login";
    }

    @GetMapping("/login-success")
    public String loginsuccess(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Añade los atributos al modelo
        model.addAttribute("username", auth.getName());
        model.addAttribute("rol", auth.getAuthorities());
        return "redirect:/home";
    }


    // USUARIO
    @GetMapping("/frmusuario")
    public String index(Model model){
        model.addAttribute("listausuario", usuarioService.listarUsuarios());
        List<Rol> roles = rolService.listarRoles();
        model.addAttribute("roles", roles);
        return "backoffice/administracion/frmusuario";
    }

    @PostMapping("/guardarusuario")
    @ResponseBody
    public ResultadoResponse guardarUsuario(@ModelAttribute Usuario usuario, @RequestParam String rol){
        return usuarioService.saveUserWithRole(usuario, rol);
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<Usuario> listarUsuarios(){
        return usuarioService.listarUsuarios();
    }

    @DeleteMapping("/eliminarusuario")
    @ResponseBody
    public ResultadoResponse eliminarUsuario(@RequestBody Usuario usuario){
        return usuarioService.eliminarUsuario(usuario.getIdusuario());
    }


}