package steam.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UtilBD {
	private static Connection conexao;

	public static Connection getConexao() {
		try {

			if (conexao == null)
				abrirConexao();

			if (conexao.isClosed())
				abrirConexao();

		} catch (SQLException e) {
			System.err.println("Não consegui abrir a conexão com o banco!");
		}

		return conexao;
	}

	private static void abrirConexao() {
		try {
			Class.forName("org.sqlite.JDBC");
			conexao = DriverManager.getConnection("jdbc:sqlite:bancoEmail.sqlite");
		} catch (SQLException e) {
			System.err.println("Não consegui abrir a conexão com o banco!");
		} catch (ClassNotFoundException e2) {
			System.err.println("A biblioteca do SQLite não está funcionando corretamente!");
		}
	}

	public static void fecharConexao() {
		if (conexao == null)
			return;

		try {
			if (!conexao.isClosed())
				conexao.close();
		} catch (SQLException e) {
			System.err.println("Não consegui fechar a conexão com o banco!");
		}
	}

	public static void initBD() {
		try {
			conexao = getConexao();
			Statement stm = conexao.createStatement();
			criarEmpresa(stm);
			criarPessoa(stm);
			criarCliente(stm);
			criarEmailEmpresa(stm);
			criarEmailPessoa(stm);
			criarDestinatarioPessoa(stm);
			criarDestinatarioPessoaEmpresa(stm);
			criarDestinatarioEmpresa(stm);
			criarDestinatarioEmpresaPessoa(stm);
			stm.close();
		} catch (SQLException e) {
			System.err.println("Não consegui criar o banco!");
		}
	}

	private static void criarEmpresa(Statement stm) throws SQLException{
		stm.executeUpdate("PRAGMA foreign_keys=ON");
		stm.executeUpdate("DROP TABLE IF EXISTS Empresa");
		stm.executeUpdate("CREATE TABLE Empresa (IdEmpresa integer, Nome varchar(100) NOT NULL, NomeUsuario varchar(50)"
				+ "NOT NULL, Senha varchar(50) NOT NULL, DataNascimento varchar(15) NOT NULL, Ramo varchar(50) NOT NULL,"
				+ "CONSTRAINT PK_EMPRESA PRIMARY KEY (IdEmpresa));");
		stm.executeUpdate("INSERT INTO Empresa VALUES (null,'Jangão Ltda','jangao@gmail.com','123','15-07-1999','Elétrico')");
		stm.executeUpdate("INSERT INTO Empresa VALUES (null,'Manutenção CB','manutencaocb@gmail.com','123','16-05-2005','Mecânico')");
		stm.executeUpdate("INSERT INTO Empresa VALUES (null,'Pauluka Alimentos','pauluka@gmail.com','123','14-08-2000','Alimentício')");
	}
	
	private static void criarPessoa(Statement stm) throws SQLException{
		stm.executeUpdate("PRAGMA foreign_keys=ON");
		stm.executeUpdate("DROP TABLE IF EXISTS Pessoa");
		stm.executeUpdate("CREATE TABLE Pessoa (IdPessoa integer, Nome varchar(100) NOT NULL, NomeUsuario varchar(50)"
				+ "NOT NULL, Senha varchar(50) NOT NULL, DataNascimento varchar(15) NOT NULL, Sexo varchar(15) NOT NULL,"
				+ "CONSTRAINT PK_PESSOA PRIMARY KEY (IdPessoa));");
		stm.executeUpdate("INSERT INTO Pessoa VALUES (null,'Billie Joe Armstrong','billiejoe@gmail.com','123','17-02-1972','Masculino')");
		stm.executeUpdate("INSERT INTO Pessoa VALUES (null,'João da Silva','joaosilva@gmail.com','123','17-03-1985','Masculino')");
		stm.executeUpdate("INSERT INTO Pessoa VALUES (null,'Maria Marin','mariamarin@gmail.com','123','02-06-2001','Feminino')");
	}
	
	private static void criarCliente(Statement stm) throws SQLException{
		stm.executeUpdate("PRAGMA foreign_keys=ON");
		stm.executeUpdate("DROP TABLE IF EXISTS Cliente");
		stm.executeUpdate("CREATE TABLE Cliente (IdEmpresa integer, IdCliente integer,"
				+ "FOREIGN KEY (IdEmpresa) REFERENCES Empresa(IdEmpresa) "
				+ "ON UPDATE CASCADE ON DELETE CASCADE,"
				+ "FOREIGN KEY (IdCliente) REFERENCES Pessoa(IdPessoa) "
				+ "ON UPDATE CASCADE ON DELETE CASCADE,"
				+ "CONSTRAINT PK_CLIENTE PRIMARY KEY (IdEmpresa,IdCliente));");
		stm.executeUpdate("INSERT INTO Cliente VALUES (3,1)");
		stm.executeUpdate("INSERT INTO Cliente VALUES (1,2)");
		stm.executeUpdate("INSERT INTO Cliente VALUES (3,3)");
	}
	
	private static void criarEmailEmpresa(Statement stm) throws SQLException{
		stm.executeUpdate("PRAGMA foreign_keys=ON");
		stm.executeUpdate("DROP TABLE IF EXISTS EmailEmpresa");
		stm.executeUpdate("CREATE TABLE EmailEmpresa (IdEmailEmpresa integer, IdEmpresa integer, "
				+ "Assunto varchar(50), Mensagem varchar(200) NOT NULL,"
				+ "DataEmail varchar(15) NOT NULL,"
				+ "FOREIGN KEY (IdEmpresa) REFERENCES Empresa(IdEmpresa) "
				+ "ON UPDATE CASCADE ON DELETE CASCADE,"
				+ "CONSTRAINT PK_EMAILEMPRESA PRIMARY KEY (IdEmailEmpresa));");
		stm.executeUpdate("INSERT INTO EmailEmpresa VALUES (null,3,'Entrega de alimentos.',"
				+ "'Informamos que será realizada a entrega de alimentos na sua propriedades.','14-09-2020')");
		stm.executeUpdate("INSERT INTO EmailEmpresa VALUES (null,1,'',"
				+ "'OK, estaremos esperando.','14-09-2020')");		
		stm.executeUpdate("INSERT INTO EmailEmpresa VALUES (null,3,'Manutenção de equipamentos.',"
				+ "'Necessitamos do serviço de vocês para fazer a manutenção de algumas máquinas.','10-09-2020')");	
	}
	
	private static void criarEmailPessoa(Statement stm) throws SQLException{
		stm.executeUpdate("PRAGMA foreign_keys=ON");
		stm.executeUpdate("DROP TABLE IF EXISTS EmailPessoa");
		stm.executeUpdate("CREATE TABLE EmailPessoa (IdEmailPessoa integer, IdPessoa, "
				+ "Assunto varchar(50), Mensagem varchar(200) NOT NULL,"
				+ "DataEmail varchar(15) NOT NULL,"
				+ "FOREIGN KEY (IdPessoa) REFERENCES Pessoa(IdPessoa) "
				+ "ON UPDATE CASCADE ON DELETE CASCADE,"
				+ "CONSTRAINT PK_EMAILPESSOA PRIMARY KEY (IdEmailPessoa));");
		stm.executeUpdate("INSERT INTO EmailPessoa VALUES (null,1,'Acompanhe o site da minha banda!',"
				+ "'Link do site: https://greenday.com/','12-03-2020')");
		stm.executeUpdate("INSERT INTO EmailPessoa VALUES (null,2,'Assunto particular!',"
				+ "'Olá, tudo bem?','11-09-2020')");		
		stm.executeUpdate("INSERT INTO EmailPessoa VALUES (null,3,'Email de resposta.',"
				+ "'Boa tarde, tudo ótimo e você?','12-09-2020')");	
	}
	
	private static void criarDestinatarioPessoa(Statement stm) throws SQLException{
		stm.executeUpdate("PRAGMA foreign_keys=ON");
		stm.executeUpdate("DROP TABLE IF EXISTS DestinatarioPessoa");
		stm.executeUpdate("CREATE TABLE DestinatarioPessoa (IdEmailPessoa integer, IdPessoa integer,"
				+ "FOREIGN KEY (IdEmailPessoa) REFERENCES EmailPessoa(IdEmailPessoa) "
				+ "ON UPDATE CASCADE ON DELETE CASCADE,"
				+ "FOREIGN KEY (IdPessoa) REFERENCES Pessoa(IdPessoa) "
				+ "ON UPDATE CASCADE ON DELETE CASCADE,"
				+ "CONSTRAINT PK_DESTINATARIOPESSOA PRIMARY KEY (IdEmailPessoa,IdPessoa));");
		stm.executeUpdate("INSERT INTO DestinatarioPessoa VALUES (1,2)");
		stm.executeUpdate("INSERT INTO DestinatarioPessoa VALUES (2,2)");
		stm.executeUpdate("INSERT INTO DestinatarioPessoa VALUES (3,3)");
		stm.executeUpdate("INSERT INTO DestinatarioPessoa VALUES (3,1)");
	}

	private static void criarDestinatarioPessoaEmpresa(Statement stm) throws SQLException{
		stm.executeUpdate("PRAGMA foreign_keys=ON");
		stm.executeUpdate("DROP TABLE IF EXISTS DestinatarioPessoaEmpresa");
		stm.executeUpdate("CREATE TABLE DestinatarioPessoaEmpresa (IdEmailPessoa integer, IdEmpresa integer,"
				+ "FOREIGN KEY (IdEmailPessoa) REFERENCES EmailPessoa(IdEmailPessoa) "
				+ "ON UPDATE CASCADE ON DELETE CASCADE,"
				+ "FOREIGN KEY (IdEmpresa) REFERENCES Empresa(IdEmpresa) "
				+ "ON UPDATE CASCADE ON DELETE CASCADE,"
				+ "CONSTRAINT PK_DESTINATARIOPESSOAEMPRESA PRIMARY KEY (IdEmailPessoa,IdEmpresa));");
		stm.executeUpdate("INSERT INTO DestinatarioPessoaEmpresa VALUES (1,3)");
		stm.executeUpdate("INSERT INTO DestinatarioPessoaEmpresa VALUES (2,1)");
		stm.executeUpdate("INSERT INTO DestinatarioPessoaEmpresa VALUES (3,3)");
	}
	
	private static void criarDestinatarioEmpresa(Statement stm) throws SQLException{
		stm.executeUpdate("PRAGMA foreign_keys=ON");
		stm.executeUpdate("DROP TABLE IF EXISTS DestinatarioEmpresa");
		stm.executeUpdate("CREATE TABLE DestinatarioEmpresa (IdEmailEmpresa integer, IdEmpresa integer,"
				+ "FOREIGN KEY (IdEmailEmpresa) REFERENCES EmailEmpresa(IdEmailEmpresa) "
				+ "ON UPDATE CASCADE ON DELETE CASCADE,"
				+ "FOREIGN KEY (IdEmpresa) REFERENCES Empresa(IdEmpresa) "
				+ "ON UPDATE CASCADE ON DELETE CASCADE,"
				+ "CONSTRAINT PK_DESTINATARIOEMPRESA PRIMARY KEY (IdEmailEmpresa,IdEmpresa));");
		stm.executeUpdate("INSERT INTO DestinatarioEmpresa VALUES (3,2)");
		stm.executeUpdate("INSERT INTO DestinatarioEmpresa VALUES (1,1)");
		stm.executeUpdate("INSERT INTO DestinatarioEmpresa VALUES (2,3)");
	}
	
	private static void criarDestinatarioEmpresaPessoa(Statement stm) throws SQLException{
		stm.executeUpdate("PRAGMA foreign_keys=ON");
		stm.executeUpdate("DROP TABLE IF EXISTS DestinatarioEmpresaPessoa");
		stm.executeUpdate("CREATE TABLE DestinatarioEmpresaPessoa (IdEmailEmpresa integer, IdPessoa integer,"
				+ "FOREIGN KEY (IdEmailEmpresa) REFERENCES EmailEmpresa(IdEmailEmpresa) "
				+ "ON UPDATE CASCADE ON DELETE CASCADE,"
				+ "FOREIGN KEY (IdPessoa) REFERENCES Pessoa(IdPessoa) "
				+ "ON UPDATE CASCADE ON DELETE CASCADE,"
				+ "CONSTRAINT PK_DESTINATARIOEMPRESAPESSOA PRIMARY KEY (IdEmailEmpresa,IdPessoa));");
		stm.executeUpdate("INSERT INTO DestinatarioEmpresaPessoa VALUES (3,2)");
		stm.executeUpdate("INSERT INTO DestinatarioEmpresaPessoa VALUES (1,3)");
		stm.executeUpdate("INSERT INTO DestinatarioEmpresaPessoa VALUES (2,2)");
	}
	
	public static void alterarBD(String sql) throws SQLException {
		Connection bd = UtilBD.getConexao();
		Statement stm = bd.createStatement();
		stm.executeUpdate(sql);
		System.out.println("Executei: " + sql);
		stm.close();
	}

	public static ResultSet consultarBD(String sql) throws SQLException {
		Connection bd = UtilBD.getConexao();
		Statement stm = bd.createStatement();
		ResultSet retorno = stm.executeQuery(sql);
		System.out.println("Executei: " + sql);
		return retorno;
	}
}
