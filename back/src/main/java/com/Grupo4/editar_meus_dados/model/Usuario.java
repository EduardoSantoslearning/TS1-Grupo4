package com.Grupo4.editar_meus_dados.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String userName;

    private LocalDate dataIngresso;

    @Lob
    @Column(columnDefinition = "BYTEA")
    private byte[] foto;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false, orphanRemoval = true)
    @JoinColumn(name = "informacoes_id", unique = true)
    private Informacoes informacoes;

    // Constructors (*****)
    public Usuario() {
    }

    public Usuario(String nome, String email, String userName, LocalDate dataIngresso, byte[] foto, Informacoes informacoes) {
        this.nome = nome;
        this.email = email;
        this.userName = userName;
        this.dataIngresso = dataIngresso;
        this.foto = foto;
        this.informacoes = informacoes;
    }

    // Getters e Setters (*****)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(LocalDate dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Informacoes getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(Informacoes informacoes) {
        this.informacoes = informacoes;
    }
}