package email.entidades;

public class Data {
	//Atributos
	private int dia;
	private int mes;
	private int ano;
	//Métodos contrutores
	public Data(int dia, int mes, int ano) {
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}
	//Data que é enviado o email, dai a cada mês e ano ele é atualizado
	public Data(int dia) {
		this.dia = dia;
		this.mes = 5;
		this.ano = 2020;
	}
	//Métodos de acesso: geters e seters
	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
}
