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
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcurso;

    @Column(name = "nomecurso", nullable = false)
    private String nomecurso;

    @ManyToMany
    @JoinTable(name = "usuario_curso",
            joinColumns = @JoinColumn(name = "idcurso"),
            inverseJoinColumns = @JoinColumn(name = "idusuario"))
    private List<Usuario> listaUsuario = new ArrayList<>();

    @ManyToMany(mappedBy = "listaCurso")
    private List<Disciplina> listaDisciplina = new ArrayList<>();

    public Curso() {
    }

    public Curso(Integer idcurso, String nomecurso) {
        this.idcurso = idcurso;
        this.nomecurso = nomecurso;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void adicionarUsuario(Usuario usuario) {
        if (!listaUsuario.contains(usuario)) {
            listaUsuario.add(usuario);
            usuario.adicionarCurso(this);
        }
    }

    public List<Disciplina> getListaDisciplina() {
        return listaDisciplina;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        if (!listaDisciplina.contains(disciplina)) {
            listaDisciplina.add(disciplina);
            disciplina.adicionarCurso(this);
        }
    }

    public Integer getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(Integer idcurso) {
        this.idcurso = idcurso;
    }

    public String getNomecurso() {
        return nomecurso;
    }

    public void setNomecurso(String nomecurso) {
        this.nomecurso = nomecurso;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcurso, nomecurso);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Curso other = (Curso) obj;
        return Objects.equals(idcurso, other.idcurso)
                && Objects.equals(nomecurso, other.nomecurso);
    }

    @Override
    public String toString() {
        return "Curso [idcurso=" + idcurso + ", nomecurso=" + nomecurso + "]";
    }
}
