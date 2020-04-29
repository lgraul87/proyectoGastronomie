package modelo;

import java.util.Date;

public interface IPago {
	public int getiIdPago();

	public Date getdFecha();

	public boolean setdFecha(Date dFecha);

	public TipoPago getoTipoPago();

	public boolean setoTipoPago(TipoPago oTipoPago);

	public boolean checkPago();

	public int hashCode();

	public boolean equals(Object obj);

	public String toString();

}
