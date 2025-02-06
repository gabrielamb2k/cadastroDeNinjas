package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {

    private final MissoesRepository missoesRepository;
    private final MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    //Listar missoes
    public List<MissoesDTO> listarMissoes(){
        List<MissoesModel> missoes = missoesRepository.findAll();
        return missoes.stream()
                .map(missoesMapper::map)
                .collect(Collectors.toList());
    }

    //listar missao por id
    public MissoesDTO listarMissoesPorId(Long id){
        Optional<MissoesModel> missoesPorId = missoesRepository.findById(id);
        return missoesPorId.map(missoesMapper::map).orElse(null);
    }

    //Criar missoes
    public MissoesDTO criarMissoes(MissoesDTO missoesDTO){
        MissoesModel missoes = missoesMapper.map(missoesDTO);
        missoes = missoesRepository.save(missoes);
        return  missoesMapper.map(missoes);
    }

    //alterar missoes
    public MissoesDTO alterarMissoes(Long id, MissoesDTO missoesDTO){
        Optional <MissoesModel> missoes = missoesRepository.findById(id);
        if(missoes.isPresent()){
            MissoesModel missoesAtualizada = missoesMapper.map(missoesDTO);
            missoesAtualizada.setId(id);
            MissoesModel missaoSalva = missoesRepository.save(missoesAtualizada);
            return missoesMapper.map(missaoSalva);
        }
        return null;
    }

    //Deletar missao
    public void deletarMissao(Long id){
        missoesRepository.deleteById(id);
    }
}
