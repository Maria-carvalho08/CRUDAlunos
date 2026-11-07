
package com.javanauta.cadastrar_alunos.business;

import com.javanauta.cadastrar_alunos.infrastructure.entitys.Aluno;
import com.javanauta.cadastrar_alunos.infrastructure.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository repository;

    // CADASTRAR ALUNO
    public void salvarAluno(Aluno aluno) {
        repository.saveAndFlush(aluno);
    }

    // BUSCAR ALUNO PELO EMAIL
    public Aluno buscarAlunoPorEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Email não encontrado"));
    }

    // EXCLUIR ALUNO PELO EMAIL
    public void deletarAlunoPorEmail(String email) {
        repository.deleteByEmail(email);
    }

    // ATUALIZAR ALUNO PELO EMAIL
    public void atualizarAlunoPorEmail(String email, Aluno aluno) {

        Aluno alunoExistente = buscarAlunoPorEmail(email);

        if (aluno.getNome() != null && !aluno.getNome().isBlank()) {
            alunoExistente.setNome(aluno.getNome());
        }

        if (aluno.getEmail() != null && !aluno.getEmail().isBlank()) {
            alunoExistente.setEmail(aluno.getEmail());
        }

        if (aluno.getNota() != null) {
            alunoExistente.setNota(aluno.getNota());
        }

        repository.saveAndFlush(alunoExistente);
    }
}
