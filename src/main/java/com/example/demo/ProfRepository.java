package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProfRepository extends JpaRepository<Professor, Long> {
	@Query("SELECT u FROM Professor u WHERE u.pid = ?1")
	Professor findByPID(String pid);
	
	@Modifying
	@Query("DELETE FROM Professor u WHERE u.pid = ?1")
	void deleteProfessor(String pid);
}
