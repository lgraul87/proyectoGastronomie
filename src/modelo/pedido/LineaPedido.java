package modelo.pedido;

import modelo.genero.Material;
import modelo.genero.Producto;
import modelo.limitdb.LimitsDB;
import modelo.proveedor.Proveedor;

public class LineaPedido implements ILineaPedido, LimitsDB {

	private int iIdLineaPedido; // PK
	private Pedido oPedido; // FK
	private Material oMaterial; // FK Puede estar o no
	private Producto oProducto; // FK Puede estar o no
	private byte bCantidad;// NN
	private String sTipo;// NN
	private Proveedor oProveedor;// NN

	public LineaPedido(int iIdLineaPedido, Pedido oPedido, Material oMaterial, byte bCantidad,
			String sTipo, Proveedor oProveedor) {
		setiIdLineaPedido(iIdLineaPedido);
		setoPedido(oPedido);
		setoMaterial(oMaterial);
		setbCantidad(bCantidad);
		setsTipo(sTipo);
		setoProveedor(oProveedor);

	}
	public LineaPedido(int iIdLineaPedido, Pedido oPedido, Producto oProducto, byte bCantidad,
			String sTipo, Proveedor oProveedor) {
		setiIdLineaPedido(iIdLineaPedido);
		setoPedido(oPedido);
		setoProducto(oProducto);
		setbCantidad(bCantidad);
		setsTipo(sTipo);
		setoProveedor(oProveedor);

	}

	public LineaPedido(int iIdLineaPedido) {
		setiIdLineaPedido(iIdLineaPedido);

	}

	@Override
	public int getiIdLineaPedido() {
		return this.iIdLineaPedido;
	}

	private boolean setiIdLineaPedido(int iIdLineaPedido) { // PK
		boolean bValido = false;
		if (iIdLineaPedido >= MIN_INT_0 && iIdLineaPedido <= MAX_INT_100) {
			this.iIdLineaPedido = iIdLineaPedido;
			bValido = true;
		} else {
			this.iIdLineaPedido = -1;
		}
		return bValido;
	}

	@Override
	public Pedido getoPedido() {
		return this.oPedido;
	}

	@Override
	public boolean setoPedido(Pedido oPedido) {
		boolean bValido = false;
		if (oPedido != null && oPedido.checkPedido()) {
			this.oPedido = oPedido;
			bValido = true;
		}
		return bValido;

	}

	@Override
	public Material getoMaterial() {
		return this.oMaterial;
	}

	@Override
	public boolean setoMaterial(Material oMaterial) {
		boolean bValido = false;
		if (oMaterial != null && oMaterial.checkMaterial()) {
			this.oMaterial = oMaterial;
			bValido = true;

		} else {
			this.oMaterial = null;
		}
		bValido = true;
		return bValido;

	}

	@Override
	public Producto getoProducto() {
		return oProducto;
	}

	@Override
	public boolean setoProducto(Producto oProducto) {
		boolean bValido = false;
		if (oProducto != null && oProducto.checkProducto()) {
			this.oProducto = oProducto;
			bValido = true;

		} else {
			this.oProducto = null;
		}
		bValido = true;
		return bValido;

	}

	@Override
	public byte getbCantidad() {
		return this.bCantidad;
	}

	@Override
	public boolean setbCantidad(byte bCantidad) {
		boolean bValido = false;
		if (bCantidad > MIN_INT_0 && bCantidad <= MAX_INT_100) {
			this.bCantidad = bCantidad;
			bValido = true;
		} else {
			this.bCantidad = -1;
		}
		return bValido;
	}

	@Override
	public String getsTipo() {
		return this.sTipo;
	}

	@Override
	public boolean setsTipo(String sTipo) {
		boolean bValido = false;
		if (sTipo != null && sTipo.equals(COMPRAS) || sTipo != null && sTipo.equals(VENTAS)) {
			this.sTipo = sTipo;
			bValido = true;
		}
		return bValido;

	}
	@Override
	public Proveedor getoProveedor() {
		return this.oProveedor;
	}

	public boolean setoProveedor(Proveedor oProveedor) {
		boolean bValido = false;
		if (oProveedor != null && oProveedor.checkProveedor()) {
			this.oProveedor = oProveedor;
			bValido = true;
		}
		return bValido;
	}

	@Override
	public boolean checkLineaPedido() {
		boolean bValido = false;
		if (this.iIdLineaPedido != -1 && this.oPedido != null && this.oMaterial != null && this.oProducto == null
				&& this.bCantidad != -1
				|| this.iIdLineaPedido != -1 && this.oPedido != null && this.oMaterial == null && this.oProducto != null
						&& this.bCantidad != -1) {
			bValido = true;
		}
		return bValido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + iIdLineaPedido;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		LineaPedido other = (LineaPedido) obj;
		if (this != null && other != null && checkLineaPedido() && other.checkLineaPedido()
				&& this.iIdLineaPedido == other.iIdLineaPedido) {
			bIgual = true;
		}
		return bIgual;

	}

	@Override
	public String toString() {
		String sResultado = "";
		//
		if (this.oMaterial != null) {
			sResultado = this.oMaterial.getsNombreMaterial() + "  --Precio: " + this.oMaterial.getfPrecio()
					+ "  --Total: " + this.oMaterial.getfPrecio() * this.bCantidad + "\n";
		}
		//
		if (this.oProducto != null) {
			sResultado = this.oProducto.getsNombreProducto() + "  --Precio: " + this.oProducto.getfPrecio()
					+ "  --Total: " + this.oProducto.getfPrecio() * this.bCantidad + "\n";

		}
		//
		return "LineaPedido n�: " + this.iIdLineaPedido + "  --Cliente: " + this.oPedido.getoUsuario().getsNombre()
				+ "\n"
				//
				+ sResultado + "  --Fecha: " + this.oPedido.getdFecha();
	}

}
