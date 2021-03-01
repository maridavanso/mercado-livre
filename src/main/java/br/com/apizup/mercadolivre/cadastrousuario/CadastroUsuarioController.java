package br.com.apizup.mercadolivre.cadastrousuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CadastroUsuarioController {
	
	@PersistenceContext
	private EntityManager manager;
	
	/*
	 * @Autowired private ProibeEmailDuplicado proibeEmailDuplicado;
	 * 
	 * @InitBinder public void init(WebDataBinder binder) {
	 * binder.addValidators(proibeEmailDuplicado); }
	 */
	
	@PostMapping(value = "/usuarios")
	@Transactional
	public String cria(@Valid @RequestBody NovoUsuarioRequest request) {
		Usuario novoUsuario = request.toUsuario();
		manager.persist(novoUsuario);
		return novoUsuario.toString();
	}

}
