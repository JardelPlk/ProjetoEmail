package steam.entidades;
//Classe extende os atributos e métos da classe mãe Cadastro
public class Pessoa extends Cadastro {
	//Atributo próprio da classe
	private String sexo;
	//Métodos contrutores
	public Pessoa(Integer id, String nome, String nomeUsuario, String senha, String dataNascimento, String sexo) {
		super(id, nome, nomeUsuario, senha, dataNascimento);
		this.sexo = sexo;
	}
	//Sobrecarga do método construtor onde é utilizada como uma extensão do segundo método construtor da classe cadastro
	public Pessoa(Integer id, String nome, String nomeUsuario) {
		super(id, nome, nomeUsuario);
		this.sexo = null;
	}

	//Métodos de acesso: geters e seters
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
}
