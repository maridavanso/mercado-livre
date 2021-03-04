package br.com.apizup.mercadolivre.cadastroprodutos;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.apizup.mercadolivre.cadastrousuario.Usuario;
import br.com.apizup.mercadolivre.cadastrousuario.UsuarioRepository;

@RestController
public class ProdutosController {

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	//1
	private UsuarioRepository usuarioRepository;
	@Autowired
	//1
	private Uploader uploaderFake;
	
	@InitBinder(value = "novoProdutoRequest")
	public void init(WebDataBinder webDataBinder) {
		//1
		webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgual());
	}

	@PostMapping(value = "/produtos")
	@Transactional
	//1
	public String cria(@RequestBody @Valid NovoProdutoRequest request) {
		//simulando o usuario logado
		//1
		Usuario dono = usuarioRepository.findByEmail("davanso001@gmail.com").get();		
		//1
		Produto produto = request.toModel(manager,dono);
		
		manager.persist(produto);
		return produto.toString();
	}
	
	@PostMapping(value = "/produtos/{id}/imagens")
	@Transactional
	//1
	public String adicionaImagens(@PathVariable("id") Long id,@Valid NovasImagensRequest request) {
		
		
		Usuario dono = usuarioRepository.findByEmail("davanso001@gmail.com").get();		
		Produto produto = manager.find(Produto.class, id);
		
		if(!produto.pertenceAoUsuario(dono)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		
		Set<String> links = uploaderFake.envia(request.getImagens());
		produto.associaImagens(links);
		
		manager.merge(produto);
		
		return produto.toString();
		
	}


}