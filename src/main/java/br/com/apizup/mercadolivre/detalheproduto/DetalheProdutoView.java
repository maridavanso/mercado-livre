package br.com.apizup.mercadolivre.detalheproduto;

import java.math.BigDecimal;
import java.util.Map;

import java.util.Set;
import java.util.SortedSet;

import br.com.apizup.mercadolivre.cadastroprodutos.Opinioes;
import br.com.apizup.mercadolivre.cadastroprodutos.Produto;

public class DetalheProdutoView {

	private String descricao;
	private String nome;
	private BigDecimal preco;
	//1
	private Set<DetalheProdutoCaracteristica> caracteristicas;
	private Set<String> linksImagens;
	private SortedSet<String> perguntas;
	private Set<Map<String,String>> opinioes;
	private double mediaNotas;
	private int total;

	public DetalheProdutoView(Produto produto) {
		this.descricao = produto.getDescricao();
		this.nome = produto.getNome();
		this.preco = produto.getValor();
		//1
		this.caracteristicas = produto
				.mapeiaCaracteristicas(DetalheProdutoCaracteristica::new);
		//1
		this.linksImagens = produto.mapeiaImagens(imagem -> imagem.getLink());
		//1
		this.perguntas = produto.mapeiaPerguntas(pergunta -> pergunta.getTitulo());
		
		//1
		Opinioes opinioes = produto.getOpinioes();		
		//1
		this.opinioes = opinioes.mapeiaOpinioes(opiniao -> {
			return Map.of("titulo",opiniao.getTitulo(),"descricao",opiniao.getDescricao());
		});
		

		this.mediaNotas = opinioes.media();
		this.total = opinioes.total();
				
		

	}
	
	public int getTotal() {
		return total;
	}
	
	public double getMediaNotas() {
		return mediaNotas;
	}
	
	public Set<Map<String, String>> getOpinioes() {
		return opinioes;
	}
	
	public SortedSet<String> getPerguntas() {
		return perguntas;
	}
	
	public Set<String> getLinksImagens() {
		return linksImagens;
	}
	
	public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

}