package com.Grupo4.editar_meus_dados.repository;

import com.Grupo4.editar_meus_dados.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u JOIN FETCH u.informacoes WHERE u.id = :id")
    Optional<Usuario> findByIdWithInformacoes(@Param("id") Long id);

}