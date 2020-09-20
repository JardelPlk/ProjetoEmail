package steam.entidades;

public class Destinatario {
	private Integer idEmail;
	private Integer idDestinatario;
	
	public Destinatario(Integer idEmail, Integer idDestinatario) {
		this.idEmail = idEmail;
		this.idDestinatario = idDestinatario;
	}
	
	public Integer getIdEmail() {
		return idEmail;
	}
	public void setIdEmail(Integer idEmail) {
		this.idEmail = idEmail;
	}
	public Integer getIdDestinatario() {
		return idDestinatario;
	}
	public void setIdDestinatario(Integer idDestinatario) {
		this.idDestinatario = idDestinatario;
	}
}
