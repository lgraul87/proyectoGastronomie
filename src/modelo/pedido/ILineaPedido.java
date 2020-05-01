package modelo.pedido;

import modelo.genero.Material;
import modelo.genero.Producto;

public interface ILineaPedido {

	public final String COMPRAS = "Compra";
	public final String VENTAS = "Venta";

	public int getiIdLineaPedido();

	public Pedido getoPedido();

	public boolean setoPedido(Pedido oPedido);

	public Material getoMaterial();

	public boolean setoMaterial(Material oMaterial);

	public Producto getoProducto();

	public boolean setoProducto(Producto oProducto);

	public byte getbCantidad();

	public boolean setbCantidad(byte bCantidad);

	public String getsTipo();

	public boolean setsTipo(String sTipo);

	public boolean checkLineaPedido();

	public int hashCode();

	public boolean equals(Object obj);

	public String toString();

}
