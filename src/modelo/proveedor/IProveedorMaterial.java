package modelo.proveedor;

import modelo.Material;

public interface IProveedorMaterial {

	public Material getoMaterial();

	public boolean setoMaterial(Material oMaterial);

	public Proveedor getoProveedor();

	public boolean setoProveedor(Proveedor oProveedor);

	public boolean checkProveedorMaterial();

	public int hashCode();

	public boolean equals(Object obj);

	public String toString();

}
