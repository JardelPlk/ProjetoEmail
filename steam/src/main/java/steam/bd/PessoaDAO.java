package steam.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import steam.entidades.Pessoa;

public class PessoaDAO implements InterfaceDAO<Pessoa>{

	public void adicionar(Pessoa pessoa) {
		try {
			String sql = "INSERT INTO Pessoa (Nome,NomeUsuario,Senha,DataNascimento,Sexo)"
					+ "VALUES ('" + pessoa.getNome() + "','" + pessoa.getNomeUsuario() + "','" 
		+ pessoa.getSenha() + "','" + pessoa.getDataNascimento() + "','" + pessoa.getSexo() + "')";
			UtilBD.alterarBD(sql);
			
		}catch (SQLException e) {
			System.err.println("Não foi possível inserir a empresa no banco!");
		}
		
	}

	public void remover(Pessoa pessoa) {
		try {
			String sql = "DELETE FROM Pessoa WHERE IdPessoa = '" + pessoa.getId() + "'";
			UtilBD.alterarBD(sql);
		}catch (SQLException e) {
			System.err.println("Não foi possível remover a empresa no banco!");
		}
	}
	
	public void atualizar(Pessoa pessoa) {
		try {
			String sql = "UPDATE Pessoa SET Nome = '" + pessoa.getNome() + "' WHERE IdPessoa = '"
					+ pessoa.getId() + "'";
			UtilBD.alterarBD(sql);
			sql = "UPDATE Pessoa SET NomeUsuario = '" + pessoa.getNomeUsuario() + "' WHERE IdPessoa = '"
					+ pessoa.getId() + "'";
			UtilBD.alterarBD(sql);
			sql = "UPDATE Pessoa SET Senha = '" + pessoa.getSenha() + "' WHERE IdPessoa = '"
					+ pessoa.getId() + "'";
			UtilBD.alterarBD(sql);
			sql = "UPDATE Pessoa SET DataNascimento = '" + pessoa.getDataNascimento() + "' WHERE IdPessoa = '"
					+ pessoa.getId() + "'";
			UtilBD.alterarBD(sql);
			sql = "UPDATE Pessoa SET Sexo = '" + pessoa.getSexo() + "' WHERE IdPessoa = '"
					+ pessoa.getId() + "'";
			UtilBD.alterarBD(sql);
		} catch (SQLException e) {
			System.err.println("Não foi possível atualizar a pessoa no banco!");
		}
	}

	public List<Pessoa> todos() {
		List<Pessoa> retorno = new ArrayList<Pessoa>();
		try {
			String sql = "SELECT IdPessoa, Nome, NomeUsuario, Senha, DataNascimento, Sexo FROM Pessoa";
			ResultSet resultset = UtilBD.consultarBD(sql);
			while(resultset.next()){
				Integer id = resultset.getInt("IdPessoa");
				String nome = resultset.getString("Nome");
				String nomeUsuario = resultset.getString("NomeUsuario");
				String senha = resultset.getString("Senha");
				String dataNascimento = resultset.getString("DataNascimento");
				String sexo = resultset.getString("Sexo");
				retorno.add(new Pessoa(id, nome, nomeUsuario, senha, dataNascimento, sexo));
			}
			resultset.getStatement().close();
		}catch (SQLException e) {
			System.err.println("Não foi possível consultar todas as empresas do banco!");
		}
		return retorno;
	}
	
	public Pessoa get(Integer id) {
		Pessoa retorno = null;
		try {
			String sql = "SELECT IdPessoa, Nome, NomeUsuario, Senha, DataNascimento, Sexo FROM Pessoa WHERE IdPessoa = '" + id + "'";
			ResultSet resultSet = UtilBD.consultarBD(sql);
			while (resultSet.next()) {
				String nome = resultSet.getString("Nome");
				String nomeUsuario = resultSet.getString("NomeUsuario");
				String senha = resultSet.getString("Senha");
				String dataNascimento = resultSet.getString("DataNascimento");
				String sexo = resultSet.getString("Sexo");
				retorno = new Pessoa(id, nome, nomeUsuario, senha, dataNascimento, sexo);
			}
			resultSet.getStatement().close();
		} catch (SQLException e) {
			System.err.println("Não foi possível consultar uma desenvolvedora do banco!");
		}
		return retorno;
	}
	
}
