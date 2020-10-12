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

public class CadastrarPessoaFX extends Application {

	private Stage stage;
	private TextField txtNome;
	private TextField txtNomeUsuario;
	private PasswordField txtSenha1;
	private PasswordField txtSenha2;
	private Button btnVoltar;
	private Button btnCadastrar;
	private Pane pane;
	private TextField txtDataNascimento;
	private TextField txtSexo;

	@Override
	public void start(Stage stage) throws Exception { // o palco vem por parâmetro

		// ATENÇÃO: SEMPRE IMPORTAR OS COMPONENTES DO PACOTE JAVAFX!!!
		this.stage = stage;
		initComponentes();
		configLayout();

		Scene scene = new Scene(pane);
		btnVoltar.requestFocus(); // precisa ser depois de inicializar a cena, focar no botão de entrada

		stage.setScene(scene);
		stage.setTitle("Registro de uma nova pessoa");
		stage.setResizable(false);
		stage.show();
	}

	private void initComponentes() {//Função para inicializar os componentes
		txtNome = new TextField();
		txtNome.setPromptText("Digite aqui seu nome");

		//Entrada de texto nome do usuário
		txtNomeUsuario = new TextField();
		txtNomeUsuario.setPromptText("Digite aqui seu nome de usuário");
		
		//Entrada de texto senha
		txtSenha1 = new PasswordField();
		txtSenha1.setPromptText("Digite aqui sua senha");

		txtSenha2 = new PasswordField();
		txtSenha2.setPromptText("Confirme sua senha");
		
		txtDataNascimento = new TextField();
		txtDataNascimento.setPromptText("Digite aqui sua data de nascimento");
		
		txtSexo = new TextField();
		txtSexo.setPromptText("Digite aqui o seu sexo");
		
		//Botões
		btnVoltar = new Button("Voltar");
		btnVoltar.setOnAction(voltar());

		btnCadastrar = new Button("Cadastrar");
		btnCadastrar.setOnAction(cadastrar());

		pane = new AnchorPane();
		pane.setPrefSize(320, 285);
		pane.getChildren().addAll(txtNome,txtNomeUsuario,txtSenha1,txtSenha2,btnCadastrar,btnVoltar,txtDataNascimento,txtSexo);

	}

	private void configLayout() {
		txtNome.setLayoutX(10);
		txtNome.setLayoutY(10);
		txtNome.setPrefHeight(30);
		txtNome.setPrefWidth(pane.getPrefWidth()-20);
		
		txtNomeUsuario.setLayoutX(10);
		txtNomeUsuario.setLayoutY(50);
		txtNomeUsuario.setPrefHeight(30);
		txtNomeUsuario.setPrefWidth(pane.getPrefWidth()-20);
		
		txtSenha1.setLayoutX(10);
		txtSenha1.setLayoutY(90);
		txtSenha1.setPrefHeight(30);
		txtSenha1.setPrefWidth(pane.getPrefWidth()-20);
		
		txtSenha2.setLayoutX(10);
		txtSenha2.setLayoutY(130);
		txtSenha2.setPrefHeight(30);
		txtSenha2.setPrefWidth(pane.getPrefWidth()-20);
		
		txtDataNascimento.setLayoutX(10);
		txtDataNascimento.setLayoutY(170);
		txtDataNascimento.setPrefHeight(30);
		txtDataNascimento.setPrefWidth(pane.getPrefWidth()-20);
		
		txtSexo.setLayoutX(10);
		txtSexo.setLayoutY(210);
		txtSexo.setPrefHeight(30);
		txtSexo.setPrefWidth(pane.getPrefWidth()-20);
		
		btnCadastrar.setLayoutX(10);
		btnCadastrar.setLayoutY(250);
		btnCadastrar.setPrefHeight(20);//altura
		btnCadastrar.setPrefWidth((pane.getPrefWidth()-30)/2);//largura
		
		btnVoltar.setLayoutX(btnCadastrar.getPrefWidth()+20);
		btnVoltar.setLayoutY(250);
		btnVoltar.setPrefHeight(20);
		btnVoltar.setPrefWidth((pane.getPrefWidth()-30)/2);
	}
	
	private EventHandler<ActionEvent> voltar(){
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				abrirJanelaLogin();
			}
		};
	}
	
	private EventHandler<ActionEvent> cadastrar() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(txtNome.getText().isBlank()) {
					AlertaFX.alerta("Nome em branco!");
					return;
				}
				if(txtNomeUsuario.getText().isBlank()) {
					AlertaFX.alerta("Nome de usuário em branco!");
					return;
				}
				if(txtSenha1.getText().isBlank()) {
					AlertaFX.alerta("Senha em branco!");
					return;
				}
				if(txtSenha2.getText().isBlank()) {
					AlertaFX.alerta("Confirmação da senha em branco!");
					return;
				}
				if(txtDataNascimento.getText().isBlank()) {
					AlertaFX.alerta("Data de nascimento em branco!");
					return;
				}
				if(txtSexo.getText().isBlank()) {
					AlertaFX.alerta("Sexo em branco!");
					return;
				}
				
				new PessoaDAO().adicionar(new Pessoa(null, txtNome.getText(), txtNomeUsuario.getText(), txtSenha1.getText(), txtDataNascimento.getText(), txtSexo.getText()));
				
				AlertaFX.info("Usuário cadastrado com sucesso!");
				
				abrirJanelaLogin();
			}
		};
	}

	private void abrirJanelaLogin() {
		try {
			new LoginFX().start(stage);
		}catch(Exception e) {
			AlertaFX.erro("Não foi possível iniciar a tela de login!");
		}
	}
}















