package email.entidades;

import java.util.ArrayList;
//Classe abstrata
public abstract class Cadastro {
	//atributos da classe que serão extendidas para suas classes filhas
	private String nome;
	private String nomeUsuario;
	private String senha;
	private Data dataNascimento;
	protected ArrayList<Cadastro> contatos;
	
	//Métodos construtores
	public Cadastro(String nome, String nomeUsuario, String senha, Data dataNascimento) {
		super();
		this.nome = nome;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		this.contatos = new ArrayList<Cadastro>();
	}
	//Esta sobrecarga do método será utilizado pelos Arrays de contatos e pelos Arrays de destinatários, e pelos Arrays de clientes
	public Cadastro(String nome, String nomeUsuario) {
		super();
		this.nome = nome;
		this.nomeUsuario = nomeUsuario;
		this.senha = null;
		this.dataNascimento = null;
		this.contatos = new ArrayList<Cadastro>();
	}
	//Funções de acionar e remover contatos
	public void adicionarContato(Cadastro contato) {
		for(int i = 0; i < contatos.size(); i++) 
			if(contatos.get(i).getNomeUsuario().contentEquals(contato.getNomeUsuario()))
				return;
		contatos.add(contato);
	}
	
	public int removerContato(String nomeUsuario) {
		for(int i = 0; i < contatos.size(); i++) {
			if(contatos.get(i).getNomeUsuario().contentEquals(nomeUsuario)) {
				contatos.remove(i);
				return 0;
			}
		}
		return -1;
	}
	
	//Métodos de acesso: geters e seters
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Data getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Data dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public ArrayList<Cadastro> getContatos() {
		return contatos;
	}

	public void setContatos(ArrayList<Cadastro> contatos) {
		this.contatos = contatos;
	}
	//Método abstrato para listar os contatos onde cada classe filha terá um
	public abstract void listarContato();
	
}
