package com.example.myprojectcorinthians.repository;

import com.example.myprojectcorinthians.entity.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface JogadorRepository extends JpaRepository<Jogador, Long> {

    List<Jogador> findByPosicaoIgnoreCase(String posicao);
}
