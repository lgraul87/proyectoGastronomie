package modelo.proveedor;

import modelo.genero.Material;

public class ProveedorMaterial implements IProveedorMaterial{
	private Material oMaterial; // FK
	private Proveedor oProveedor; // FK

	public ProveedorMaterial(Material oMaterial, Proveedor oProveedor) {

		setoMaterial(oMaterial);
		setoProveedor(oProveedor);

	}
	@Override
	public Material getoMaterial() {
		return this.oMaterial;
	}
	@Override
	public boolean setoMaterial(Material oMaterial) {
		boolean bValido = false;
		if (oMaterial.checkMaterial()) {
			this.oMaterial = oMaterial;
			bValido = true;
		}
		return bValido;
	}
	@Override
	public Proveedor getoProveedor() {
		return this.oProveedor;
	}
	@Override
	public boolean setoProveedor(Proveedor oProveedor) {
		boolean bValido = false;
		if (oProveedor.checkProveedor()) {
			this.oProveedor = oProveedor;
			bValido = true;
		}
		return bValido;
	}
	@Override
	public boolean checkProveedorMaterial() {
		boolean bValido = false;
		if (this.oMaterial != null && this.oProveedor != null) {
			bValido = true;

		}
		return bValido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oMaterial == null) ? 0 : oMaterial.hashCode());
		result = prime * result + ((oProveedor == null) ? 0 : oProveedor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		ProveedorMaterial other = (ProveedorMaterial) obj;
		if (this.checkProveedorMaterial() && other.checkProveedorMaterial() && this.oMaterial.equals(other.oMaterial)
				&& this.oProveedor.equals(other.oProveedor)) {
			bIgual = true;
		}
		return bIgual;
	}

	@Override
	public String toString() {
		return "  --Proveedor Material: " + this.oProveedor.getsNombreProveedor() + "  --Material: " + this.oMaterial;
	}

}
