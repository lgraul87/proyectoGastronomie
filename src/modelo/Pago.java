package modelo;

import java.util.Date;

public class Pago implements IPago, LimitsDB {

	private int iIdPago; // PK
	private Date dFecha; // NN
	private TipoPago oTipoPago; // FK

	public Pago(int iIdPago, Date dFecha, TipoPago oTipoPago) {
		setiIdPago(iIdPago);
		setdFecha(dFecha);
		setoTipoPago(oTipoPago);

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
	public TipoPago getoTipoPago() {
		return this.oTipoPago;
	}
	@Override
	public boolean setoTipoPago(TipoPago oTipoPago) {
		boolean bValido = false;
		if (oTipoPago != null) {
			this.oTipoPago = oTipoPago;
			bValido = true;
		}
		return bValido;
	}
	@Override
	public boolean checkPago() {
		boolean bValido = false;
		if (this.iIdPago != -1 && this.dFecha != null && this.oTipoPago != null) {
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
				+ "  --Type: " + this.oTipoPago.toString() 
				//
				+ "  --Fecha: " + this.dFecha + "\n";
	}

}
