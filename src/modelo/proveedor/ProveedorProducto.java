package modelo.proveedor;

import modelo.genero.Producto;

public class ProveedorProducto implements IProveedorProducto{
	private Producto oProducto; // FK
	private Proveedor oProveedor; // FK

	public ProveedorProducto(Producto oProducto, Proveedor oProveedor) {

		setoProducto(oProducto);
		setoProveedor(oProveedor);

	}
	@Override
	public Producto getoProducto() {
		return this.oProducto;
	}
	@Override
	public boolean setoProducto(Producto oProducto) {
		boolean bValido = false;
		if (oProducto!=null && oProducto.checkProducto()) {
			this.oProducto = oProducto;
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
		if (oProveedor != null && oProveedor.checkProveedor()) {
			this.oProveedor = oProveedor;
			bValido = true;
		}
		return bValido;
	}
	@Override
	public boolean checkProveedorProducto() {
		boolean bValido = false;
		if (this.oProducto != null && this.oProveedor != null && this.oProveedor.getoTipoProveedor().checkTipoProveedor()) {
			bValido = true;

		}
		return bValido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oProducto == null) ? 0 : oProducto.hashCode());
		result = prime * result + ((oProveedor == null) ? 0 : oProveedor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		ProveedorProducto other = (ProveedorProducto) obj;
		if (this != null && other != null && this.checkProveedorProducto() && other.checkProveedorProducto() && this.oProducto.equals(other.oProducto)
				&& this.oProveedor.equals(other.oProveedor) && this.oProveedor.getoTipoProveedor().equals(other.oProveedor.getoTipoProveedor())) {
			bIgual = true;
		}
		return bIgual;
	}

	@Override
	public String toString() {
		return "  --Proveedor Producto: " + this.oProveedor.getsNombreProveedor() + "  --Producto: " + this.oProducto;
	}

}
