package modelo.pedido;

import java.util.Date;

import modelo.instalacion.Instalacion;
import modelo.limitdb.LimitsDB;
import modelo.pago.Pago;
import modelo.usuario.Usuario;

public class Pedido implements IPedido, LimitsDB {
	private int iIdPedido; // PK
	private Date dFecha; // NN
	private Usuario oUsuario; // FK
	private Pago oPago; // FK
	private Instalacion oInstalacion; // FK

	public Pedido(int iIdPedido, Date dFecha, Usuario oUsuario, Pago oPago, Instalacion oInstalacion) {

		setiIdPedido(iIdPedido);
		setdFecha(dFecha);
		setoUsuario(oUsuario);
		setoPago(oPago);
		setoInstalacion(oInstalacion);

	}
	public Pedido(int iIdPedido, Date dFecha, Usuario oUsuario, Instalacion oInstalacion) {

		setiIdPedido(iIdPedido);
		setdFecha(dFecha);
		setoUsuario(oUsuario);
		setoInstalacion(oInstalacion);

	}
	
	@Override
	public int getiIdPedido() {
		return this.iIdPedido;
	}
	
	private boolean setiIdPedido(int iIdPedido) {
		boolean bValido = false;
		if (iIdPedido >= MIN_INT_0 && iIdPedido <= MAX_INT_100) {
			this.iIdPedido = iIdPedido;
			bValido = true;
		} else {
			this.iIdPedido = -1;
		}
		return bValido;
	}
	@Override
	public Date getdFecha() {
		return this.dFecha;
	}
	@Override
	public boolean setdFecha(Date dFecha) {
		boolean bValido = false;
		if (dFecha != null) {
			this.dFecha = dFecha;
		}
		return bValido;
	}
	@Override
	public Usuario getoUsuario() {
		return this.oUsuario;
	}
	@Override
	public boolean setoUsuario(Usuario oUsuario) {
		boolean bValido = false;
		if (oUsuario != null) {
			this.oUsuario = oUsuario;
		}
		return bValido;
	}
	@Override
	public Pago getoPago() {
		return oPago;
	}
	@Override
	public boolean setoPago(Pago oPago) {
		boolean bValido = false;
		if (oPago != null) {
			this.oPago = oPago;
		}
		return bValido;
	}
	@Override
	public Instalacion getoInstalacion() {
		return this.oInstalacion;
	}
	@Override
	public boolean setoInstalacion(Instalacion oInstalacion) {
		boolean bValido = false;
		if (oInstalacion != null) {
			this.oInstalacion = oInstalacion;
		}
		return bValido;
	}
	@Override
	public boolean checkPedido() {
		boolean bValido = false;
		if (this.iIdPedido != -1 && this.dFecha != null && this.oInstalacion != null && this.oUsuario != null
				&& this.oPago != null) {
			bValido = true;

		}
		return bValido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + iIdPedido;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Pedido other = (Pedido) obj;
		if (checkPedido() && other.checkPedido() && this.iIdPedido == other.iIdPedido) {
			bIgual = true;
		}
		return bIgual;

	}

	@Override
	public String toString() {
		return "  --Pedido (Solicitud) nº: " + this.iIdPedido + "\n"
		//
				+ "  --Para: " + this.oUsuario.toString() + "\n"
				//
				+ "  --Establecimiento: " + this.oInstalacion.toString() + "\n"
				//
				+ "  --Pago: " + this.oPago.toString() + "\n"
				//
				+ "  --Fecha: " + this.dFecha + "\n";
		//
	}

}
