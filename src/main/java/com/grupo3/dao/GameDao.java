package com.grupo3.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grupo3.Model.Jogo;

@Repository
public interface GameDao extends JpaRepository<Jogo, Integer> {
	@Query("select u from Jogo u")
    public Page<Jogo> AllJogos(Pageable pagReq);
	
	public List<Jogo> findByNomeContainingIgnoreCase(String nome);
	
	@Query("select u from Jogo u where u.id = :id")
	public Jogo EncontrePeloID(int id);
}
