package com.Grupo4.editar_meus_dados.repository;

import com.Grupo4.editar_meus_dados.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}