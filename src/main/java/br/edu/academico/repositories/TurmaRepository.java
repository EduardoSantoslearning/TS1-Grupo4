package br.edu.academico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.academico.entities.Usuario;

@Repository
public interface TurmaRepository extends JpaRepository<Usuario, Integer>{

}
