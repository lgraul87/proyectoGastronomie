package modelo.genero;

import modelo.limitdb.LimitsDB;

public class Material implements IMaterial, LimitsDB {

	private String sNombreMaterial; // PK
	private float fPrecio; // NN
	private short shStock; // NN
	private String sTipo;// NN

	public Material(String sNombreMaterial, float fPrecio, short shStock, String sTipo) {

		setsNombreMaterial(sNombreMaterial);
		setfPrecio(fPrecio);
		setShStock(shStock);
		setsTipo(sTipo);
	}

	@Override
	public String getsNombreMaterial() {
		return this.sNombreMaterial;
	}

	private boolean setsNombreMaterial(String sNombreMaterial) { // PK
		boolean bValido = false;
		if (sNombreMaterial != null && sNombreMaterial.length() <= MAX_CHAR_15) {
			this.sNombreMaterial = sNombreMaterial;
			bValido = true;
		}
		return bValido;
	}

	@Override
	public float getfPrecio() {
		return this.fPrecio;
	}

	@Override
	public boolean setfPrecio(float fPrecio) {
		boolean bValido = false;
		if (fPrecio > MIN_FLOAT_0 && fPrecio <= MAX_FLOAT_3000) {
			this.fPrecio = fPrecio;
			bValido = true;
		} else {
			this.fPrecio = -1;
		}
		return bValido;
	}

	@Override
	public short getShStock() {
		return this.shStock;
	}

	@Override
	public boolean setShStock(short shStock) {
		boolean bValido = false;
		if (shStock > MIN_INT_0 && shStock <= MAX_INT_100) {
			this.shStock = shStock;
			bValido = true;
		} else {
			this.shStock = -1;
		}
		return bValido;
	}

	@Override
	public String getsTipo() {
		return this.sTipo;
	}

	@Override
	public boolean setsTipo(String sTipo) {
		boolean bValido = false;
		if (sTipo != null && sTipo.length() <= MAX_CHAR_30) {
			this.sTipo = sTipo;

		}
		return bValido;
	}

	@Override
	public boolean checkMaterial() {
		boolean bValido = false;
		if (this.sNombreMaterial != null && this.fPrecio != -1 && this.shStock != -1 && this.sTipo != null) {
			bValido = true;
		}
		return bValido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sNombreMaterial == null) ? 0 : sNombreMaterial.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Material other = (Material) obj;
		if (this.checkMaterial() && other.checkMaterial() && this.sNombreMaterial.equals(other.sNombreMaterial)) {
			bIgual = true;
		}
		return bIgual;
	}

	@Override
	public String toString() {
		return "  --Material: " + this.sNombreMaterial + "  --Precio: " + this.fPrecio + "  --Stock: " + this.shStock;
	}

}
