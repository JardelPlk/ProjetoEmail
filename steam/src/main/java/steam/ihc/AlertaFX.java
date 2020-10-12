package steam.ihc;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertaFX {
	
	public static void info(String mensagem) {//Passando a mensagem que irá aparecer nos alertas
		Alert alerta = new Alert(AlertType.INFORMATION);//Criação do objeto, e o tipo do icone que aparecerá: INFORMATION
		alerta.setTitle("Informação");//Título do alerta
		alerta.setHeaderText(null);//
		alerta.setContentText(mensagem);//Mensagem que foi passado por parâmetro
		alerta.showAndWait();
	}
	
	public static void alerta(String mensagem) {
		Alert alerta = new Alert(AlertType.WARNING);
		alerta.setTitle("Aviso");
		alerta.setHeaderText(null);
		alerta.setContentText(mensagem);
		alerta.showAndWait();
	}
	
	public static void erro(String mensagem) {
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Erro");
		alerta.setHeaderText(null);
		alerta.setContentText(mensagem);
		alerta.showAndWait();
	}
	
}
