package controlador;

import modelo.genero.Material;
import modelo.proveedor.Proveedor;

public interface IMaterialProveedorController {

	public boolean determinarMaterial(Material oMaterial);

	public Proveedor obtenerProveedorMaterial(Material oMaterial);

	public boolean add(Material oMaterial, Proveedor oProveedor);

	public boolean remove(Material oMaterial, Proveedor oProveedor);

	public boolean search(Material oMaterial, Proveedor oProveedor);

	public String mostrarMaterialProveedores();

	public String mostrarMaterialProveedor(Proveedor oProveedor);

}
