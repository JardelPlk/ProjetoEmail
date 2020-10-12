package steam.ihc;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import steam.bd.EmailPessoaDAO;
import steam.bd.PessoaDAO;
import steam.entidades.Email;
import steam.entidades.Pessoa;

public class EnvioEmailFx extends Application{

	private Stage stage;
	private Pane pane;
	private Integer idUsuarioLogado;
	private Button btnVoltar;
	private TextField txtAssunto;
	private TextField txtMensagem;
	private TextField txtData;
	private Label lblDestinatario;
	private ComboBox<String> cmbDestinatario;
	private Button btnEnviarEmail;
	private Integer idDestinatario;
	
	public EnvioEmailFx(Integer idUsuarioLogado) {
		this.idUsuarioLogado = idUsuarioLogado;
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		
		initComponentes();
		
		configLayout();
		
		Scene scene = new Scene(pane);
		btnVoltar.requestFocus();
		
		stage.setScene(scene);
		stage.setTitle("Envio de um novo email");
		stage.setResizable(false);
		stage.show();
		
	}

	private void initComponentes() {
		txtAssunto = new TextField();
		txtAssunto.setPromptText("Assunto do email");
		
		txtMensagem = new TextField();
		txtMensagem.setPromptText("Mensagem do email");
		
		txtData = new TextField();
		txtData.setPromptText("Data do email");
		
		lblDestinatario = new Label("Nome do destinatário:");
		cmbDestinatario = new ComboBox<>();
		cmbDestinatario.setItems(FXCollections.observableArrayList(geraListaDestinatarios()));
		cmbDestinatario.getSelectionModel().selectFirst();
		
		btnEnviarEmail = new Button("Enviar email");
		btnEnviarEmail.setOnAction(enviarEmail());
		
		btnVoltar = new Button("Voltar");
		btnVoltar.setOnAction(voltar());
		
		pane = new AnchorPane();
		pane.getChildren().addAll(txtAssunto,txtData,txtMensagem,lblDestinatario,btnEnviarEmail,btnVoltar,cmbDestinatario);
	}
	
	private void configLayout() {
		pane.setPrefSize(320, 245);
		
		txtAssunto.setLayoutX(10);
		txtAssunto.setLayoutY(10);
		txtAssunto.setPrefHeight(30);
		txtAssunto.setPrefWidth(pane.getPrefWidth()-20);
		
		txtMensagem.setLayoutX(10);
		txtMensagem.setLayoutY(50);
		txtMensagem.setPrefHeight(30);
		txtMensagem.setPrefWidth(pane.getPrefWidth()-20);
		
		txtData.setLayoutX(10);
		txtData.setLayoutY(90);
		txtData.setPrefHeight(30);
		txtData.setPrefWidth(pane.getPrefWidth()-20);
		
		lblDestinatario.setLayoutX(10);
		lblDestinatario.setLayoutY(130);
		
		cmbDestinatario.setLayoutX(10);
		cmbDestinatario.setLayoutY(150);
		cmbDestinatario.setPrefHeight(30);
		cmbDestinatario.setPrefWidth(pane.getPrefWidth()-20);
		
		btnEnviarEmail.setLayoutX(10);
		btnEnviarEmail.setLayoutY(200);
		btnEnviarEmail.setPrefHeight(20);
		btnEnviarEmail.setPrefWidth((pane.getPrefWidth()-30)/2);
		
		btnVoltar.setLayoutX(btnEnviarEmail.getPrefWidth()+20);
		btnVoltar.setLayoutY(200);
		btnVoltar.setPrefHeight(20);
		btnVoltar.setPrefWidth((pane.getPrefWidth()-30)/2);
	}
	
	private List<String> geraListaDestinatarios() {
		List<String> retorno = new ArrayList<String>();
		List<Pessoa> pessoas = new PessoaDAO().todos();
		for(Pessoa pessoa : pessoas)
			retorno.add(pessoa.getNomeUsuario());
		return retorno;
	}

	private EventHandler<ActionEvent> voltar() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				abrirJanelaPrincipal();
			}
		};
	}

	private EventHandler<ActionEvent> enviarEmail() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(txtAssunto.getText().isBlank()) {
					AlertaFX.alerta("Assunto em branco!");
					return;
				}
				if(txtMensagem.getText().isBlank()) {
					AlertaFX.alerta("Mensagem em branco!");
					return;
				}
				if(txtData.getText().isBlank()) {
					AlertaFX.alerta("Data do email em branco!");;
					return;
				}
				
				PessoaDAO dao = new PessoaDAO();
				List<Pessoa> pessoas = new ArrayList<Pessoa>();
				pessoas = dao.todos();
				for(int i = 0; i < pessoas.size(); i++) {
					if(pessoas.get(i).getNomeUsuario().contentEquals(cmbDestinatario.getSelectionModel().getSelectedItem())) {
						idDestinatario = pessoas.get(i).getId();
						break;
					}
				}
				
				Pessoa pessoa = new PessoaDAO().get(idDestinatario);
				
				Email email = new Email(null, idUsuarioLogado, txtAssunto.getText(), txtMensagem.getText(), txtData.getText());
				email.adicionarDestinatarios(pessoa);
				
				new EmailPessoaDAO().adicionar(email);
				
				AlertaFX.info("Email enviado com sucesso!");
				
				abrirJanelaPrincipal();
			}
		};
	}
	
	private void abrirJanelaPrincipal() {
		try {
			Pessoa pessoa = null;
			PessoaDAO dao = new PessoaDAO();
			pessoa = dao.get(idUsuarioLogado);
			
			new MainFX(pessoa.getNomeUsuario()).start(stage);
		}catch(Exception e) {
			AlertaFX.erro("Não foi possível iniciar a tela principal!");
		}
	}

}
