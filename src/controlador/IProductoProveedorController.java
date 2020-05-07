package controlador;

import modelo.proveedor.Proveedor;

public interface IProductoProveedorController {

	public boolean determinarProducto(String sNombreProducto);

	public Proveedor obtenerProveedorProducto(String sNombreProducto);

}
