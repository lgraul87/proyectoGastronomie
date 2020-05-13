package modelo.usuario;

import modelo.limitdb.LimitsDB;

public class TipoUsuario implements ITipoUsuario, LimitsDB {

	private String sNombreTipoUsuario; // PK

	public TipoUsuario(String sNombreTipoUsuario) {
		setsNombreTipoUsuario(sNombreTipoUsuario);
	}

	@Override
	public String getsNombreTipoUsuario() {
		return this.sNombreTipoUsuario;
	}

	private boolean setsNombreTipoUsuario(String sNombreTipoUsuario) {
		boolean bValido = false;
		if (sNombreTipoUsuario != null && sNombreTipoUsuario.length() > MIN_CHAR_0
				&& sNombreTipoUsuario.length() <= MAX_CHAR_10) {
			this.sNombreTipoUsuario = sNombreTipoUsuario;
		}
		return bValido;
	}

	@Override
	public boolean checkTipoUsuario() {
		boolean bValido = false;
		if (this.sNombreTipoUsuario != null) {
			bValido = true;
		}
		return bValido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sNombreTipoUsuario == null) ? 0 : sNombreTipoUsuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = true;
		TipoUsuario other = (TipoUsuario) obj;
		if (this != null && other != null && this.checkTipoUsuario() && other.checkTipoUsuario()
				&& this.sNombreTipoUsuario.equals(other.sNombreTipoUsuario)) {
			bIgual = true;
		}
		return bIgual;
	}

	@Override
	public String toString() {
		return "Type: " + this.sNombreTipoUsuario;
	}

}
