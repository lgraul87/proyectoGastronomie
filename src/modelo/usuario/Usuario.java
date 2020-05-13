package modelo.usuario;

import modelo.limitdb.LimitsDB;

public class Usuario implements IUsuario, LimitsDB {

	private String sNombre;// NN
	private String sApellidos;// NN
	private String sDni;// PK
	private String sTelefono;
	private String sCorreoElectronico;
	private String sContrasenia;
	private TipoUsuario oTipoUsuario;// FK

	public Usuario(String sNombre, String sDni, String sApellidos, String sTelefono, String sCorreoElectronico,
			String sContrasenia, TipoUsuario oTipoUsuario) {

		setsNombre(sNombre);
		setsDni(sDni);
		setsApellidos(sApellidos);
		setsTelefono(sTelefono);
		setsCorreoElectronico(sCorreoElectronico);
		setsContrasenia(sContrasenia);
		setoTipoUsuario(oTipoUsuario);

	}

	public Usuario(String sNombre, String sDni, String sApellidos,String sContrasenia, TipoUsuario oTipoUsuario) {

		setsNombre(sNombre);
		setsDni(sDni);
		setsApellidos(sApellidos);
		setsContrasenia(sContrasenia);
		setoTipoUsuario(oTipoUsuario);
	}

	public Usuario(String sDni) {
		setsDni(sDni);

	}

	@Override
	public String getsNombre() {
		return this.sNombre;
	}

	@Override
	public boolean setsNombre(String sNombre) {
		boolean bValido = false;
		if (sNombre != null && sNombre.length() > MIN_CHAR_0 && sNombre.length() <= MAX_CHAR_20) {
			this.sNombre = sNombre;
			bValido = true;
		}
		return bValido;
	}

	@Override
	public String getsDni() {
		return this.sDni;
	}

	private boolean setsDni(String sDni) {// PK
		boolean bValido = false;
		if (sDni != null && sDni.length() == CHAR_DNI_9) {
			this.sDni = sDni;
			bValido = true;
		}
		return bValido;
	}

	@Override
	public String getsTelefono() {
		return this.sTelefono;
	}

	@Override
	public boolean setsTelefono(String sTelefono) {
		boolean bValido = false;
		if (sTelefono != null && sTelefono.length() == CHAR_DNI_9) {
			this.sTelefono = sTelefono;
			bValido = true;
		}
		return bValido;
	}

	@Override
	public String getsApellidos() {
		return this.sApellidos;
	}

	@Override
	public boolean setsApellidos(String sApellidos) {
		boolean bValido = false;
		if (sApellidos != null && sApellidos.length() > MIN_CHAR_0 && sApellidos.length() <= MAX_CHAR_20) {
			this.sApellidos = sApellidos;
			bValido = true;

		}
		return bValido;
	}

	@Override
	public String getsCorreoElectronico() {
		return this.sCorreoElectronico;
	}

	@Override
	public boolean setsCorreoElectronico(String sCorreoElectronico) {
		boolean bValido = false;
		if (sCorreoElectronico != null && sCorreoElectronico.length() <= MAX_CHAR_30
				&& sCorreoElectronico.contains("@")) {
			this.sCorreoElectronico = sCorreoElectronico;
			bValido = true;

		}
		return bValido;
	}
	@Override
	public String getsContrasenia() {
		return this.sContrasenia;
	}
	@Override
	public boolean setsContrasenia(String sContrasenia) {
		boolean bValido = false;
		if (sContrasenia != null && sContrasenia.length() > MIN_CHAR_0 && sContrasenia.length() <= MAX_CHAR_250) {
			this.sContrasenia = sContrasenia;
			bValido = true;
		}
		return bValido;
	}

	@Override
	public TipoUsuario getoTipoUsuario() {
		return this.oTipoUsuario;
	}

	@Override
	public boolean setoTipoUsuario(TipoUsuario oTipoUsuario) {
		boolean bValido = false;
		if (oTipoUsuario != null) {
			this.oTipoUsuario = oTipoUsuario;
			bValido = true;

		}
		return bValido;
	}

	// Mirar el check de tabla inferior

	@Override
	public boolean checkUsuario() {
		boolean bValido = false;
		if (this.sNombre != null && this.sApellidos != null && this.sDni != null && this.oTipoUsuario != null
				&& this.sContrasenia != null && oTipoUsuario.checkTipoUsuario()) {
			bValido = true;
		}
		return bValido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sDni == null) ? 0 : sDni.hashCode());
		return result;
	}

	// Mirar this!=null && other !=null

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Usuario other = (Usuario) obj;
		if (this != null && other != null && this.checkUsuario() && other.checkUsuario()
				&& this.sDni.equals(other.sDni)) {
			bIgual = true;
		}
		return bIgual;
	}

	@Override
	public String toString() {
		String sResultado = "";
		if (this.sTelefono != null) {
			sResultado += "\n  --Telefono: " + this.sTelefono;
		}
		if (this.sCorreoElectronico != null) {
			sResultado += "\n  --email: " + this.sCorreoElectronico;
		}
		return "  --Usuario: " + this.oTipoUsuario.toString() + "\n" + "  --Datos Personales: " + "\n" + this.sNombre
				+ " " + this.sApellidos + "  --Dni: " + this.sDni + sResultado;

	}

}
