package modelo.pago;

import java.util.Date;

import modelo.limitdb.LimitsDB;

public class Pago implements IPago, LimitsDB {

	private int iIdPago; // PK
	private Date dFecha; // NN
	private MetodoPago oMetodoPago; // FK

	public Pago(int iIdPago, Date dFecha, MetodoPago oMetodoPago) {
		setiIdPago(iIdPago);
		setdFecha(dFecha);
		setoMetodoPago(oMetodoPago);

	}
	@Override
	public int getiIdPago() {
		return this.iIdPago;
	}

	private boolean setiIdPago(int iIdPago) {
		boolean bValido = false;
		if (iIdPago >= MIN_INT_0 && iIdPago <= MAX_INT_10000000) {
			this.iIdPago = iIdPago;
			bValido = true;
		} else {
			this.iIdPago = -1;
		}
		return bValido;
	}
	@Override
	public Date getdFecha() {
		return this.dFecha;
	}
	@Override
	public boolean setdFecha(Date dFecha) {
		boolean bValido = false;
		if (dFecha != null) {
			this.dFecha = dFecha;
			bValido = true;
		}
		return bValido;
	}
	@Override
	public MetodoPago getoMetodoPago() {
		return this.oMetodoPago;
	}
	@Override
	public boolean setoMetodoPago(MetodoPago oMetodoPago) {
		boolean bValido = false;
		if (oMetodoPago != null) {
			this.oMetodoPago = oMetodoPago;
			bValido = true;
		}
		return bValido;
	}
	@Override
	public boolean checkPago() {
		boolean bValido = false;
		if (this.iIdPago != -1 && this.dFecha != null && this.oMetodoPago != null) {
			bValido = true;
		}
		return bValido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + iIdPago;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Pago other = (Pago) obj;
		if (this.checkPago() && other.checkPago() && this.iIdPago == other.iIdPago) {
			bIgual = true;
		}
		return bIgual;
	}

	@Override
	public String toString() {
		return "  -- Nº: " + this.iIdPago + "\n"
		//
				+ "  --Type: " + this.oMetodoPago.toString() 
				//
				+ "  --Fecha: " + this.dFecha + "\n";
	}

}
