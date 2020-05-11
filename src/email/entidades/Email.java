package email.entidades;

import java.util.ArrayList;

public class Email {
	//Atributos
	private int codigo;
	private Cadastro autor;
	ArrayList<Cadastro> destinatarios;
	private String assunto;
	private String mensagem;
	private Data dataEmail;
	//M�todos construtores 
	public Email(int codigo, Cadastro autor, String assunto, String mensagem, Data dataEmail) {
		super();
		this.codigo = codigo;
		this.autor = autor;
		this.destinatarios = new ArrayList<Cadastro>();
		this.assunto = assunto;
		this.mensagem = mensagem;
		this.dataEmail = dataEmail;
	}
	//Sobrecarga do m�todo onde neste ele n�o recebe o assunto
	public Email(int codigo, Cadastro autor, String mensagem, Data dataEmail) {
		super();
		this.codigo = codigo;
		this.autor = autor;
		this.destinatarios = new ArrayList<Cadastro>();
		this.assunto = null;
		this.mensagem = mensagem;
		this.dataEmail = dataEmail;
	}
	//M�todos para adicionar destinat�rios ao email, onde destinat�rios poderam tamb�m visualizar este email
	public void adicionarDestinatarios(Cadastro destinatario) {
		for(int indice = 0; indice < destinatarios.size(); indice++) 
			if(destinatarios.get(indice).getNomeUsuario().contentEquals(destinatario.getNomeUsuario()))
				return;
			destinatarios.add(destinatario);
	}
	//Listar destinat�rios
	public void listarDestinatarios() {
		for(int indice = 0; indice < destinatarios.size(); indice++) {
			System.out.printf("Nome de usuario: %s\nNome: %s\n",destinatarios.get(indice).getNomeUsuario(),destinatarios.get(indice).getNome());
		}
	}
	//Remover destinat�rios
	public void removerDestinatarios(Cadastro destinatario) {
		for(int indice = 0; indice < destinatarios.size(); indice++) {
			if(destinatarios.get(indice).getNomeUsuario().contentEquals(destinatario.getNomeUsuario())) {
				destinatarios.remove(indice);
				return;
			}
		}
	}
	//M�todos de acesso geters e seters
	public Cadastro getAutor() {
		return autor;
	}
	public void setAutor(Cadastro autor) {
		this.autor = autor;
	}
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

	public Data getDataMensagem() {
		return dataEmail;
	}

	public void setDataMensagem(Data dataMensagem) {
		this.dataEmail = dataMensagem;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
}
