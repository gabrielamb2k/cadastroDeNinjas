package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/missoes/ui")
public class MissoesControllerUi {

    private final MissoesService missoesService;

    public MissoesControllerUi(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/listar")
    public String listarMissao(Model model){
        List<MissoesDTO> missoes =missoesService.listarMissoes();
        model.addAttribute("missoes" , missoes);
        return "Missoes/listarMissoes";
    }

    @GetMapping("/deletar/{id}")
    public String deletarMissao(@PathVariable Long id) {
        missoesService.deletarMissao(id);
        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/listar/{id}")
    public String mostrarTodosAsMissoesPorId(@PathVariable Long id, Model model){
        MissoesDTO missao = missoesService.listarMissoesPorId(id);
        if(missao != null){
            model.addAttribute("missao", missao);
            return "Missoes/detalhesMissoes";
        } else {
            model.addAttribute("mensagem", "Ninja não encontrado");
            return "Missoes/listarMissoes";
        }
    }

    @GetMapping("/alterar/{id}")
    public String alterarMissao(@PathVariable Long id, MissoesDTO missaoDto ,Model model){
        MissoesDTO missao = missoesService.alterarMissoes(id, missaoDto);
        if(missao != null){
            model.addAttribute("missao", missao);
            return "Missoes/alterarMissoes";}
        else{
            model.addAttribute("mensagem", "Missão não encontrado");
            return "Missoes/listarMissoes";
        }

    }


    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarNovaMissao(Model model){
        model.addAttribute("missao", new MissoesDTO());
        return "Missoes/adicionarMissao";
    }

    @PostMapping("/salvar")
    public String salvarMissao(@ModelAttribute MissoesDTO missao, RedirectAttributes redirectAttributes){
        missoesService.criarMissoes(missao);
        redirectAttributes.addFlashAttribute("mensagem", "Missao criada");
        return "redirect:/missoes/ui/listar";
    }
}
