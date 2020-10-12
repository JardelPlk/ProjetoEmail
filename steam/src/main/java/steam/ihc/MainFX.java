package steam.ihc;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import steam.bd.EmailPessoaDAO;
import steam.bd.PessoaDAO;
import steam.entidades.Email;
import steam.entidades.Pessoa;

public class MainFX extends Application{

	private Stage stage;
	private Pane pane;
	private String usuarioLogado;
	private Integer idUsuarioLogado;
	private Button btnSair;
	private Button btnEnviarEmail;
	private Button btnExcluirEmail;
	private Button btnAlterarEmail;
	private ListView<String> listaEmail;
	private Integer idEmail;
	
	public MainFX(String usuarioLogado) {
		if(usuarioLogado.isBlank())
			usuarioLogado = "Erro - Nome de usuário em branco!";
		this.usuarioLogado = usuarioLogado;
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		//Pane pane = new AnchorPane();
		//pane.setPrefSize(640, 480);//Tamanho da janela comprimento x altura
		this.stage = stage;
		
		//Código para obter o id do usuario logado
		PessoaDAO dao = new PessoaDAO();
		List<Pessoa> pes = new ArrayList<Pessoa>();
		pes = dao.todos();
		for(int i = 0; i < pes.size(); i++) {
			if(usuarioLogado.contentEquals(pes.get(i).getNomeUsuario())) {
				idUsuarioLogado = pes.get(i).getId();
				break;
			}
		}
		
		initComponentes();
		configLayout();
		
		Scene scene = new Scene(pane);
		btnSair.requestFocus();//Focar neste botão
		
		stage.setScene(scene);
		stage.setTitle("Email de " + usuarioLogado);
		stage.setResizable(false);
		stage.show();
	}

	private void initComponentes() {
		listaEmail = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList(geraListaEmail());

		listaEmail.setItems(items);
		
		btnEnviarEmail = new Button("Enviar email");
		btnEnviarEmail.setOnAction(abrirEnvioEmail());
		
		btnAlterarEmail = new Button("Alterar email");
		btnAlterarEmail.setOnAction(abrirAlteracaoEmail());
		
		btnExcluirEmail = new Button("Excluir email");
		btnExcluirEmail.setOnAction(excluirEmail());
		
		btnSair = new Button("Sair");
		btnSair.setOnAction(sair());
		
		pane = new AnchorPane();
		pane.getChildren().addAll(listaEmail,btnAlterarEmail,btnEnviarEmail,btnExcluirEmail,btnSair);
	}

	private void configLayout() {
		pane.setPrefSize(640, 480);
		
		listaEmail.setLayoutX(10);
		listaEmail.setLayoutY(10);
		listaEmail.setPrefHeight(pane.getPrefHeight()-55);
		listaEmail.setPrefWidth(pane.getPrefWidth()-20);
		
		btnEnviarEmail.setLayoutX(pane.getPrefWidth()-630);
		btnEnviarEmail.setLayoutY(pane.getPrefHeight()-35);
		btnEnviarEmail.setPrefHeight(20);
		btnEnviarEmail.setPrefWidth(150);
		
		btnAlterarEmail.setLayoutX(pane.getPrefWidth()-455);
		btnAlterarEmail.setLayoutY(pane.getPrefHeight()-35);
		btnAlterarEmail.setPrefHeight(20);
		btnAlterarEmail.setPrefWidth(150);
		
		btnExcluirEmail.setLayoutX(pane.getPrefWidth()-280);
		btnExcluirEmail.setLayoutY(pane.getPrefHeight()-35);
		btnExcluirEmail.setPrefHeight(20);
		btnExcluirEmail.setPrefWidth(150);
		
		btnSair.setLayoutX(pane.getPrefWidth()-110);
		btnSair.setLayoutY(pane.getPrefHeight()-35);
		btnSair.setPrefHeight(20);
		btnSair.setPrefWidth(100);
	}
	
	private List<String> geraListaEmail(){
		List<String> retorno = new ArrayList<String>();
		List<Email> emails = new EmailPessoaDAO().todos();
		for(Email email : emails)
			retorno.add(email.getAssunto());
		return retorno;
	}
	
	private EventHandler<ActionEvent> abrirEnvioEmail() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					new EnvioEmailFx(idUsuarioLogado).start(stage);
				}catch(Exception e) {
					AlertaFX.erro("Não foi possível iniciar a tela de envio de email!");
				}
			}
		};
	}
	
	private void atualizarLista() {
		ObservableList<String> items = FXCollections.observableArrayList(geraListaEmail());
		listaEmail.setItems(items);
	}

	private EventHandler<ActionEvent> abrirAlteracaoEmail() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(listaEmail.getSelectionModel().isEmpty()) {
					AlertaFX.alerta("Selecione um email para alterar!");
					return;
				}
				
				List<Email> emails = new ArrayList<Email>();
				EmailPessoaDAO dao = new EmailPessoaDAO();
				emails = dao.todos();
				Email email = null;
				
				for(int i = 0; i < emails.size(); i++) {
					if(emails.get(i).getAssunto().contentEquals(listaEmail.getSelectionModel().getSelectedItem())) {
						idEmail = emails.get(i).getIdEmail();
						break;
					}
				}
				
				email = dao.get(idEmail);
				try {
					new AlterarEmailFX(idUsuarioLogado,email).start(stage);
				}catch (Exception e) {
					AlertaFX.erro("Não foi possível iniciar a tela de cadastro de um email!");
				}
			}
		};
	}
	
	private EventHandler<ActionEvent> excluirEmail() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(listaEmail.getSelectionModel().isEmpty()) {
					AlertaFX.alerta("Seleciona um email para ser excluído!");
					return;
				}
				
				EmailPessoaDAO dao = new EmailPessoaDAO();
				
				List<Email> listaEmail2 = new ArrayList<Email>();
				listaEmail2 = dao.todos();
				
				for(int i = 0; i < listaEmail2.size(); i++) {
					if(listaEmail2.get(i).getAssunto().contentEquals(listaEmail.getSelectionModel().getSelectedItem())) {
						idEmail = listaEmail2.get(i).getIdEmail();
						break;
					}
				}
				
				Email email = dao.get(idEmail);
				dao.remover(email);
				atualizarLista();
			}
		};
	}
	
	private EventHandler<ActionEvent> sair() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					new LoginFX().start(stage);
				}catch (Exception e) {
					AlertaFX.erro("Não foi possível iniciar a tela de login!");
				}
			}
		};
	}
}
