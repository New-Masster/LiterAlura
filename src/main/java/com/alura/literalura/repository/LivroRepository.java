package com.alura.literalura.repository;

import com.alura.literalura.model.Livro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class LivroRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Livro livro) {
        entityManager.persist(livro);
    }

    public List<Livro> findAll() {
        return entityManager.createQuery("SELECT l FROM Livro l", Livro.class)
                .getResultList();
    }

    public List<Livro> findByIdioma(String idioma) {
        return entityManager.createQuery("SELECT l FROM Livro l WHERE l.idioma = :idioma", Livro.class)
                .setParameter("idioma", idioma)
                .getResultList();
    }

    public int countByIdioma(String idioma) {
        return entityManager.createQuery("SELECT COUNT(l) FROM Livro l WHERE l.idioma = :idioma", Long.class)
                .setParameter("idioma", idioma)
                .getSingleResult()
                .intValue();
    }
}
