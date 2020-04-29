package modelo;

public class Usuario {
	private String sNombreUsuario;// NN
	private String sDni;// PK
	private String sApellidos;// NN
	private int iTelefono;
	private String sCorreoElectronico;
	private TipoUsuario oTipoUsuario;// FK
}
