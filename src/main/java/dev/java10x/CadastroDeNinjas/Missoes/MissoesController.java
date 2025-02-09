package dev.java10x.CadastroDeNinjas.Missoes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Lista um missao por id", description = "Rota lista uma missao pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missao encontrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Missao nao encontrada")
    })
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
    @Operation(summary = "Cria um nova missao", description = "Rota cria um nova missao e inserre no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missao criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação da missao")
    })
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missoesDTO){
        MissoesDTO missoes = missoesService.criarMissoes(missoesDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missao criada com sucesso " + missoesDTO.getNome());

    }

    //altera missoes
    // PUT -- Mandar uma requisiçao para alterar missoes
    @PutMapping("/alterar{id}")
    @Operation(summary = "Altera uma missao por id", description = "Rota altera uma missao pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missao alterada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Missao nao encontrada, não foi possivel alterar")
    })
    public ResponseEntity<?> alterarMissao(
            @Parameter(description = "Usuário manda o id no caminho da requisiçãp")
            @PathVariable Long id,
            @Parameter(description = "Usuário manda os dados da missao a ser atualizada no corpo da requisição")
            @RequestBody MissoesDTO missoesDTO){
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
    @Operation(summary = "Deleta uma missao por id", description = "Rota deleta uma missao pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missao deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Missao nao encontrada, não foi possóvel deletar")
    })
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
