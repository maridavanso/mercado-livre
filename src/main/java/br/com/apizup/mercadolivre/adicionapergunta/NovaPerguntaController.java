package br.com.apizup.mercadolivre.adicionapergunta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import br.com.apizup.mercadolivre.cadastroprodutos.Produto;
import br.com.apizup.mercadolivre.cadastrousuario.Usuario;
import br.com.apizup.mercadolivre.cadastrousuario.UsuarioRepository;

@RestController
public class NovaPerguntaController {

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	//1
	private UsuarioRepository usuarioRepository;
	//1
	@Autowired
	private Emails emails;	

	@PostMapping(value = "/produtos/{id}/perguntas")
	@Transactional
	//1
	public String cria(@RequestBody @Valid NovaPerguntaRequest request, @PathVariable("id") Long id) {
		//1
		Produto produto = manager.find(Produto.class,id);
		//1
		Usuario interessada = usuarioRepository.findByEmail("davanso001@gmail.com").get();
		
		//1
		Pergunta novaPergunta = request.toModel(interessada,produto);
		manager.persist(novaPergunta);
		
		emails.novaPergunta(novaPergunta);
		
		return novaPergunta.toString();
	}

}