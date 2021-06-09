package br.com.fullstack.dao;



import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.fullstack.model.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario, Integer> {
	
	//DAO (É UM PATTERN - CRUD em uma classe)
	//Gravar - Consultar - Excluir - Alterar
	
	
	
	
	
	public List<Usuario> findByNome(String nome);
	
	Usuario findByEmailAndSenha(String email, String senha);
	
	
	

	
	
	
	
	
	
	
	
	
	
	
}
