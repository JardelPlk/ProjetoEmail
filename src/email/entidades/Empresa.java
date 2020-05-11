package email.entidades;

import java.util.ArrayList;
//Classe extende os atributos e métos da classe mãe Cadastro
public class Empresa extends Cadastro {
	//Atribustos próprios da classe
	private String ramo;
	private ArrayList<Pessoa> clientes;
	//Métodos construtores
	public Empresa(String nome, String nomeUsuario, String senha, Data dataNascimento, String ramo) {
		super(nome, nomeUsuario, senha, dataNascimento);
		this.ramo = ramo;
		this.clientes = new ArrayList<Pessoa>();
	}
	//Sobrecarga do método construtor onde é utilizada como uma extensão do segundo método construtor da classe cadastro
	public Empresa(String nome, String nomeUsuario) {
		super(nome, nomeUsuario);
		this.ramo = null;
		this.clientes = new ArrayList<Pessoa>();
	}
	//Método para adicionar clientes a ao email empresarial
	public void adicionarCliente(Pessoa cliente) {
		for(int indice = 0; indice < clientes.size(); indice++)
			if(clientes.get(indice).getNomeUsuario().contentEquals(cliente.getNomeUsuario()))
				return;
			clientes.add(cliente);
	}
	//Listar os clientes de determinada empresa
	public void listarCLiente() {
		for(int indice = 0; indice < clientes.size(); indice++) {
			System.out.printf("\nNome de usuário do cliente: %s\nNome do cliente: %s",clientes.get(indice).getNomeUsuario(), clientes.get(indice).getNome());
		}
	}
	//Remover clientes de determinada empresa
	public int removerCliente(String nomeUsuario) {
		for(int indice = 0; indice < clientes.size(); indice++) {
			if(clientes.get(indice).getNomeUsuario().contentEquals(nomeUsuario)) {
				clientes.remove(indice);
				return 0;
			}
		}
		return -1;
	}
	//Métodos de acesso: geters e seters
	public String getRamo() {
		return ramo;
	}

	public void setRamo(String ramo) {
		this.ramo = ramo;
	}

	public ArrayList<Pessoa> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Pessoa> clientes) {
		this.clientes = clientes;
	}

	//Sobrescrita do método abstrato
	@Override
	public void listarContato() {
		for(int indice = 0; indice < contatos.size(); indice++)
			System.out.printf("\nNome de usuario do contato: %s\nNome do contato: %s\n",contatos.get(indice).getNomeUsuario(),contatos.get(indice).getNome());
	}
}
