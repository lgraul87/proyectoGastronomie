package modelo;

public class MetodoPago implements IMetodoPago,LimitsDB {
	private String sNombrePago; // PK

	public MetodoPago(String sNombrePago) {
		setsNombrePago(sNombrePago);
	}
	@Override
	public String getsNombrePago() {
		return this.sNombrePago;
	}

	private boolean setsNombrePago(String sNombrePago) { // PK
		boolean bValido = false;
		if (sNombrePago != null && sNombrePago.length() <= MAX_CHAR_30) {
			this.sNombrePago = sNombrePago;

		}
		return bValido;
	}
	@Override
	public boolean checkMetodoPago() {
		boolean bValido = false;
		if (this.sNombrePago != null) {
			bValido = true;
		}
		return bValido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sNombrePago == null) ? 0 : sNombrePago.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		MetodoPago other = (MetodoPago) obj;
		if (this.checkMetodoPago() && other.checkMetodoPago() && this.sNombrePago.equals(other.sNombrePago)) {
			bIgual = true;
		}
		return bIgual;

	}

	@Override
	public String toString() {
		return "Metodo de Pago: " + this.sNombrePago;
	}

}
