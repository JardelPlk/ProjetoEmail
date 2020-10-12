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
	public void start(Stage stage) throws Exception { // o palco vem por parâmetro

		// ATENÇÃO: SEMPRE IMPORTAR OS COMPONENTES DO PACOTE JAVAFX!!!
		this.stage = stage;
		initComponentes();
		configLayout();

		Scene scene = new Scene(pane);
		btnEntrar.requestFocus(); // precisa ser depois de inicializar a cena, focar no botão de entrada

		stage.setScene(scene);
		stage.setTitle("Email login");
		stage.setResizable(false);
		stage.show();
	}

	private void initComponentes() {//Função para inicializar os componentes
		//rótulo
		lblEmail = new Label("Bem-vindo ao Email");

		//Entrada de texto nome do usuário
		txtUsuario = new TextField();
		txtUsuario.setPromptText("Digite aqui seu nome de usuário");

		//Entrada de texto senha
		txtSenha = new PasswordField();
		txtSenha.setPromptText("Digite aqui sua senha");

		//Botões
		btnEntrar = new Button("Entrar");
		btnEntrar.setOnAction(entrar());

		btnSair = new Button("Sair");
		btnSair.setOnAction(sair());

		btnCadastrar = new Button("Registrar nova conta");
		btnCadastrar.setOnAction(abrirJanelaCadastro());

		pane = new AnchorPane();

		//Adicionar os componentes
		pane.getChildren().add(lblEmail);//Adicionar somente um
		pane.getChildren().addAll(txtUsuario, txtSenha, btnEntrar, btnSair, btnCadastrar);//Adicionar vários
	}

	private void configLayout() {//Função para configurar o layout
		pane.setPrefSize(320, 180);//Comprimento e altura da cena
		
		//Onde ficará localizado o rótulo
		lblEmail.setLayoutX(10);
		lblEmail.setLayoutY(10);

		//Dica do usuário
		txtUsuario.setLayoutX(10);
		txtUsuario.setLayoutY(35);
		//Caixa de texto do usuário
		txtUsuario.setPrefHeight(30);
		txtUsuario.setPrefWidth(pane.getPrefWidth() - 20);

		//Dica da senha
		txtSenha.setLayoutX(10);
		txtSenha.setLayoutY(75);
		//Caixa de texto da senha
		txtSenha.setPrefHeight(30);
		txtSenha.setPrefWidth(pane.getPrefWidth() - 20);

		//Nome do botão de entrada
		btnEntrar.setLayoutX(10);
		btnEntrar.setLayoutY(115);
		//Botão de entrada
		btnEntrar.setPrefHeight(20);
		btnEntrar.setPrefWidth((pane.getPrefWidth() - 30) / 2);

		//Nome do botão de saída
		btnSair.setLayoutX(btnEntrar.getPrefWidth() + 20);
		btnSair.setLayoutY(115);
		//Botão de saída 
		btnSair.setPrefHeight(20);
		btnSair.setPrefWidth((pane.getPrefWidth() - 30) / 2);

		//Nome do botão de cadastro
		btnCadastrar.setLayoutX(10);
		btnCadastrar.setLayoutY(145);
		//Botão de cadastro
		btnCadastrar.setPrefHeight(20);
		btnCadastrar.setPrefWidth(pane.getPrefWidth() - 20);
	}

	private EventHandler<ActionEvent> entrar() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					if (txtUsuario.getText().isBlank()) {
						AlertaFX.alerta("Usuário em branco!");
						return;
					}
					if (txtSenha.getText().isBlank()) {
						AlertaFX.alerta("Senha em branco!");
						return;
					}

					Pessoa usuarioBD = new PessoaDAO().getNomeUsuario(txtUsuario.getText());

					if (usuarioBD == null) {
						AlertaFX.alerta("Usuário ou senha inválidos!");
						return;
					}

					if (!usuarioBD.getSenha().contentEquals(txtSenha.getText())) {
						AlertaFX.alerta("Usuário ou senha inválidos!");
						return;
					}

					new MainFX(txtUsuario.getText()).start(stage);
				} catch (Exception e) {
					AlertaFX.erro("Não foi possível iniciar a tela principal!");
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
					AlertaFX.erro("Não foi possível iniciar a tela de cadastro de jogador!");
				}
			}
		};
	}
}