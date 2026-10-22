package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbrigoService {

    @Autowired
    private final AbrigoRepository abrigoRepository;
    public AbrigoService(AbrigoRepository abrigoRepository) {
        this.abrigoRepository = abrigoRepository;
    }

    @Autowired
    private PetRepository petRepository;

//    public void cadastrar(CadastroAbrigoDto dto) {
//        boolean nomeJaCadastrado = abrigoRepository.existsByNome(dto.nome());
//        boolean telefoneJaCadastrado = abrigoRepository.existsByTelefone(dto.telefone());
//        boolean emailJaCadastrado = abrigoRepository.existsByEmail(dto.email());
//
//        if (nomeJaCadastrado || telefoneJaCadastrado || emailJaCadastrado) {
//            throw new ValidacaoException("Dados já cadastrados para outro abrigo!");
//        } else {
//            abrigoRepository.save(new Abrigo(dto));
//        }
//    }

//    public List<Pet> listaPets(String idOuNome) {
//        long id = Long.parseLong(idOuNome);
//        List<Abrigo> petsAbrigo = abrigoRepository.findById(id);
//        if (!petsAbrigo.isEmpty()) {
//            return petsAbrigo;
//        } else {
//            List<Pet> pets = abrigoRepository.findByNome(idOuNome);
//            return pets;
//        }
//    }

    public List<PetDto> listaPetsDoAbrigo(String idOuNome) {
        long id = Long.parseLong(idOuNome);
        List<PetDto>  petsDoAbrigoId = abrigoRepository.findById(id);
        if (!petsDoAbrigoId.isEmpty()) {
            return petsDoAbrigoId;
        } else {
            List<PetDto>  petsDoAbrigoNome = abrigoRepository.procuraPorNome(idOuNome);
            return petsDoAbrigoNome;
        }
    }

    public List<AbrigoDto> listar() {
        //List<> lista = abrigoRepository
        List<AbrigoDto> lista;
        lista = abrigoRepository
                .findAll()
                .stream()
                .map(AbrigoDto::new)
                .toList();
        return lista;
    }

    public void cadastrar(CadastroAbrigoDto dto) {
        boolean jaCadastrado = abrigoRepository.existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.email());
        if (jaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro abrigo!");
        }
        abrigoRepository.save(new Abrigo(dto));
    }

    public List<PetDto> listarPetsAbrigo(String idOutNome) {
        Abrigo abrigo = carregarAbrigo(idOutNome);

        return petRepository
                .findByAbrigo(abrigo)
                .stream()
                .map(PetDto::new)
                .toList();
    }

    public Abrigo carregarAbrigo(String idOutNome) {
        Optional<Abrigo> optional;
        try {
            Long id = Long.parseLong(idOutNome);
            optional = abrigoRepository.findById(id);
        } catch (NumberFormatException exception) {
            //optional = abrigoRepository.findByNome(idOutNome);
            optional = abrigoRepository.findByNome(idOutNome) ;
        }

        return optional.orElseThrow(() -> new ValidacaoException("Abrigo não encontrado!"));

    }
}
