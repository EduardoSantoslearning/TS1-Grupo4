package com.Grupo4.editar_meus_dados.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Grupo4.editar_meus_dados.model.Informacoes;
import com.Grupo4.editar_meus_dados.model.Usuario;
import com.Grupo4.editar_meus_dados.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
    }

    @Transactional
    public Usuario editarDadosUsuario(Long id, byte[] foto, String emailSecundario, String telefone, String curriculoLattes) {
        Usuario usuario = buscarUsuarioPorId(id);
        usuario.setFoto(foto);
        Informacoes informacoes = usuario.getInformacoes();
        if (informacoes == null) {
            informacoes = new Informacoes();
            usuario.setInformacoes(informacoes);
        }
        informacoes.setEmailSecundario(emailSecundario);
        informacoes.setTelefone(telefone);
        informacoes.setCurriculoLattes(curriculoLattes);
        return usuarioRepository.save(usuario);
    }
}
