package modelo.proveedor;

import modelo.limitdb.LimitsDB;

public class TipoProveedor implements ITipoProveedor,LimitsDB {
	private String sNombreTipoProveedor; // PK

	public TipoProveedor(String sNombreTipoProveedor) {
		setsNombreTipoProveedor(sNombreTipoProveedor);
	}
	@Override
	public String getsNombreTipoProveedor() {
		return this.sNombreTipoProveedor;
	}

	private boolean setsNombreTipoProveedor(String sNombreTipoProveedor) {  //PK
		boolean bValido = false;
		if (sNombreTipoProveedor != null && sNombreTipoProveedor.length() > MIN_CHAR_0 && sNombreTipoProveedor.length() <= MAX_CHAR_15) {
			this.sNombreTipoProveedor = sNombreTipoProveedor;
			bValido = true;
		}
		return bValido;
	}
	@Override
	public boolean checkTipoProveedor() {
		boolean bValido = false;
		if (this.sNombreTipoProveedor != null) {
			bValido = true;
		}
		return bValido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sNombreTipoProveedor == null) ? 0 : sNombreTipoProveedor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		TipoProveedor other = (TipoProveedor) obj;
		if (this != null && other != null && this.checkTipoProveedor() && other.checkTipoProveedor()
				&& this.sNombreTipoProveedor.equals(other.sNombreTipoProveedor)) {
			bIgual = true;
		}
		return bIgual;
	}

	@Override
	public String toString() {
		return "  --Tipo de Proveedor: " + this.sNombreTipoProveedor;
	}

}
