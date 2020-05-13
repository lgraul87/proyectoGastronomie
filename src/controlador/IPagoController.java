package controlador;

import modelo.pago.Pago;

public interface IPagoController {

	public boolean add(Pago oPago);

	public java.sql.Date convert(java.util.Date uDate);

	public String mostrarPago(Pago oPago);

	public String mostrarPagos();

	public boolean remove(Pago oPago);

	public boolean search(Pago oPago);

}
