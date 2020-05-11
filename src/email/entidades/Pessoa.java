package email.entidades;
//Classe extende os atributos e m�tos da classe m�e Cadastro
public class Pessoa extends Cadastro {
	//Atributo pr�prio da classe
	private String sexo;
	//M�todos contrutores
	public Pessoa(String nome, String nomeUsuario, String senha, Data dataNascimento, String sexo) {
		super(nome, nomeUsuario, senha, dataNascimento);
		this.sexo = sexo;
	}
	//Sobrecarga do m�todo construtor onde � utilizada como uma extens�o do segundo m�todo construtor da classe cadastro
	public Pessoa(String nome, String nomeUsuario) {
		super(nome, nomeUsuario);
		this.sexo = null;
	}

	//M�todos de acesso: geters e seters
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	//Sobrescrita do m�todo abstrato
	@Override
	public void listarContato() {
		for(int indice = 0; indice < contatos.size(); indice++)
			System.out.printf("\nNome de usuario do contato: %s\nNome do contato: %s\n",contatos.get(indice).getNomeUsuario(),contatos.get(indice).getNome());
	}
}
