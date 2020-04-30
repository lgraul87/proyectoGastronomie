package modelo.pago;

import java.util.Date;

public interface IPago {

	public int getiIdPago();

	public Date getdFecha();

	public boolean setdFecha(Date dFecha);

	public MetodoPago getoMetodoPago();

	public boolean setoMetodoPago(MetodoPago oMetodoPago);

	public boolean checkPago();

	public int hashCode();

	public boolean equals(Object obj);

	public String toString();

}
