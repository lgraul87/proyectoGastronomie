package vista.principal;

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
					+ "\n  --Para tomar aqui: (1)"
					//
					+ "\n  --Para llevar:     (2)"
					//
					+ "\n  --Salir: (3)"
					//
					+ "\n"
					//
					+ "\n  --Admin(4)"
					//
					+ "\n", 1, 4, 3);
			//

			if (bOption == 1) {
				PedidoView.tomarNotaLocal(controlGeneral);
			} else if (bOption == 2) {
				PedidoView.tomarNotaTakeAway();
			} else if (bOption == 3) {
				System.out.println("Buen dia, adios");
			} else if (bOption == 4) {
				seleccionOperaciones();
			}
		} while (bOption != 3);
	}

	private static void seleccionOperaciones() {
		byte bOption;
		do {
			bOption = (byte) L.valida(""
					//
					+ "*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *"
					//
					+ "\n					ADMINISTRACION"
					//
					+ "\n*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *"
					//
					+ "\n"
					//
					+ "\n  --Operaciones usuario: (1)"
					//
					+ "\n  --Operaciones instalacion:     (2)"
					//
					+ "\n  --Operaciones proveedor: (3)"
					//
					+ "\n  --Operaciones material: (4)"
					//
					+ "\n  --Operaciones Producto: (5)"
					//
					+ "\n  --Operaciones Pago: (6)"
					//
					+ "\n  --Operaciones Pedido: (7)"
					//
					+ "\n  --Salir: (8)"
					//
					+ "\n", 1, 8, 3);
			//

			if (bOption == 1) {
				UsuarioView.operacionesUsuario();
			} else if (bOption == 2) {
				InstalacionView.operacionesInstalacion();
			} else if (bOption == 3) {
				ProveedorView.operacionesProveedor();
			} else if (bOption == 4) {
				MaterialView.operacionesMaterial();
			}else if (bOption == 5) {
				ProductoView.operacionesProducto();
			}else if (bOption == 6) {
				PagoView.operacionesPago();
			}else if (bOption == 7) {
				PedidoView.operacionesPedido();
			}
		} while (bOption != 8);
	}

}
