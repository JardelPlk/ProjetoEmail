package steam.entidades;

//Classe abstrata
public abstract class Cadastro {
	//atributos da classe que serão extendidas para suas classes filhas
	private Integer id;
	private String nome;
	private String nomeUsuario;
	private String senha;
	private String dataNascimento;
	
	//Métodos construtores
	public Cadastro(Integer id, String nome, String nomeUsuario, String senha, String dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
	}
	//Esta sobrecarga do método será utilizado pelos Arrays de contatos e pelos Arrays de destinatários, e pelos Arrays de clientes
	public Cadastro(Integer id, String nome, String nomeUsuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.nomeUsuario = nomeUsuario;
		this.senha = null;
		this.dataNascimento = null;
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

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
