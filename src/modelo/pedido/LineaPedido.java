package modelo.pedido;

import modelo.genero.Material;
import modelo.genero.Producto;
import modelo.limitdb.LimitsDB;

public class LineaPedido implements ILineaPedido, LimitsDB {
	
	private int iIdLineaPedido; // PK
	private Pedido oPedido; // FK
	private Material oMaterial; // FK
	private Producto oProducto; // FK
	private byte bCantidad;// NN
	private String sTipo;

	public LineaPedido(int iIdLineaPedido, Pedido oPedido, Material oMaterial, byte bCantidad,String sTipo) {
		setiIdLineaPedido(iIdLineaPedido);
		setoPedido(oPedido);
		setoMaterial(oMaterial);
		setbCantidad(bCantidad);
		setsTipo(sTipo);

	}

	public LineaPedido(int iIdLineaPedido, Pedido oPedido, Producto oProducto, byte bCantidad) {
		setiIdLineaPedido(iIdLineaPedido);
		setoPedido(oPedido);
		setoProducto(oProducto);
		setbCantidad(bCantidad);
		setsTipo(sTipo);

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
		if (oPedido != null) {
			this.oPedido = oPedido;
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
		if (oMaterial != null) {
			this.oMaterial = oMaterial;
		}
		return bValido;

	}

	@Override
	public Producto getoProducto() {
		return oProducto;
	}

	@Override
	public boolean setoProducto(Producto oProducto) {
		boolean bValido = false;
		if (oProducto != null) {
			this.oProducto = oProducto;
		}
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
		if (sTipo.equals(COMPRAS) || sTipo.equals(VENTAS)) {
			this.sTipo = sTipo;
			bValido = true;
		}
		return bValido;
	}
	@Override
	public boolean checkLineaPedido() {
		boolean bValido = false;
		if (this.iIdLineaPedido != -1 && this.oPedido != null && this.oMaterial != null && this.bCantidad != -1
				|| this.iIdLineaPedido != -1 && this.oPedido != null && this.oProducto != null
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
		if (checkLineaPedido() && other.checkLineaPedido() && this.iIdLineaPedido == other.iIdLineaPedido) {
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
		return "LineaPedido nº: " + this.iIdLineaPedido + "  --Cliene: "
				+ this.oPedido.getoUsuario().getsNombre() + "\n"
				//
				+ sResultado + "  --Fecha: " + this.oPedido.getdFecha();
	}

	

}
