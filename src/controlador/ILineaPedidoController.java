package controlador;

import modelo.pedido.LineaPedido;

public interface ILineaPedidoController {

	public boolean add(LineaPedido oLineaPedido);

	public boolean addSinPago(LineaPedido oLineaPedido);

	public String mostrarPedidosPorId();

	public boolean remove(int iNumero);

	public boolean search(int iNumero);

	public java.sql.Date convert(java.util.Date uDate);

	public boolean searchPedidoProveedor(String sNombreProveedor);

	public String mostrarPedidoProveedor(String sNombreProveedor);

	public String mostrarPedidos();

}
