package modelo.proveedor;

import modelo.Producto;

public interface IProveedorProducto {

	public Producto getoProducto();

	public boolean setoProducto(Producto oProducto);

	public Proveedor getoProveedor();

	public boolean setoProveedor(Proveedor oProveedor);

	public boolean checkProveedorProducto();

	public int hashCode();

	public boolean equals(Object obj);

	public String toString();

}
