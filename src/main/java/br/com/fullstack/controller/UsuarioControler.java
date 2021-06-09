package br.com.fullstack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fullstack.dao.UsuarioDAO;
import br.com.fullstack.model.Usuario;

@RestController //para que a classe responda as requisições HTTP
@CrossOrigin("*") //permite para que a controller receba requisições externas externas (fora do server)
public class UsuarioControler {
	
	@Autowired // transfere para o Springboot a responsabilidade sobre este objeto
	private UsuarioDAO dao;
	
	@PostMapping("/nome")
	public ResponseEntity<Usuario> respondernome(@RequestBody Usuario nome){
		Usuario resposta = (Usuario) dao.findByNome(nome.getNome());
		if(resposta==null) {
			return ResponseEntity.status(404).build();
		}
		resposta.setNome("nome");
		return ResponseEntity.ok(resposta);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Usuario> logar(@RequestBody Usuario usuario){
		Usuario resposta = dao.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
		if(resposta==null ) {
			return ResponseEntity.status(404).build();
		}
		resposta.setSenha("");
		return ResponseEntity.ok(resposta);
		
	}
	
	@PostMapping("/novousuario")
	
	public ResponseEntity<Usuario> add(@RequestBody Usuario usuario){
		try {
			dao.save(usuario);
			return ResponseEntity.ok(usuario);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(403).build();
			
		}
		
	}
	
	@GetMapping("/usuario/{var}")
	public ResponseEntity<Usuario> getUser(@PathVariable int var){
		Usuario resposta = dao.findById(var).orElse(null);
		if (resposta == null) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(resposta);
	}
	
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> getAll(){
		List<Usuario> lista = (List<Usuario>) dao.findAll();
		if (lista.size()==0) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(lista);
	}
	
	
	
	
	
	

}
