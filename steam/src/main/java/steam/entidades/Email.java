package steam.entidades;

import java.util.ArrayList;

public class Email {
	//Atributos
	private Integer idEmail;
	private Integer idAutor;
	ArrayList<Cadastro> destinatarios;
	private String assunto;
	private String mensagem;
	private String dataEmail;
	//Métodos construtores 
	public Email(Integer idEmail, Integer idAutor, String assunto, String mensagem, String dataEmail) {
		super();
		this.idEmail = idEmail;
		this.idAutor = idAutor;
		this.destinatarios = new ArrayList<Cadastro>();
		this.assunto = assunto;
		this.mensagem = mensagem;
		this.dataEmail = dataEmail;
	}
	//Sobrecarga do método onde neste ele não recebe o assunto
	public Email(Integer idEmail, Integer idAutor, String mensagem, String dataEmail) {
		super();
		this.idEmail = idEmail;
		this.idAutor = idAutor;
		this.destinatarios = new ArrayList<Cadastro>();
		this.assunto = null;
		this.mensagem = mensagem;
		this.dataEmail = dataEmail;
	}
	//Métodos para adicionar destinatários ao email, onde destinatários poderam também visualizar este email
	public void adicionarDestinatarios(Cadastro destinatario) {
		for(int indice = 0; indice < destinatarios.size(); indice++) 
			if(destinatarios.get(indice).getNomeUsuario().contentEquals(destinatario.getNomeUsuario()))
				return;
			destinatarios.add(destinatario);
	}
	//Listar destinatários
	public void listarDestinatarios() {
		for(int indice = 0; indice < destinatarios.size(); indice++) {
			System.out.printf("Nome de usuario: %s\nNome: %s\n",destinatarios.get(indice).getNomeUsuario(),destinatarios.get(indice).getNome());
		}
	}
	//Remover destinatários
	public void removerDestinatarios(Cadastro destinatario) {
		for(int indice = 0; indice < destinatarios.size(); indice++) {
			if(destinatarios.get(indice).getNomeUsuario().contentEquals(destinatario.getNomeUsuario())) {
				destinatarios.remove(indice);
				return;
			}
		}
	}
	//Métodos de acesso geters e seters
	public ArrayList<Cadastro> getDestinatarios() {
		return destinatarios;
	}
	public void setDestinatarios(ArrayList<Cadastro> destinatarios) {
		this.destinatarios = destinatarios;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getDataMensagem() {
		return dataEmail;
	}

	public void setDataMensagem(String dataMensagem) {
		this.dataEmail = dataMensagem;
	}
	public Integer getIdEmail() {
		return idEmail;
	}
	public void setIdEmail(Integer idEmail) {
		this.idEmail = idEmail;
	}
	public Integer getIdAutor() {
		return idAutor;
	}
	public void setIdAutor(Integer idAutor) {
		this.idAutor = idAutor;
	}

	
}
