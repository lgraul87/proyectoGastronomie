package controlador;

import modelo.genero.Producto;
import modelo.proveedor.Proveedor;

public interface IProductoProveedorController {

	public boolean determinarProducto(String sNombreProducto);

	public Proveedor obtenerProveedorProducto(String sNombreProducto);

	public boolean add(Producto oProducto, Proveedor oProveedor);

	public boolean remove(Producto oProducto, Proveedor oProveedor);

	public boolean search(Producto oProducto, Proveedor oProveedor);

	public String mostrarProductoProveedores();

	public String mostrarProductoProveedor(Proveedor oProveedor);

}
