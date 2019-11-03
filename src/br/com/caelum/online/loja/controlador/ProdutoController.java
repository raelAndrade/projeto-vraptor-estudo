package br.com.caelum.online.loja.controlador;

import java.util.List;

import br.com.caelum.online.loja.dominio.Produto;
import br.com.caelum.online.loja.repositorio.RepositorioDeProdutos;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import br.com.caelum.vraptor.view.Results;

@Resource
public class ProdutoController {
	
	private final Result result;
	private final RepositorioDeProdutos produtos;
	private Validator validator;

	public ProdutoController(RepositorioDeProdutos produtos, Result result, Validator validator) {
		this.result = result;
		this.produtos = produtos;
		this.validator = validator;
	}
	
	public void formulario() { }
	
	@Post
	public void adiciona(final Produto produto) {
		// Uma forma de validação dos campos
		/*if(produto.getPreco() < 0.1) {
			validator.add(new ValidationMessage("O preço dever ser maior do que R$ 0.1", "preco"));
		}*/
		
		// Outra forma de validar campos com o vraptor
		/* O objeto validator possui um método checking() que recebe um objeto especializado que encapsula 
		 * a regra de validação. Esse objeto é do tipo Validations
		 */
		validator.checking(new Validations() {{
			that(produto.getPreco() > 0.1, "preco", "produto.preco.inválido");
			that(produto.getDescricao() != null && produto.getDescricao().length() <= 0, "descricao",  "produto.descricao.invalido");
			that(produto.getNome() != null && produto.getNome().length() >= 3 && produto.getNome().length() <= 100, "nome",  "produto.nome.invalido");
		}});
		
		validator.onErrorUsePageOf(ProdutoController.class).formulario();
		
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
	
	public void remove(Produto produto) {
		produtos.remove(produto);
		result.nothing();
	}
}
