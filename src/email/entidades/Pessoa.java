package email.entidades;
//Classe extende os atributos e métos da classe mãe Cadastro
public class Pessoa extends Cadastro {
	//Atributo próprio da classe
	private String sexo;
	//Métodos contrutores
	public Pessoa(String nome, String nomeUsuario, String senha, Data dataNascimento, String sexo) {
		super(nome, nomeUsuario, senha, dataNascimento);
		this.sexo = sexo;
	}
	//Sobrecarga do método construtor onde é utilizada como uma extensão do segundo método construtor da classe cadastro
	public Pessoa(String nome, String nomeUsuario) {
		super(nome, nomeUsuario);
		this.sexo = null;
	}

	//Métodos de acesso: geters e seters
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	//Sobrescrita do método abstrato
	@Override
	public void listarContato() {
		for(int indice = 0; indice < contatos.size(); indice++)
			System.out.printf("\nNome de usuario do contato: %s\nNome do contato: %s\n",contatos.get(indice).getNomeUsuario(),contatos.get(indice).getNome());
	}
}
