package br.com.apizup.mercadolivre.cadastrousuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CadastroUsuarioController {
	
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private ProibeEmailDuplicado proibeUsuarioComEmailDuplicado;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(proibeUsuarioComEmailDuplicado);
	}
	
	@PostMapping(value = "/usuarios")
	@Transactional
	public String cria(@RequestBody @Valid NovoUsuarioRequest request) {
		Usuario novoUsuario = request.toUsuario();
		manager.persist(novoUsuario);
		return novoUsuario.toString();
	}

}