package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    // adicionar ninja (CREATE)
    @PostMapping("/criar")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso" + novoNinja.getNome() + " (ID): " + novoNinja.getId());
    }

    // mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas(){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    // mostrar ninjas por id (READ)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> mostrarTodosOsNinjasPorId(@PathVariable Long id){
        NinjaDTO ninjaId = ninjaService.listarNinjasPorId(id);
        if(ninjaId != null){
            return ResponseEntity.ok(ninjaId);
        } else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja de ID " + id + " não encontrado");
        }
    }

    //alterar dados dos ninjas(UPDATE)
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarNinjasPorId(@PathVariable Long id, @RequestBody NinjaDTO ninjaDTO){
        NinjaDTO ninjaAlterado = ninjaService.alterarNinjasPorId(id, ninjaDTO);
        if(ninjaAlterado != null){
            return ResponseEntity.ok(ninjaAlterado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja do ID " + id + " nao existe");
        }
    }

    //deletar ninjas(DELETE)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id){
        if(ninjaService.listarNinjasPorId(id) != null){
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok("Ninja com o ID " + id + " deletado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja de ID " + id + " não encontrado");
        }
    }

}
