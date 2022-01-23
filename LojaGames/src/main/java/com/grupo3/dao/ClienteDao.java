package com.grupo3.dao;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.grupo3.Model.Cliente;

@Repository
public interface ClienteDao extends JpaRepository<Cliente, Integer> {
	@Query("select u from Cliente u")
    public Page<Cliente> AllCliente(Pageable pagReq);
	
	public List<Cliente> findByNomeContainingIgnoreCase(String nome);
	
	@Query("select u from Cliente u where u.email = :email AND u.senha = :senha")
	public Cliente findByLogin(String email, String senha);
	
	@Query("select u from Cliente u where u.id = :id")
	public Cliente EncontraCliId(int id);
	
	
	@Query("select u from Cliente u where u.email = :email")
	public Cliente buscaEmail(String email);
	
	@Transactional
	@Modifying
	@Query("update Cliente u set u.atividade = :atividade where u.email = :email AND u.senha = :senha")
	public void logado(String email, String senha,String atividade);

}
