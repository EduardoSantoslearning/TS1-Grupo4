package br.edu.academico.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Disciplina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iddisciplina;

    @Column(name = "nomedisciplina", nullable = false)
    private String nomedisciplina;

    @Column(name = "cargahoraria", nullable = false)
    private String cargahoraria;

    @ManyToMany
    @JoinTable(name = "curso_disciplina",
            joinColumns = @JoinColumn(name = "iddisciplina"),
            inverseJoinColumns = @JoinColumn(name = "idcurso"))
    private List<Curso> listaCurso = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "usuario_disciplina",
            joinColumns = @JoinColumn(name = "iddisciplina"),
            inverseJoinColumns = @JoinColumn(name = "idusuario"))
    private List<Usuario> listaUsuario = new ArrayList<>();

    public Disciplina() {
    }

    public Disciplina(Integer iddisciplina, String nomedisciplina, String cargahoraria) {
        this.iddisciplina = iddisciplina;
        this.nomedisciplina = nomedisciplina;
        this.cargahoraria = cargahoraria;
    }

    public Integer getIddisciplina() {
        return iddisciplina;
    }

    public void setIddisciplina(Integer iddisciplina) {
        this.iddisciplina = iddisciplina;
    }

    public String getNomedisciplina() {
        return nomedisciplina;
    }

    public void setNomedisciplina(String nomedisciplina) {
        this.nomedisciplina = nomedisciplina;
    }

    public String getCargahoraria() {
        return cargahoraria;
    }

    public void setCargahoraria(String cargahoraria) {
        this.cargahoraria = cargahoraria;
    }

    public List<Curso> getListaCurso() {
        return listaCurso;
    }

    public void adicionarCurso(Curso curso) {
        if (!listaCurso.contains(curso)) {
            listaCurso.add(curso);
            curso.adicionarDisciplina(this);
        }
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void adicionarUsuario(Usuario usuario) {
        if (!listaUsuario.contains(usuario)) {
            listaUsuario.add(usuario);
            usuario.adicionarDisciplina(this);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(iddisciplina, nomedisciplina, cargahoraria);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Disciplina other = (Disciplina) obj;
        return Objects.equals(iddisciplina, other.iddisciplina)
                && Objects.equals(nomedisciplina, other.nomedisciplina)
                && Objects.equals(cargahoraria, other.cargahoraria);
    }

    @Override
    public String toString() {
        return "Disciplina [iddisciplina=" + iddisciplina + ", nomedisciplina=" + nomedisciplina
                + ", cargahoraria=" + cargahoraria + "]";
    }
}
