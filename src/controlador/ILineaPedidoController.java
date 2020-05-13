package controlador;

import modelo.pedido.LineaPedido;
import modelo.proveedor.Proveedor;

public interface ILineaPedidoController {

	public boolean add(LineaPedido oLineaPedido);

	public boolean addSinPago(LineaPedido oLineaPedido);

	public String mostrarPedidosPorId();

	public boolean remove(int iNumero);

	public boolean search(int iNumero);

	public java.sql.Date convert(java.util.Date uDate);

	public boolean searchPedidoProveedor(Proveedor oProveedor);

	public String mostrarPedidoProveedor(Proveedor oProveedor);

	public String mostrarPedidos();
	
	public int autoId();

}
