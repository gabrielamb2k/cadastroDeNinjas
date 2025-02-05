package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/bemvindo")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota";
    }

    // adicionar ninja (CREATE)
    @PostMapping("/criar")
    public String criarNinja(){
        return "Ninja criado";
    }

    // mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas(){
        return ninjaService.listarNinjas();
    }

    // mostrar ninjas por id (READ)
    @GetMapping("/todos")
    public String mostrarTodosOsNinjasPorId(){
        return "mostrar todos os ninjas por id";
    }

    //alterar dados dos ninjas(UPDATE)
    @PutMapping("/alterarID")
    public String alterarNinjasPorId(){
        return "alterar ninja por id";
    }

    //deletar ninjas(DELETE)
    @DeleteMapping("/deletarID")
    public String deletarNinjaPorId(){
        return "Ninja id deletado";
    }
}
