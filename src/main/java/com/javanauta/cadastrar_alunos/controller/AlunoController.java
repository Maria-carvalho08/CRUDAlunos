
package com.javanauta.cadastrar_alunos.controller;

import com.javanauta.cadastrar_alunos.business.AlunoService;
import com.javanauta.cadastrar_alunos.infrastructure.entitys.Aluno;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    // CADASTRAR ALUNO
    @PostMapping
    public ResponseEntity<Void> salvarAluno(@RequestBody Aluno aluno) {
        alunoService.salvarAluno(aluno);
        return ResponseEntity.ok().build();
    }

    // BUSCAR ALUNO PELO EMAIL
    @GetMapping
    public ResponseEntity<Aluno> buscarAlunoPorEmail(
            @RequestParam String email) {

        return ResponseEntity.ok(
                alunoService.buscarAlunoPorEmail(email)
        );
    }

    // EXCLUIR ALUNO PELO EMAIL
    @DeleteMapping
    public ResponseEntity<Void> deletarAlunoPorEmail(
            @RequestParam String email) {

        alunoService.deletarAlunoPorEmail(email);
        return ResponseEntity.ok().build();
    }

    // ATUALIZAR ALUNO PELO EMAIL
    @PutMapping
    public ResponseEntity<Void> atualizarAlunoPorEmail(
            @RequestBody Aluno aluno,
            @RequestParam String email) {

        alunoService.atualizarAlunoPorEmail(email, aluno);
        return ResponseEntity.ok().build();
    }
}
