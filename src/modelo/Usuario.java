package modelo;

public class Usuario implements LimitsDB {
	private String sNombreUsuario;// NN
	private String sDni;// PK
	private String sApellidos;// NN
	private int iTelefono;
	private String sCorreoElectronico;
	private TipoUsuario oTipoUsuario;// FK

	public Usuario(String sNombreUsuario, String sDni, String sApellidos, int iTelefono, String sCorreoElectronico,
			TipoUsuario oTipoUsuario) {

		this.sNombreUsuario = sNombreUsuario;
		this.sDni = sDni;
		this.sApellidos = sApellidos;
		this.iTelefono = iTelefono;
		this.sCorreoElectronico = sCorreoElectronico;
		this.oTipoUsuario = oTipoUsuario;
	}

	public Usuario(String sNombreUsuario, String sDni, String sApellidos, String sCorreoElectronico,
			TipoUsuario oTipoUsuario) {

		this.sNombreUsuario = sNombreUsuario;
		this.sDni = sDni;
		this.sApellidos = sApellidos;
		this.sCorreoElectronico = sCorreoElectronico;
		this.oTipoUsuario = oTipoUsuario;
	}

	public Usuario(String sNombreUsuario, String sDni, String sApellidos, int iTelefono, TipoUsuario oTipoUsuario) {

		this.sNombreUsuario = sNombreUsuario;
		this.sDni = sDni;
		this.sApellidos = sApellidos;
		this.iTelefono = iTelefono;
		this.oTipoUsuario = oTipoUsuario;
	}

	public Usuario(String sNombreUsuario, String sDni, String sApellidos, TipoUsuario oTipoUsuario) {

		this.sNombreUsuario = sNombreUsuario;
		this.sDni = sDni;
		this.sApellidos = sApellidos;
		this.oTipoUsuario = oTipoUsuario;
	}

	public String getsNombreUsuario() {
		return this.sNombreUsuario;
	}

	public boolean setsNombreUsuario(String sNombreUsuario) {
		boolean bValido = false;
		if (sNombreUsuario != null && sNombreUsuario.length() <= MAX_CHAR_20) {
			this.sNombreUsuario = sNombreUsuario;
		}
		return bValido;
	}

	public String getsDni() {
		return this.sDni;
	}

	private boolean setsDni(String sDni) {// PK
		boolean bValido = false;
		if (sDni != null && sDni.length() == MAX_CHAR_9) {
			this.sDni = sDni;
		}
		return bValido;
	}

	public String getsApellidos() {
		return this.sApellidos;
	}

	public boolean setsApellidos(String sApellidos) {
		boolean bValido = false;
		if (sApellidos != null && sApellidos.length() <= MAX_CHAR_20) {
			this.sApellidos = sApellidos;
		}
		return bValido;
	}

	public int getiTelefono() {
		return this.iTelefono;
	}

	public boolean setiTelefono(int iTelefono) {
		boolean bValido = false;
		if (iTelefono >= MIN_TELEFONO && iTelefono <= MAX_TELEFONO) {
			this.iTelefono = iTelefono;
		} else {
			this.iTelefono = -1;
		}
		return bValido;
	}

	public String getsCorreoElectronico() {
		return this.sCorreoElectronico;
	}

	public boolean setsCorreoElectronico(String sCorreoElectronico) {
		boolean bValido = false;
		if (sCorreoElectronico != null && sCorreoElectronico.length() <= MAX_CHAR_30
				&& sCorreoElectronico.contains("@")) {
			this.sCorreoElectronico = sCorreoElectronico;
		}
		return bValido;
	}

	public TipoUsuario getoTipoUsuario() {
		return this.oTipoUsuario;
	}

	public boolean setoTipoUsuario(TipoUsuario oTipoUsuario) {
		boolean bValido = false;
		if (oTipoUsuario != null) {
			this.oTipoUsuario = oTipoUsuario;
		}
		return bValido;
	}

	public boolean checkUsuario() {
		boolean bValido = false;
		if (this.sNombreUsuario != null && this.sApellidos != null && this.sDni != null && this.oTipoUsuario != null) {
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

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Usuario other = (Usuario) obj;
		if (checkUsuario()&& other.checkUsuario()&& this.sDni.equals(other.sDni)) {
			bIgual = true;
		}
		return bIgual; 
	}

	@Override
	public String toString() {
		return "  --Usuario: "+this.oTipoUsuario.ge + this.sNombreUsuario +" "+this.sApellidos+"\n"
				+ "  --Dni: " + this.sDni + "  --Telefono: " + this.iTelefono+"  --email: "+this.sCorreoElectronico
				+ ", iTelefono=" + iTelefono + ", sCorreoElectronico=" + sCorreoElectronico + ", oTipoUsuario="
				+ oTipoUsuario + "]";
	}
	
}
