package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    // mostrar missoes
    // GET -- Mandar uma requisiao para mostrar as missoes
    @GetMapping("/listar")
    public String listarMissao(){
        return "Missoes listadas com sucesso";
    }

    // adicionar missoes
    // POST -- Mandar uma requisiçao para criar missoes
    @PostMapping("/criar")
    public String criarMissao(){
        return "Missao criada com sucesso";
    }

    //altera missoes
    // PUT -- Mandar uma requisiçao para alterar missoes
    @PutMapping("/alterar")
    public String alterarMissao(){
        return "Missao alterada com sucesso";
    }

    // deleta missoes
    // DELETE -- Mandar uma requisiçao para deletar missoes
    @DeleteMapping("/deletar")
    public String deletarMissao(){
        return "Missao deletada com sucesso";
    }
}
