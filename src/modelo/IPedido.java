package modelo;

import java.util.Date;

public interface IPedido {

	public int getiIdPedido();

	public Date getdFecha();

	public boolean setdFecha(Date dFecha);

	public Usuario getoUsuario();

	public boolean setoUsuario(Usuario oUsuario);

	public Pago getoPago();

	public boolean setoPago(Pago oPago);

	public Instalacion getoInstalacion();

	public boolean setoInstalacion(Instalacion oInstalacion);

	public boolean checkPedido();

	public int hashCode();

	public boolean equals(Object obj);

	public String toString();

}
