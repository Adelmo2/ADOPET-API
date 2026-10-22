package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.StatusAdocao;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {
    boolean existsByNome(String nome);

    boolean existsByTelefone(String telefone);

    boolean existsByEmail(String email);

    Optional<Abrigo> findByNome(String nome);

    List<PetDto> findById(long id);

    @Query("""
            SELECT a
            from Abrigo a
            WHERE
            a.nome = :nome
            """
    )
    List<PetDto> procuraPorNome(String nome);

    boolean existsByNomeOrTelefoneOrEmail(String nome, String telefone, String email);
}
