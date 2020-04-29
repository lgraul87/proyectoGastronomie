package modelo;

public interface ILineaPedido {

	public int getiIdLineaPedido();

	public Pedido getoPedido();

	public boolean setoPedido(Pedido oPedido);

	public Material getoMaterial();

	public boolean setoMaterial(Material oMaterial);

	public Producto getoProducto();

	public boolean setoProducto(Producto oProducto);

	public boolean checkLineaPedido();

	public int hashCode();

	public boolean equals(Object obj);

	public String toString();

}
