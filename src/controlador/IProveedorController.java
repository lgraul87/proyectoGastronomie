package controlador;

import modelo.proveedor.Proveedor;

public interface IProveedorController {

	public boolean add(Proveedor oProveedor);

	public void createTipoProveedor(String sTipo);

	public boolean searchTipoProveedor(String sTipo);

	public boolean remove(String sNombre);

	public boolean deleteProveedor(String sNombre);

	public boolean searchProveedor(String sNombre);

	public String mostrarProveedor(String sNombre);

	public String mostrarProveedores();

}
