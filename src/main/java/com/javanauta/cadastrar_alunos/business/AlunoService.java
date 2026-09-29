package com.javanauta.cadastrar_alunos.business;

import com.javanauta.cadastrar_alunos.infrastructure.entitys.Aluno;
import com.javanauta.cadastrar_alunos.infrastructure.repository.AlunoRepository;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }
    public void salvarAluno(Aluno aluno){
        repository.saveAndFlush(aluno);
    }

    public Aluno buscarAlunoPorEmail(String email) {
        return repository.findByEmail(email).orElseThrow(
          () -> new RuntimeException("Email não encontrado")
        );
  }

  public void
}

