package controlador;

import modelo.pago.Pago;

public interface IPagoController {

	public boolean add(Pago oPago);

	public java.sql.Date convert(java.util.Date uDate);

	public String mostrarPago(int iId);

	public String mostrarPagos();

	public boolean remove(int iNumero);

	public boolean search(int iNumero);

}
