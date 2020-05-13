package modelo.genero;

import modelo.limitdb.LimitsDB;

public class Producto implements IProducto, LimitsDB {
	private String sNombreProducto; // PK
	private float fPrecio; // NN
	private short shStock; // NN
	private String sTipo;

	public Producto(String sNombreProducto, float fPrecio, short shStock, String sTipo) {

		setsNombreProducto(sNombreProducto);
		setfPrecio(fPrecio);
		setShStock(shStock);
		setsTipo(sTipo);
	}
	
	public Producto(String sNombreProducto) {

		setsNombreProducto(sNombreProducto);
		
	}

	@Override
	public String getsNombreProducto() {
		return this.sNombreProducto;
	}

	private boolean setsNombreProducto(String sNombreProducto) { // PK
		boolean bValido = false;
		if (sNombreProducto != null && sNombreProducto.length() > MIN_CHAR_0
				&& sNombreProducto.length() <= MAX_CHAR_15) {
			this.sNombreProducto = sNombreProducto;
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
		if (shStock > MIN_INT_0 && shStock <= MAX_INT_10000000) {
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
		if (sTipo != null && sTipo.equals(CARTA_BEBIDA) || sTipo.equals(CARTA_COMIDA) || sTipo.equals(DERIVADOS)) {
			this.sTipo = sTipo;
			bValido = true;

		}
		return bValido;
	}

	@Override
	public boolean checkProducto() {
		boolean bValido = false;
		if (this.sNombreProducto != null && this.fPrecio != -1 && this.shStock != -1) {
			bValido = true;
		}
		return bValido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sNombreProducto == null) ? 0 : sNombreProducto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Producto other = (Producto) obj;
		if (this != null && other != null && this.checkProducto() && other.checkProducto()
				&& this.sNombreProducto.equals(other.sNombreProducto)) {
			bIgual = true;
		}
		return bIgual;
	}

	@Override
	public String toString() {
		return "  --Material: " + this.sNombreProducto + "  --Precio: " + this.fPrecio + "  --Stock: " + this.shStock;
	}

}
