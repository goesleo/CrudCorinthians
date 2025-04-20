package com.example.myprojectcorinthians.controller;
import com.example.myprojectcorinthians.entity.Jogador;
import com.example.myprojectcorinthians.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogador")
public class JogadorController {
    @Autowired
    private JogadorRepository jogadorRepository;

    @PostMapping
    public Jogador criarJogador(@RequestBody Jogador jogador) {
        return jogadorRepository.save(jogador);
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
    public Jogador atualizarJogador(@RequestBody Jogador novoJogador, @PathVariable Long id) {
        return jogadorRepository.findById(id).map(jogador -> {
            jogador.setNome(novoJogador.getNome());
            jogador.setPosicao(novoJogador.getPosicao());
            jogador.setId(novoJogador.getId());
            jogador.setNumeroCamisa(novoJogador.getNumeroCamisa());
            jogador.setSalario(novoJogador.getSalario());
            return jogadorRepository.save(jogador);
        }).orElseThrow(() -> new RuntimeException("Jogador não encontrado com o id: " + id));
    }

    @DeleteMapping("/{id}")
    public void deletarJogador(@PathVariable Long id) {
        jogadorRepository.deleteById(id);
    }




}
