package br.edu.academico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.academico.entities.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

}
