package steam.entidades;
//Classe extende os atributos e m�tos da classe m�e Cadastro
public class Pessoa extends Cadastro {
	//Atributo pr�prio da classe
	private String sexo;
	//M�todos contrutores
	public Pessoa(Integer id, String nome, String nomeUsuario, String senha, String dataNascimento, String sexo) {
		super(id, nome, nomeUsuario, senha, dataNascimento);
		this.sexo = sexo;
	}
	//Sobrecarga do m�todo construtor onde � utilizada como uma extens�o do segundo m�todo construtor da classe cadastro
	public Pessoa(Integer id, String nome, String nomeUsuario) {
		super(id, nome, nomeUsuario);
		this.sexo = null;
	}

	//M�todos de acesso: geters e seters
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
}
