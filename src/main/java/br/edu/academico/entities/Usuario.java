package br.edu.academico.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idusuario;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sexo", nullable = false)
    private String sexo;

    @Column(name = "dt_nasc", nullable = false)
    private Date dt_nasc;

    @JsonIgnore
    @ManyToMany(mappedBy = "listaUsuario")
    private List<Curso> listaCurso = new ArrayList<>();

    @ManyToMany(mappedBy = "listaUsuario")
    private List<Disciplina> listaDisciplina = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(Integer idusuario, String nome, String sexo, Date dt_nasc) {
        this.idusuario = idusuario;
        this.nome = nome;
        this.sexo = sexo;
        this.dt_nasc = dt_nasc;
    }

    public List<Curso> getListaCurso() {
        return listaCurso;
    }

    public void adicionarCurso(Curso c) {
        if (!listaCurso.contains(c)) {
            listaCurso.add(c);
            c.adicionarUsuario(this);
        }
    }

    public List<Disciplina> getListaDisciplina() {
        return listaDisciplina;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        if (!listaDisciplina.contains(disciplina)) {
            listaDisciplina.add(disciplina);
            disciplina.adicionarUsuario(this);
        }
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDt_nasc() {
        return dt_nasc;
    }

    public void setDt_nasc(Date dt_nasc) {
        this.dt_nasc = dt_nasc;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dt_nasc, idusuario, nome, sexo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(idusuario, other.idusuario)
                && Objects.equals(nome, other.nome)
                && Objects.equals(sexo, other.sexo)
                && Objects.equals(dt_nasc, other.dt_nasc);
    }

    @Override
    public String toString() {
        return "Usuario [idusuario=" + idusuario + ", nome=" + nome + ", sexo=" + sexo + ", dt_nasc=" + dt_nasc + "]";
    }
}
