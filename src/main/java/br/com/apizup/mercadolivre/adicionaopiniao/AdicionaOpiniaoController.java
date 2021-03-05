package br.com.apizup.mercadolivre.adicionaopiniao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.apizup.mercadolivre.cadastroprodutos.Produto;
import br.com.apizup.mercadolivre.cadastrousuario.Usuario;
import br.com.apizup.mercadolivre.compartilhado.UsuarioLogado;

@RestController
public class AdicionaOpiniaoController {

	@PersistenceContext
	private EntityManager manager;

	@PostMapping(value = "/produtos/{id}/opiniao")
	@Transactional
	// 1
	public String adiciona(@RequestBody @Valid NovaOpiniaoRequest request,
			@PathVariable("id") Long id,@AuthenticationPrincipal UsuarioLogado usuarioLogado ) {
		// 1
		Produto produto = manager.find(Produto.class, id);
		// 1
		Usuario consumidor = usuarioLogado.get();
		// 1
		Opiniao novaOpiniao = request.toModel(produto, consumidor);
		manager.persist(novaOpiniao);

		return novaOpiniao.toString();
	}

}