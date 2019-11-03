package br.com.caelum.online.loja.controlador;

import java.util.List;

import br.com.caelum.online.loja.dao.ProdutoDao;
import br.com.caelum.online.loja.dominio.Produto;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class ProdutoController {
	
	private final ProdutoDao produtos;
	private final Result result;

	public ProdutoController(ProdutoDao produtos, Result result) {
		this.result = result;
		this.produtos = produtos;
	}
	
	public void formulario() { }
	
	@Post
	public void adiciona(Produto produto) {
		this.produtos.salva(produto);
		result.include("mensagem", "Novo produto adicionado com sucesso!");
		result.redirectTo(ProdutoController.class).lista();
	}

	public List<Produto> lista() {
		List<Produto> produtos = this.produtos.pegaTodos();
		return produtos;
	}
	
	@Path("/produto/{id}")
	public Produto exibe(Long id) {
		Produto produto = this.produtos.pegaPorId(id); 
		return produto;
	}
	
	@Path("/produto/{id}/xml")
	public void exibeComXML(Long id) {
		Produto produto = this.produtos.pegaPorId(id); 
		result.use(Results.xml()).from(produto).serialize();
	}
	
	@Path("/produto/{id}/json")
	public void exibeComJSON(Long id) {
		Produto produto = this.produtos.pegaPorId(id); 
		result.use(Results.json()).from(produto).serialize();
	}
}
