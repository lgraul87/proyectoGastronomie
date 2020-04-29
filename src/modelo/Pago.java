package modelo;

import java.util.Date;

public class Pago implements LimitsDB {

	private int iIdPago; // PK
	private Date dFecha; // NN
	private TipoPago oTipoPago; // FK

	public Pago(int iIdPago, Date dFecha, TipoPago oTipoPago) {
		setiIdPago(iIdPago);
		setdFecha(dFecha);
		setoTipoPago(oTipoPago);

	}

	public int getiIdPago() {
		return this.iIdPago;
	}

	private boolean setiIdPago(int iIdPago) {
		boolean bValido = false;
		if (iIdPago >= MIN_INT_0 && iIdPago <= MAX_INT_10000000) {
			this.iIdPago = iIdPago;
			bValido = true;
		}else {
			this.iIdPago =-1;
		}
		return bValido;
	}

	public Date getdFecha() {
		return this.dFecha;
	}

	public boolean setdFecha(Date dFecha) {
		boolean bValido = false;
		if (dFecha != null) {
			this.dFecha = dFecha;
			bValido = true;
		}
		return bValido;
	}

	public TipoPago getoTipoPago() {
		return this.oTipoPago;
	}

	public boolean setoTipoPago(TipoPago oTipoPago) {
		boolean bValido = false;
		if (oTipoPago != null) {
			this.oTipoPago = oTipoPago;
			bValido = true;
		}
		return bValido;
	}

	public boolean checkPago() {
		boolean bValido = false;
		if (this.iIdPago!=-1 && this.dFecha!=null && this.oTipoPago!=null) {
			bValido = true;
		}
		return bValido;
	}
}
