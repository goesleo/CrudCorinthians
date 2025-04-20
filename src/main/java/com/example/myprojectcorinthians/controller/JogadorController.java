package com.example.myprojectcorinthians.controller;
import com.example.myprojectcorinthians.entity.Jogador;
import com.example.myprojectcorinthians.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogador")
public class JogadorController {
    @Autowired
    private JogadorRepository jogadorRepository;

    @PostMapping
    public ResponseEntity<String> criarJogador(@RequestBody Jogador jogador) {
        Jogador salvo = jogadorRepository.save(jogador);
        return ResponseEntity.ok("Jogador " + salvo.getNome() + " cadastrado com sucesso!");
    }

    @GetMapping
    public List<Jogador> listarJogador() {
        return jogadorRepository.findAll();
    }

    @GetMapping("/id/{id}")
    public Jogador buscarporId(@PathVariable Long id) {
        return jogadorRepository.findById(id)
     .orElseThrow(() -> new RuntimeException("Jogador não encontrado com o id: " + id));
    }

    @GetMapping("/posicao/{posicao}")
    public List<Jogador> buscarPorPosicao(@PathVariable String posicao){
        return jogadorRepository.findByPosicaoIgnoreCase(posicao);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarJogador(@RequestBody Jogador novoJogador, @PathVariable Long id) {
        return jogadorRepository.findById(id).map(jogador -> {
            jogador.setNome(novoJogador.getNome());
            jogador.setPosicao(novoJogador.getPosicao());
            jogador.setNumeroCamisa(novoJogador.getNumeroCamisa());
            jogador.setSalario(novoJogador.getSalario());
            jogadorRepository.save(jogador);
            return ResponseEntity.ok("Jogador " + jogador.getNome() + " atualizado com sucesso!");
        }).orElseThrow(() -> new RuntimeException("Jogador não encontrado com o id: " + id));
    }

    @DeleteMapping("/{id}")
    public void deletarJogador(@PathVariable Long id) {
        jogadorRepository.deleteById(id);
    }




}
