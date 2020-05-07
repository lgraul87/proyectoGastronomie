package vista.principal;

import controlador.database.ConexionDB;
import controlador.database.GeneralController;
import validaciones.L;
import vista.genero.MaterialView;
import vista.genero.ProductoView;
import vista.instalacion.InstalacionView;
import vista.pago.PagoView;
import vista.pedido.PedidoView;
import vista.proveedor.ProveedorView;
import vista.usuario.UsuarioView;

public class Gastronomie {
	
	public static GeneralController controlGeneral = new GeneralController("raulbase");

	public static void main(String[] args) {

		start();
	}

	public static void start() {
		byte bOption;
		do {
			bOption = (byte) L.valida("\n\n"
					//
					+ "*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *"
					//
					+ "\n·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·"
					//
					+ "\n		BIENVENIDO AL BAR GASTRONOMIE"
					//
					+ "\n·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·"
					//
					+ "\n*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *"
					//
					+ "\n"
					//
					+ "\n  --Para tomar aqui:    	(1)"
					//
					+ "\n  --Para llevar:     		(2)"
					//
					+ "\n  --Salir:           		(3)"
					//
					+ "\n"
					//
					+ "\n  --Admin:			(4)"
					//
					+ "\n", 1, 4, 3);
			//

			if (bOption == 1) {
				PedidoView.tomarNotaLocal(controlGeneral);
			} else if (bOption == 2) {
				PedidoView.tomarNotaTakeAway(controlGeneral);
			} else if (bOption == 3) {
				System.out.println("Buen dia, adios");
				ConexionDB.disconnectDatabase();
			} else if (bOption == 4) {
				seleccionOperaciones();
			}
		} while (bOption != 3);
	}

	public static void seleccionOperaciones() {
		byte bOption;
		do {
			bOption = (byte) L.valida(""
					//
					+ "*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *"
					//
					+ "\n		ADMINISTRACION"
					//
					+ "\n*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *"
					//
					+ "\n"
					//
					+ "\n  --Operaciones usuario: 			(1)"
					//
					+ "\n  --Operaciones instalacion:     		(2)"
					//
					+ "\n  --Operaciones proveedor: 			(3)"
					//
					+ "\n  --Operaciones material: 			(4)"
					//
					+ "\n  --Operaciones Producto: 			(5)"
					//
					+ "\n  --Operaciones Pago: 				(6)"
					//
					+ "\n  --Operaciones Pedido: 			(7)"
					//
					+ "\n  --Salir: 					(8)"
					//
					+ "\n", 1, 8, 3);
			//

			if (bOption == 1) {
				UsuarioView.operacionesUsuario(controlGeneral);
			} else if (bOption == 2) {
				InstalacionView.operacionesInstalacion(controlGeneral);
			} else if (bOption == 3) {
				ProveedorView.operacionesProveedor(controlGeneral);
			} else if (bOption == 4) {
				MaterialView.operacionesMaterial(controlGeneral);
			}else if (bOption == 5) {
				ProductoView.operacionesProducto(controlGeneral);
			}else if (bOption == 6) {
				PagoView.operacionesPago(controlGeneral);
			}else if (bOption == 7) {
				PedidoView.operacionesPedido(controlGeneral);
			}
		} while (bOption != 8);
	}

}
