package modelo;

public class TipoPago implements ITipoPago,LimitsDB {
	private String sNombreTipoPago;// PK

	public TipoPago(String sNombreTipoPago) {
		setsNombreTipoPago(sNombreTipoPago);
	}
	@Override
	public String getsNombreTipoPago() {
		return this.sNombreTipoPago;
	}

	private boolean setsNombreTipoPago(String sNombreTipoPago) {
		boolean bValido = false;
		if (sNombreTipoPago != null && sNombreTipoPago.length() <= MAX_CHAR_30) {
			this.sNombreTipoPago = sNombreTipoPago;
		}
		return bValido;
	}
	@Override
	public boolean checkTipoPago() {
		boolean bValido = false;
		if (this.sNombreTipoPago != null) {
			bValido = true;
		}
		return bValido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sNombreTipoPago == null) ? 0 : sNombreTipoPago.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		TipoPago other = (TipoPago) obj;
		if (this.checkTipoPago() && other.checkTipoPago() && this.sNombreTipoPago.equals(other.sNombreTipoPago)) {
			bIgual = true;
		}
		return bIgual;
	}

	@Override
	public String toString() {
		return "Type: " + this.sNombreTipoPago + "\n";
	}
	
}
