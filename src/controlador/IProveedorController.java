package controlador;

import modelo.proveedor.Proveedor;

public interface IProveedorController {

	public boolean add(Proveedor oProveedor);

	public void createTipoProveedor(String sTipo);

	public boolean searchTipoProveedor(String sTipo);

	public boolean remove(Proveedor oProveedor);

	public boolean deleteProveedor(Proveedor oProveedor);

	public boolean searchProveedor(Proveedor oProveedor);

	public String mostrarProveedor(Proveedor oProveedor);

	public String mostrarProveedores();

}
