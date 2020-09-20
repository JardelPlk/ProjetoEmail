package steam.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import steam.entidades.Cliente;
import steam.entidades.Empresa;
import steam.entidades.Pessoa;

public class EmpresaDAO implements InterfaceDAO<Empresa>{

	public void adicionar(Empresa empresa) {
		try {
			String sql = "INSERT INTO Empresa (Nome,NomeUsuario,Senha,DataNascimento,Ramo)"
					+ "VALUES ('" + empresa.getNome() + "','" + empresa.getNomeUsuario() + "','" 
		+ empresa.getSenha() + "','" + empresa.getDataNascimento() + "','" + empresa.getRamo() + "')";
			UtilBD.alterarBD(sql);
			
		}catch (SQLException e) {
			System.err.println("Não foi possível inserir a empresa no banco!");
		}
	}
	
	public void adicionarCliente(Empresa empresa) {
		try {
			List<Pessoa> pes = new ArrayList<Pessoa>();
			PessoaDAO pesDAO = new PessoaDAO();
			pes = pesDAO.todos();
			
			for(int i=0;i<empresa.getClientes().size();i++) {
				for(int j=0;j<pes.size();j++) {
					if(empresa.getClientes().get(i).getId() == pes.get(j).getId() && empresa.getClientes().get(i).getNome().contentEquals(pes.get(j).getNome())) {
						String sql = "INSERT INTO Cliente VALUES ('"+ empresa.getId() +"','"+ empresa.getClientes().get(i).getId() +"')";
						UtilBD.alterarBD(sql);
					}
				}
			}
		}catch (SQLException e) {
			System.err.println("Não foi possível inserir a empresa no banco!");
		}
	}

	public void remover(Empresa empresa) {
		try {
			String sql = "DELETE FROM Empresa WHERE IdEmpresa = '" + empresa.getId() + "'";
			UtilBD.alterarBD(sql);
		}catch (SQLException e) {
			System.err.println("Não foi possível remover a empresa no banco!");
		}
	}
	
	public void removerCliente(Empresa empresa) {
		try {
			int controle1 = 1;
			Scanner entrada = new Scanner(System.in);
			//O usuário poderá remover quantos clientes quiser
			while(controle1 == 1) {
				System.out.println("\nDigite o id de usuário do cliente: ");
				Integer id = entrada.nextInt();
				String sql = "DELETE FROM Cliente WHERE IdEmpresa = '" + empresa.getId() + "'"
						+ "AND IdCliente = '"+ id +"'";
				UtilBD.alterarBD(sql);
				System.out.println("\nDeseja remover mais contatos?[1/0]");
				controle1 = entrada.nextInt();
			}
		}catch (SQLException e) {
			System.err.println("Não foi possível remover a empresa no banco!");
		}
	}

	public void atualizar(Empresa empresa) {
		try {
			String sql = "UPDATE Empresa SET Nome = '" + empresa.getNome() + "' WHERE IdEmpresa = '"
					+ empresa.getId() + "'";
			UtilBD.alterarBD(sql);
			sql = "UPDATE Empresa SET NomeUsuario = '" + empresa.getNomeUsuario() + "' WHERE IdEmpresa = '"
					+ empresa.getId() + "'";
			UtilBD.alterarBD(sql);
			sql = "UPDATE Empresa SET Senha = '" + empresa.getSenha() + "' WHERE IdEmpresa = '"
					+ empresa.getId() + "'";
			UtilBD.alterarBD(sql);
			sql = "UPDATE Empresa SET DataNascimento = '" + empresa.getDataNascimento() + "' WHERE IdEmpresa = '"
					+ empresa.getId() + "'";
			UtilBD.alterarBD(sql);
			sql = "UPDATE Empresa SET Ramo = '" + empresa.getRamo() + "' WHERE IdEmpresa = '"
					+ empresa.getId() + "'";
			UtilBD.alterarBD(sql);
		} catch (SQLException e) {
			System.err.println("Não foi possível atualizar a empresa no banco!");
		}
	}
	
	public List<Empresa> todos() {
		List<Empresa> retorno = new ArrayList<Empresa>();
		try {
			String sql = "SELECT IdEmpresa, Nome, NomeUsuario, Senha, DataNascimento, Ramo FROM Empresa";
			ResultSet resultset = UtilBD.consultarBD(sql);
			while(resultset.next()){
				Integer id = resultset.getInt("IdEmpresa");
				String nome = resultset.getString("Nome");
				String nomeUsuario = resultset.getString("NomeUsuario");
				String senha = resultset.getString("Senha");
				String dataNascimento = resultset.getString("DataNascimento");
				String ramo = resultset.getString("Ramo");
				retorno.add(new Empresa(id, nome, nomeUsuario, senha, dataNascimento, ramo));
			}
			resultset.getStatement().close();
		}catch (SQLException e) {
			System.err.println("Não foi possível consultar todas as empresas do banco!");
		}
		return retorno;
	}
	
	public List<Cliente> todosCliente(){
		List<Cliente> retorno = new ArrayList<Cliente>();
		EmpresaDAO empresaDAO = new EmpresaDAO();
		Empresa empresa = null;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = null;
		try {
			String sql = "SELECT IdEmpresa, IdCliente FROM Cliente";
			ResultSet resultset = UtilBD.consultarBD(sql);
			while(resultset.next()){
				Integer idEmpresa = resultset.getInt("IdEmpresa");
				Integer idCliente = resultset.getInt("IdCliente");
				retorno.add(new Cliente(empresa = empresaDAO.get(idEmpresa), pessoa = pessoaDAO.get(idCliente)));
			}
			resultset.getStatement().close();
		}catch (SQLException e) {
			System.err.println("Não foi possível consultar todas as empresas do banco!");
		}
		return retorno;
	}
	
	public Empresa get(Integer id) {
		Empresa retorno = null;
		try {
			String sql = "SELECT IdEmpresa, Nome, NomeUsuario, Senha, DataNascimento, Ramo FROM Empresa WHERE IdEmpresa = '" + id + "'";
			ResultSet resultSet = UtilBD.consultarBD(sql);
			while (resultSet.next()) {
				String nome = resultSet.getString("Nome");
				String nomeUsuario = resultSet.getString("NomeUsuario");
				String senha = resultSet.getString("Senha");
				String dataNascimento = resultSet.getString("DataNascimento");
				String ramo = resultSet.getString("Ramo");
				retorno = new Empresa(id, nome, nomeUsuario, senha, dataNascimento, ramo);
			}
			resultSet.getStatement().close();
		} catch (SQLException e) {
			System.err.println("Não foi possível consultar uma desenvolvedora do banco!");
		}
		return retorno;
	}
	
}
