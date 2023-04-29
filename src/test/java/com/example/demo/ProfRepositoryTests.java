package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)


public class ProfRepositoryTests {

	@Autowired
	private ProfRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateProfessor() {
		Professor prof = new Professor();
		prof.setPID("PES03");
		prof.setPassword("kap");
		
		
		Professor savedProf = repo.save(prof);
		
		Professor existProf = entityManager.find(Professor.class, savedProf.getId());
		
		assertThat(existProf.getPID()).isEqualTo(prof.getPID()); 
	}
	
	@Test
	public void testFindProfessorByPID() {
		String pid = "PES123";
		Professor prof = repo.findByPID(pid);
		assertThat(prof).isNotNull();
		}
	
	
	
}