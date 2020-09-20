package steam.entidades;

public class Cliente {
	private Cadastro empresa;
	private Cadastro cliente;
	
	public Cliente(Cadastro empresa, Cadastro cliente) {
		this.empresa = empresa;
		this.cliente = cliente;
	}
	
	public Cadastro getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Cadastro empresa) {
		this.empresa = empresa;
	}
	public Cadastro getCliente() {
		return cliente;
	}
	public void setCliente(Cadastro cliente) {
		this.cliente = cliente;
	}
}
