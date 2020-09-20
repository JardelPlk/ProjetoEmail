package steam.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import steam.entidades.Destinatario;
import steam.entidades.Email;
import steam.entidades.Empresa;
import steam.entidades.Pessoa;

public class EmailEmpresaDAO implements InterfaceDAO<Email>{

	public void adicionar(Email email) {
		try {
			String sql = "INSERT INTO EmailEmpresa (IdEmpresa,Assunto,Mensagem,DataEmail)"
					+ "VALUES ('" + email.getIdAutor() + "','" + email.getAssunto() + "','" 
		+ email.getMensagem() + "','" + email.getDataMensagem() + "')";
			UtilBD.alterarBD(sql);
			
			List<Email> emailLista = new ArrayList<Email>();
			EmailEmpresaDAO emailEmpresaDAO = new EmailEmpresaDAO();
			emailLista = emailEmpresaDAO.todos();
			int indice = emailLista.size()-1;
			
			List<Empresa> emp = new ArrayList<Empresa>();
			EmpresaDAO empDAO = new EmpresaDAO();
			emp = empDAO.todos();
			List<Pessoa> pes = new ArrayList<Pessoa>();
			PessoaDAO pesDAO = new PessoaDAO();
			pes = pesDAO.todos();
			
			for(int i=0;i<email.getDestinatarios().size();i++) {
				for(int j=0;j<emp.size();j++) {
					if(email.getDestinatarios().get(i).getId() == emp.get(j).getId() && email.getDestinatarios().get(i).getNome().contentEquals(emp.get(j).getNome())) {
						sql = "INSERT INTO DestinatarioEmpresa VALUES ('" + emailLista.get(indice).getIdEmail() + "','" + email.getDestinatarios().get(i).getId() + "')";
						UtilBD.alterarBD(sql);
					}else if(email.getDestinatarios().get(i).getId() == pes.get(j).getId() && email.getDestinatarios().get(i).getNome().contentEquals(pes.get(j).getNome())){
						sql = "INSERT INTO DestinatarioEmpresaPessoa VALUES ('" + emailLista.get(indice).getIdEmail() + "','" + email.getDestinatarios().get(i).getId() + "')";
						UtilBD.alterarBD(sql);
					}
				}
			}
			
		}catch (SQLException e) {
			System.err.println("Não foi possível inserir o email no banco!");
		}
	}

	public void remover(Email email) {
		try {
			String sql = "DELETE FROM EmailEmpresa WHERE IdEmailEmpresa = '" + email.getIdEmail() + "'";
			UtilBD.alterarBD(sql);
		}catch (SQLException e) {
			System.err.println("Não foi possível remover o email no banco!");
		}
	}
	
	public void atualizar(Email email) {
		try {
			String sql = "UPDATE EmailEmpresa SET Assunto = '" + email.getAssunto() + "' WHERE "
					+ "IdEmailEmpresa = '"+ email.getIdEmail() +"'";
			UtilBD.alterarBD(sql);
			sql = "UPDATE EmailEmpresa SET Mensagem = '" + email.getMensagem() + "' WHERE "
					+ "IdEmailEmpresa = '"+ email.getIdEmail() +"'";
			UtilBD.alterarBD(sql);
			sql = "UPDATE EmailEmpresa SET DataEmail = '" + email.getDataMensagem() + "' WHERE "
					+ "IdEmailEmpresa = '"+ email.getIdEmail() +"'";
			UtilBD.alterarBD(sql);
		} catch (SQLException e) {
			System.err.println("Não foi possível atualizar o email no banco!");
		}
	}

	public List<Email> todos() {
		List<Email> retorno = new ArrayList<Email>();
		try {
			String sql = "SELECT IdEmailEmpresa, IdEmpresa, Assunto, Mensagem, DataEmail FROM EmailEmpresa";
			ResultSet resultset = UtilBD.consultarBD(sql);
			while(resultset.next()){
				Integer idEmail = resultset.getInt("IdEmailEmpresa");
				Integer idAutor = resultset.getInt("IdEmpresa");
				String assunto = resultset.getString("Assunto");
				String mensagem = resultset.getString("Mensagem");
				String dataEmail = resultset.getString("DataEmail");
				retorno.add(new Email(idEmail, idAutor, assunto, mensagem, dataEmail));
			}
			resultset.getStatement().close();
		}catch (SQLException e) {
			System.err.println("Não foi possível consultar todas as empresas do banco!");
		}
		return retorno;
	}
	
	public List<Destinatario> todosDestinatario(int i){
		List<Destinatario> retorno = new ArrayList<Destinatario>();
		try {
			if(i == 1) {
				String sql = "SELECT IdEmailEmpresa, IdEmpresa FROM DestinatarioEmpresa";
				ResultSet resultset = UtilBD.consultarBD(sql);
				while(resultset.next()) {
					Integer idEmail = resultset.getInt("IdEmailEmpresa");
					Integer idDestinatario = resultset.getInt("IdEmpresa");
					retorno.add(new Destinatario(idEmail, idDestinatario));
				}
				resultset.getStatement().close();
			}else if(i == 2) {
				String sql = "SELECT IdEmailEmpresa, IdPessoa FROM DestinatarioEmpresaPessoa";
				ResultSet resultset = UtilBD.consultarBD(sql);
				while(resultset.next()) {
					Integer idEmail = resultset.getInt("IdEmailEmpresa");
					Integer idDestinatario = resultset.getInt("IdPessoa");
					retorno.add(new Destinatario(idEmail, idDestinatario));
				}
				resultset.getStatement().close();
			}else if(i == 3) {
				String sql = "SELECT IdEmailPessoa, IdPessoa FROM DestinatarioPessoa";
				ResultSet resultset = UtilBD.consultarBD(sql);
				while(resultset.next()) {
					Integer idEmail = resultset.getInt("IdEmailPessoa");
					Integer idDestinatario = resultset.getInt("IdPessoa");
					retorno.add(new Destinatario(idEmail, idDestinatario));
				}
				resultset.getStatement().close();
			}else {
				String sql = "SELECT IdEmailPessoa, IdEmpresa FROM DestinatarioPessoaEmpresa";
				ResultSet resultset = UtilBD.consultarBD(sql);
				while(resultset.next()) {
					Integer idEmail = resultset.getInt("IdEmailPessoa");
					Integer idDestinatario = resultset.getInt("IdEmpresa");
					retorno.add(new Destinatario(idEmail, idDestinatario));
				}
				resultset.getStatement().close();
			}

		}catch(SQLException e) {
			System.err.println("Não for possível consultar os destinatarios!");
		}
		return retorno;
	}
	
	public Email get(Integer id) {
		Email retorno = null;
		try {
			String sql = "SELECT IdEmailEmpresa, idEmpresa, Assunto, Mensagem, DataEmail FROM EmailEmpresa"
					+ " WHERE IdEmailEmpresa = '" + id + "'";
			ResultSet resultSet = UtilBD.consultarBD(sql);
			while (resultSet.next()) {
				Integer idAutor = resultSet.getInt("IdEmpresa");
				String assunto = resultSet.getString("Assunto");
				String mensagem = resultSet.getString("Mensagem");
				String dataEmail = resultSet.getString("DataEmail");
				retorno = new Email(id, idAutor, assunto, mensagem, dataEmail);
			}
			resultSet.getStatement().close();
		} catch (SQLException e) {
			System.err.println("Não foi possível consultar uma desenvolvedora do banco!");
		}
		return retorno;
	}

}
