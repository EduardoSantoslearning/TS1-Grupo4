package br.edu.academico.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.edu.academico.entities.Curso;
import br.edu.academico.entities.Disciplina;
import br.edu.academico.repositories.CursoRepository;
import br.edu.academico.repositories.DisciplinaRepository;

@Configuration
public class Config implements CommandLineRunner{
	
	@Autowired
	private CursoRepository cursoRep;
	
	@Override
	public void run(String... args) throws Exception {
		
		//Curso c1 = new Curso(null,"Spring");
		//Curso c2 = new Curso(null,"RestFull");
		
		//cursoRep.save(c1);
		//cursoRep.save(c2);
		System.out.println("Qtde Cursos: " +  cursoRep.count());
	}
	
}
