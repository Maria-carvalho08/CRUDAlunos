package com.javanauta.cadastrar_alunos.controller;

import com.javanauta.cadastrar_alunos.business.AlunoService;
import com.javanauta.cadastrar_alunos.infrastructure.entitys.Aluno;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
public class AlunoController {
    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Void> salvarAluno(@RequestBody Aluno aluno){
        alunoService.salvarAluno(aluno);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Aluno> buscarAlunoPorEmail(@RequestParam String email){
        return ResponseEntity.ok(alunoService.buscarAlunoPorEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarAlunoPorEmail(@RequestParam String email){
        alunoService.deletarAlunoPorEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarAlunoPorEmail (@RequestBody Aluno aluno,
                                                        @RequestParam String email){
        alunoService.atualizarAlunoPorEmail(email,aluno);
        return ResponseEntity.ok().build();
    }
}
