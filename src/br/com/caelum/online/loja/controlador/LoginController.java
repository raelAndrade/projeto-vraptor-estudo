package br.com.caelum.online.loja.controlador;

import br.com.caelum.online.loja.dominio.UsuarioLogado;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class LoginController {
	
	private final UsuarioLogado usuarioLogado;
	private final Result result;
	
	public LoginController(UsuarioLogado usuarioLogado, Result result) {
		this.usuarioLogado = usuarioLogado;
		this.result = result;
	}
	
	private void formulario() {	}
	
	public void loga(String login, String senha) {
		if(login.equals("israel") && senha.equals("123")) {
			this.usuarioLogado.setLogin("israel");
			result.redirectTo(ProdutoController.class).lista();
		}else{
			result.redirectTo(LoginController.class).formulario();
		}
	}
	

	public void exibe() {
		result.include("usuario",usuarioLogado.getLogin());
	}
}
