package controlador;

import modelo.proveedor.Proveedor;

public interface IMaterialProveedorController {

	public boolean determinarMaterial(String sNombreMaterial);

	public Proveedor obtenerProveedorMaterial(String sNombreMaterial);
}
