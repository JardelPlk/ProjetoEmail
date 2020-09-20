package steam.entidades;

import java.util.ArrayList;
//Classe extende os atributos e m�tos da classe m�e Cadastro
public class Empresa extends Cadastro {
	//Atribustos pr�prios da classe
	private String ramo;
	private ArrayList<Pessoa> clientes;
	//M�todos construtores
	public Empresa(Integer id, String nome, String nomeUsuario, String senha, String dataNascimento, String ramo) {
		super(id, nome, nomeUsuario, senha, dataNascimento);
		this.ramo = ramo;
		this.clientes = new ArrayList<Pessoa>();
	}
	//Sobrecarga do m�todo construtor onde � utilizada como uma extens�o do segundo m�todo construtor da classe cadastro
	public Empresa(Integer id, String nome, String nomeUsuario) {
		super(id, nome, nomeUsuario);
		this.ramo = null;
		this.clientes = new ArrayList<Pessoa>();
	}
	//M�todo para adicionar clientes a ao email empresarial
	public void adicionarCliente(Pessoa cliente) {
		for(int indice = 0; indice < clientes.size(); indice++)
			if(clientes.get(indice).getNomeUsuario().contentEquals(cliente.getNomeUsuario()))
				return;
			clientes.add(cliente);
	}
	//Listar os clientes de determinada empresa
	public void listarCLiente() {
		for(int indice = 0; indice < clientes.size(); indice++) {
			System.out.printf("\nNome de usu�rio do cliente: %s\nNome do cliente: %s",clientes.get(indice).getNomeUsuario(), clientes.get(indice).getNome());
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
	//M�todos de acesso: geters e seters
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
}
