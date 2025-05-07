package br.edu.academico.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String codigo; // INF0056-A
    private String horario;
    private Integer cargaHoraria;

    @ManyToOne
    private Disciplina disciplina;

    @ManyToOne
    private NucleoConhecimento nucleo;

    @ManyToOne
    private Usuario docenteResponsavel;
}
