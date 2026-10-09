package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {

    boolean  exiistByPetIdAndStatus(Long idPet, StatusAdocao status);

    @Query("""
            SELECT COUNT(a)
            from Adocao a
            WHERE
            a.tutor.id = :id
            AND a.status = :status
            """)
    int qtdAdocao(
            @Param("id") Long id,
            @Param("status") StatusAdocao status
    );

    int countByTutorIdAndStatus(Long id, StatusAdocao status);
}
