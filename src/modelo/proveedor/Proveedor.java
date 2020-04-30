package modelo.proveedor;

import modelo.LimitsDB;

public class Proveedor implements LimitsDB {
	private String sNombreProveedor; // PK
	private int iTelefono; // NN
	private String sCorreoElectronico;
	private String sDireccion;// NN
	private TipoProveedor oTipoProveedor; // FK

	public Proveedor(String sNombreProveedor, int iTelefono, String sCorreoElectronico, String sDireccion,
			TipoProveedor oTipoProveedor) {
		this.sNombreProveedor = sNombreProveedor;
		this.iTelefono = iTelefono;
		this.sCorreoElectronico = sCorreoElectronico;
		this.sDireccion = sDireccion;
		this.oTipoProveedor = oTipoProveedor;
	}

	public Proveedor(String sNombreProveedor, int iTelefono, String sDireccion, TipoProveedor oTipoProveedor) {
		this.sNombreProveedor = sNombreProveedor;
		this.iTelefono = iTelefono;
		this.sDireccion = sDireccion;
		this.oTipoProveedor = oTipoProveedor;
	}

	public String getsNombreProveedor() {
		return this.sNombreProveedor;
	}

	public boolean setsNombreProveedor(String sNombreProveedor) {
		boolean bValido = false;
		if (sNombreProveedor != null && sNombreProveedor.length() <= MAX_CHAR_30) {
			this.sNombreProveedor = sNombreProveedor;
			bValido = true;
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
			bValido = true;
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
		if (sCorreoElectronico != null && sCorreoElectronico.length() <= MAX_CHAR_30 && sCorreoElectronico.contains("@")) {
			this.sCorreoElectronico = sCorreoElectronico;
			bValido = true;
		}
		return bValido;
	}

	public String getsDireccion() {
		return this.sDireccion;
	}

	public boolean setsDireccion(String sDireccion) {
		boolean bValido = false;
		if (sDireccion != null && sDireccion.length() <= MAX_CHAR_50) {
			this.sDireccion = sDireccion;
			bValido = true;
		}
		return bValido;
	}

	public TipoProveedor getoTipoProveedor() {
		return this.oTipoProveedor;
	}

	public boolean setoTipoProveedor(TipoProveedor oTipoProveedor) {
		boolean bValido = false;
		if (oTipoProveedor != null) {
			this.oTipoProveedor = oTipoProveedor;
			bValido = true;
		}
		return bValido;
	}

	public boolean checkProveedor() {
		boolean bValido = false;
		if (this.sNombreProveedor != null && this.sDireccion != null && this.iTelefono != -1
				&& this.oTipoProveedor != null) {
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
		if (this.checkProveedor() && other.checkProveedor() && this.sNombreProveedor.equals(other.sNombreProveedor)) {
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
				+ this.iTelefono + "  --Direccion: " + this.sDireccion + sResultado;
	}

}
