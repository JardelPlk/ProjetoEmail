package steam.ihc;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertaFX {
	
	public static void info(String mensagem) {//Passando a mensagem que ir� aparecer nos alertas
		Alert alerta = new Alert(AlertType.INFORMATION);//Cria��o do objeto, e o tipo do icone que aparecer�: INFORMATION
		alerta.setTitle("Informa��o");//T�tulo do alerta
		alerta.setHeaderText(null);//
		alerta.setContentText(mensagem);//Mensagem que foi passado por par�metro
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
