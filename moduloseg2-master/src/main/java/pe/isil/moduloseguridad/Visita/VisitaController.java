package pe.isil.moduloseguridad.Visita;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.isil.moduloseguridad.afiliadoUser.Afiliado;
import pe.isil.moduloseguridad.afiliadoUser.AfiliadoService;
import pe.isil.moduloseguridad.shared.BasicRespone;

import java.util.List;


@Controller
@RequestMapping("/visita")
public class VisitaController {
    @Autowired
    private VisitaService visitaService;

    @Autowired
    private AfiliadoService afiliadoService;

    @GetMapping("/")
    public String indexVisita(Afiliado dni, Model model){
        List <Afiliado> afil= (List<Afiliado>) afiliadoService.getAfiliadoByDni(dni.getDni());
        List<Visita> vist = visitaService.findAllVisitas();
        model.addAttribute("visit",vist);
        model.addAttribute("afi",afil);
        return "visita/indexvisita";
    }

    @GetMapping("/create")
    public String createView( Model model){

        List<Afiliado> afiliados=afiliadoService.findAll();
        model.addAttribute("afiliados", afiliados);

        return "visita/crearvisita";
    }


    @PostMapping("/create")
    public String registrarVisita( Visita visita, Model model) {

        visitaService.save(visita);
        return "redirect:/visita/";
    }


    @GetMapping("/update")
    public String update(@RequestParam("id") Long id, Model model){
        Visita vst = visitaService.getVisitaById(id);
        model.addAttribute("vst",vst);
        return "visita/actualizarvisita";
    }


    @PostMapping("/update")
    public String updateApp(Visita visitToUpdate, Model model){

        BasicRespone response = visitaService.update(visitToUpdate,visitToUpdate.getId());
        if(response.getCode().equals("200")){
            return "redirect:/visita/";
        }else{
            model.addAttribute("resp",response);
            return "./response";
        }
    }

    @DeleteMapping("/delete")
    public String deleteApp(@RequestParam("id") Long id, Model model){
        visitaService.delete(id);
        return "redirect:/visita/";
    }

    @Data
    @Builder
    public static class Response {
        String code;
        String message;
    }
}
