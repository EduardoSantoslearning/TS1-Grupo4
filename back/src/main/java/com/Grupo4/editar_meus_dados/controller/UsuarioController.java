package com.Grupo4.editar_meus_dados.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.Grupo4.editar_meus_dados.model.Usuario;
import com.Grupo4.editar_meus_dados.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint para buscar um usuário pelo ID.
     * GET /api/usuarios/{id}
     *
     * @param id O ID do usuário a ser buscado.
     * @return ResponseEntity com o objeto Usuario se encontrado, ou 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
        	
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint para editar os dados de um usuário existente.
     * PUT /api/usuarios/{id}
     *
     * @param id O ID do usuário a ser editado.
     * @param nome Novo nome do usuário (opcional, pode ser mantido o atual se não alterado).
     * @param email Novo email principal (opcional).
     * @param userName Novo nome de usuário (opcional).
     * @param emailSecundario Novo email secundário (opcional).
     * @param telefone Novo telefone (opcional).
     * @param curriculoLattes Novo link para currículo Lattes (opcional).
     * @param foto Arquivo da nova foto (opcional).
     * @return ResponseEntity com o usuário atualizado e status 200 OK, ou status de erro.
     */
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Usuario> editarUsuario(
            @PathVariable Long id,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String emailSecundario,
            @RequestParam(required = false) String telefone,
            @RequestParam(required = false) String curriculoLattes,
            @RequestParam(value = "foto", required = false) MultipartFile foto) {
        try {

            Usuario usuarioExistente = usuarioService.buscarUsuarioPorId(id);

            if (nome != null) {
                usuarioExistente.setNome(nome);
            }
            if (email != null) {
                usuarioExistente.setEmail(email);
            }
            if (userName != null) {
                usuarioExistente.setUserName(userName);
            }

            byte[] fotoBytes = null;
            if (foto != null && !foto.isEmpty()) {
                fotoBytes = foto.getBytes();
                usuarioExistente.setFoto(fotoBytes);
            }

            Usuario usuarioAtualizado = usuarioService.editarDadosUsuario(
                    id,
                    fotoBytes,
                    emailSecundario,
                    telefone,
                    curriculoLattes
            );

            if (usuarioAtualizado.getInformacoes() != null) {
                 usuarioAtualizado.getInformacoes().setUltimoAcesso(LocalDateTime.now());
            }


            usuarioService.salvarUsuario(usuarioAtualizado);

            return ResponseEntity.ok(usuarioAtualizado);

        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IOException e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}