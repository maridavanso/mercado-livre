package br.com.apizup.mercadolivre.adicionapergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.apizup.mercadolivre.cadastroprodutos.Produto;
import br.com.apizup.mercadolivre.cadastrousuario.Usuario;

public class NovaPerguntaRequest {

	@NotBlank
	private String titulo;
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public String toString() {
		return "NovaPerguntaRequest [titulo=" + titulo + "]";
	}

	public Pergunta toModel(@NotNull @Valid Usuario interessada,
			@NotNull @Valid Produto produto) {
		return new Pergunta(titulo, interessada, produto);
	}

}
