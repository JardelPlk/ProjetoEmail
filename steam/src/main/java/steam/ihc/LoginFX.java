package steam.ihc;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import steam.bd.PessoaDAO;
import steam.entidades.Pessoa;

public class LoginFX extends Application {

	private Stage stage;
	private Label lblEmail;
	private TextField txtUsuario;
	private PasswordField txtSenha;
	private Button btnEntrar;
	private Button btnSair;
	private Button btnCadastrar;
	private Pane pane;

	@Override
	public void start(Stage stage) throws Exception { // o palco vem por par�metro

		// ATEN��O: SEMPRE IMPORTAR OS COMPONENTES DO PACOTE JAVAFX!!!
		this.stage = stage;
		initComponentes();
		configLayout();

		Scene scene = new Scene(pane);
		btnEntrar.requestFocus(); // precisa ser depois de inicializar a cena, focar no bot�o de entrada

		stage.setScene(scene);
		stage.setTitle("Email login");
		stage.setResizable(false);
		stage.show();
	}

	private void initComponentes() {//Fun��o para inicializar os componentes
		//r�tulo
		lblEmail = new Label("Bem-vindo ao Email");

		//Entrada de texto nome do usu�rio
		txtUsuario = new TextField();
		txtUsuario.setPromptText("Digite aqui seu nome de usu�rio");

		//Entrada de texto senha
		txtSenha = new PasswordField();
		txtSenha.setPromptText("Digite aqui sua senha");

		//Bot�es
		btnEntrar = new Button("Entrar");
		btnEntrar.setOnAction(entrar());

		btnSair = new Button("Sair");
		btnSair.setOnAction(sair());

		btnCadastrar = new Button("Registrar nova conta");
		btnCadastrar.setOnAction(abrirJanelaCadastro());

		pane = new AnchorPane();

		//Adicionar os componentes
		pane.getChildren().add(lblEmail);//Adicionar somente um
		pane.getChildren().addAll(txtUsuario, txtSenha, btnEntrar, btnSair, btnCadastrar);//Adicionar v�rios
	}

	private void configLayout() {//Fun��o para configurar o layout
		pane.setPrefSize(320, 180);//Comprimento e altura da cena
		
		//Onde ficar� localizado o r�tulo
		lblEmail.setLayoutX(10);
		lblEmail.setLayoutY(10);

		//Dica do usu�rio
		txtUsuario.setLayoutX(10);
		txtUsuario.setLayoutY(35);
		//Caixa de texto do usu�rio
		txtUsuario.setPrefHeight(30);
		txtUsuario.setPrefWidth(pane.getPrefWidth() - 20);

		//Dica da senha
		txtSenha.setLayoutX(10);
		txtSenha.setLayoutY(75);
		//Caixa de texto da senha
		txtSenha.setPrefHeight(30);
		txtSenha.setPrefWidth(pane.getPrefWidth() - 20);

		//Nome do bot�o de entrada
		btnEntrar.setLayoutX(10);
		btnEntrar.setLayoutY(115);
		//Bot�o de entrada
		btnEntrar.setPrefHeight(20);
		btnEntrar.setPrefWidth((pane.getPrefWidth() - 30) / 2);

		//Nome do bot�o de sa�da
		btnSair.setLayoutX(btnEntrar.getPrefWidth() + 20);
		btnSair.setLayoutY(115);
		//Bot�o de sa�da 
		btnSair.setPrefHeight(20);
		btnSair.setPrefWidth((pane.getPrefWidth() - 30) / 2);

		//Nome do bot�o de cadastro
		btnCadastrar.setLayoutX(10);
		btnCadastrar.setLayoutY(145);
		//Bot�o de cadastro
		btnCadastrar.setPrefHeight(20);
		btnCadastrar.setPrefWidth(pane.getPrefWidth() - 20);
	}

	private EventHandler<ActionEvent> entrar() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					if (txtUsuario.getText().isBlank()) {
						AlertaFX.alerta("Usu�rio em branco!");
						return;
					}
					if (txtSenha.getText().isBlank()) {
						AlertaFX.alerta("Senha em branco!");
						return;
					}

					Pessoa usuarioBD = new PessoaDAO().getNomeUsuario(txtUsuario.getText());

					if (usuarioBD == null) {
						AlertaFX.alerta("Usu�rio ou senha inv�lidos!");
						return;
					}

					if (!usuarioBD.getSenha().contentEquals(txtSenha.getText())) {
						AlertaFX.alerta("Usu�rio ou senha inv�lidos!");
						return;
					}

					new MainFX(txtUsuario.getText()).start(stage);
				} catch (Exception e) {
					AlertaFX.erro("N�o foi poss�vel iniciar a tela principal!");
				}
			}
		};
	}

	private EventHandler<ActionEvent> sair() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		};
	}

	private EventHandler<ActionEvent> abrirJanelaCadastro() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					new CadastrarPessoaFX().start(stage);
				} catch (Exception e) {
					AlertaFX.erro("N�o foi poss�vel iniciar a tela de cadastro de jogador!");
				}
			}
		};
	}
}