package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    // mostrar missoes
    // GET -- Mandar uma requisiao para mostrar as missoes
    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissao(){
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarMissaoPorId(@PathVariable Long id){
        MissoesDTO missoes = missoesService.listarMissoesPorId(id);
        if(missoes != null){
            return ResponseEntity.ok(missoes);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missoes de ID " + id + " nao encontrada");
        }
    }

    // adicionar missoes
    // POST -- Mandar uma requisiçao para criar missoes
    @PostMapping("/criar")
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missoesDTO){
        MissoesDTO missoes = missoesService.criarMissoes(missoesDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missao criada com sucesso " + missoesDTO.getNome());

    }

    //altera missoes
    // PUT -- Mandar uma requisiçao para alterar missoes
    @PutMapping("/alterar{id}")
    public ResponseEntity<?> alterarMissao(@PathVariable Long id, @RequestBody MissoesDTO missoesDTO){
        MissoesDTO missaoAlterada = missoesService.alterarMissoes(id, missoesDTO);
        if( missaoAlterada != null){
            return ResponseEntity.ok(missaoAlterada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missao de ID " + id + " nao encontrada");
        }
    }

    // deleta missoes
    // DELETE -- Mandar uma requisiçao para deletar missoes
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissao(@PathVariable Long id)
    {
        if(missoesService.listarMissoesPorId(id) != null){
            missoesService.deletarMissao(id);
            return ResponseEntity.ok("Missao de ID " + id + " deletada");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missao de ID " + id + " nao encontrada");
        }
    }
}
