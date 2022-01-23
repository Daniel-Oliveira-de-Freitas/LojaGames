package com.grupo3.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grupo3.Model.Compra;
import com.grupo3.Model.Jogo;



@Repository
public interface CompraDao extends JpaRepository<Compra, Integer> {
	@Query("select u from Compra u")
    public Page<Compra> AllCompras(Pageable pagReq);

}
