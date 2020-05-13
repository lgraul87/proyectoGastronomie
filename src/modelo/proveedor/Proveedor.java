package modelo.proveedor;

import modelo.limitdb.LimitsDB;

public class Proveedor implements IProveedor, LimitsDB {
	private String sNombreProveedor; // PK
	private String sTelefono; // NN
	private String sCorreoElectronico;
	private String sDireccion;// NN
	private TipoProveedor oTipoProveedor; // FK

	public Proveedor(String sNombreProveedor, String sTelefono, String sCorreoElectronico, String sDireccion,
			TipoProveedor oTipoProveedor) {

		setsNombreProveedor(sNombreProveedor);
		setsTelefono(sTelefono);
		setsCorreoElectronico(sCorreoElectronico);
		setsDireccion(sDireccion);
		setoTipoProveedor(oTipoProveedor);
	}

	public Proveedor(String sNombreProveedor, String sTelefono, String sDireccion, TipoProveedor oTipoProveedor) {
		setsNombreProveedor(sNombreProveedor);
		setsTelefono(sTelefono);
		setsDireccion(sDireccion);
		setoTipoProveedor(oTipoProveedor);
	}

	public Proveedor(String sNombreProveedor) {
		setsNombreProveedor(sNombreProveedor);

	}

	@Override
	public String getsNombreProveedor() {
		return this.sNombreProveedor;
	}

	private boolean setsNombreProveedor(String sNombreProveedor) { // PK
		boolean bValido = false;
		if (sNombreProveedor != null && sNombreProveedor.length() > MIN_CHAR_0
				&& sNombreProveedor.length() <= MAX_CHAR_30) {
			this.sNombreProveedor = sNombreProveedor;
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
		if (sTelefono != null && sTelefono.length() == CHAR_TELEFONO_9) {
			this.sTelefono = sTelefono;
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
	public String getsDireccion() {
		return this.sDireccion;
	}

	@Override
	public boolean setsDireccion(String sDireccion) {
		boolean bValido = false;
		if (sDireccion != null && sDireccion.length() > MIN_CHAR_0 && sDireccion.length() <= MAX_CHAR_50) {
			this.sDireccion = sDireccion;
			bValido = true;
		}
		return bValido;
	}

	@Override
	public TipoProveedor getoTipoProveedor() {
		return this.oTipoProveedor;
	}

	@Override
	public boolean setoTipoProveedor(TipoProveedor oTipoProveedor) {
		boolean bValido = false;
		if (oTipoProveedor != null && oTipoProveedor.checkTipoProveedor()) {
			this.oTipoProveedor = oTipoProveedor;
			bValido = true;
		}
		return bValido;
	}

	@Override
	public boolean checkProveedor() {
		boolean bValido = false;
		if (this.sNombreProveedor != null && this.sDireccion != null && this.sTelefono != null
				&& this.oTipoProveedor != null && oTipoProveedor.checkTipoProveedor()) {
			bValido = true;
		}
		return bValido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sNombreProveedor == null) ? 0 : sNombreProveedor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Proveedor other = (Proveedor) obj;
		if (this != null && other != null && this.checkProveedor() && other.checkProveedor()
				&& this.oTipoProveedor.checkTipoProveedor() && other.oTipoProveedor.checkTipoProveedor()
				&& this.sNombreProveedor.equals(other.sNombreProveedor)) {
			bIgual = true;
		}
		return bIgual;
	}

	@Override
	public String toString() {
		String sResultado = "";
		if (this.sCorreoElectronico != null) {
			sResultado = "  --email: " + this.sCorreoElectronico;
		}
		return "  --Proveedor: " + this.sNombreProveedor + "  --TipoProveedor: " + this.oTipoProveedor + "  --Telefono="
				+ this.sTelefono + "  --Direccion: " + this.sDireccion + sResultado;
	}

}
