package vista;

import java.util.Date;

import controlador.database.GeneralController;
import modelo.genero.Producto;
import modelo.instalacion.Instalacion;
import modelo.pago.MetodoPago;
import modelo.pago.Pago;
import modelo.pedido.LineaPedido;
import modelo.pedido.Pedido;
import modelo.usuario.TipoUsuario;
import modelo.usuario.Usuario;
import validaciones.L;

public class PedidoView {

	/****************************************************************************************************
	 * PARA CLIENTES
	 ****************************************************************************************************/
	public static void tomarNotaLocal(GeneralController controlGeneral) {

		System.out.println(controlGeneral.getProductoController().mostrarCartaBebida());

		String sBebida = L.leer("Introduce la bebida");

		byte bCantidad = (byte) L.valida("Cantidad: (bebida)", 1, 30, 3);

		String sNombrePersona = L.leer("Me dices tu nombre:");

		String sApellidos = L.leer("Me dices tu apellido:");

		String sDni = L.leer("Me dices tu DNI:");

		String sMetodoPago = asignarMetodoPago();

		Producto oProducto = controlGeneral.getProductoController().obtenerProducto(sBebida);

		TipoUsuario oTipoUsuario = new TipoUsuario("Cliente");

		Usuario oUsuario = new Usuario(sNombrePersona, sDni, sApellidos, oTipoUsuario);

		MetodoPago oMetodoPago = new MetodoPago(sMetodoPago);

		Pago oPago = new Pago(controlGeneral.getPagoController().asignarId(), new Date(), oMetodoPago);
		
		Instalacion oInstalacion = new Instalacion("Gastronomie", "C/Comercio, 72", 1000);

		Pedido oPedido = new Pedido(controlGeneral.getPedidoController().asignarId(), new Date(), oUsuario, oPago, oInstalacion);

		LineaPedido oLineaPedido = new LineaPedido(controlGeneral.getLineaPedidoController().asignarId(), oPedido, oProducto, bCantidad);
		
		controlGeneral.getLineaPedidoController().upDateLineaPedido(oLineaPedido);
	}

	public static String asignarMetodoPago() {
		String sMetodoPago = "";

		byte bOption = (byte) L.valida(""
				//
				+ "Elige metodo de pago"
				//
				+ "\n  --Efectivo: (1)"
				//
				+ "\n  --Tarjeta: (2)", 1, 2, 3);
		if (bOption == 1) {
			sMetodoPago = "Efectivo";

		} else if (bOption == 2) {
			sMetodoPago = "Tarjeta";

		}
		return sMetodoPago;
	}

	public static void tomarNotaTakeAway() {
		// TODO Auto-generated method stub

	}

	/****************************************************************************************************
	 * ADMIN
	 ****************************************************************************************************/
	public static void operacionesPedido() {
		// TODO Auto-generated method stub

	}

}
