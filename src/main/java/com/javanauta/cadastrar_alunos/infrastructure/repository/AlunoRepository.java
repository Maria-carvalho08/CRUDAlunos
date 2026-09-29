package com.javanauta.cadastrar_alunos.infrastructure.repository;

import com.javanauta.cadastrar_alunos.infrastructure.entitys.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Integer>
{
     Optional<Aluno> findByEmail(String email);
}
