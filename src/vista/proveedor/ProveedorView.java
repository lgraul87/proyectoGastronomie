package vista.proveedor;

import controlador.database.GeneralController;
import validaciones.L;

public class ProveedorView {
	/***********************************************************************************************
	 * ADMIN
	 * @param controlGeneral 
	 ***********************************************************************************************/
	public static void operacionesProveedor(GeneralController controlGeneral) {
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
					+ "\n  --Aniadir Proveedor (Compra): (1)"
					//
					+ "\n  --Borrar Proveedor (Compra):     (2)"
					//
					+ "\n  --Buscar Proveedor (Compra): (3)"
					//
					+ "\n  --Mostrar Proveedor (Compra): (4)"
					//
					+ "\n  --Salir: (5)", 1, 5, 3);
			//

			if (bOption == 1) {
				aniadirProveedor();
			} else if (bOption == 2) {
				borrarProveedor();
			} else if (bOption == 3) {
				buscarProveedor();
			} else if (bOption == 4) {
				mostrarProveedor();
			}
		} while (bOption != 5);
	}

	private static void aniadirProveedor() {
		// TODO Auto-generated method stub

	}

	private static void borrarProveedor() {
		// TODO Auto-generated method stub

	}

	private static void buscarProveedor() {
		// TODO Auto-generated method stub

	}

	private static void mostrarProveedor() {
		// TODO Auto-generated method stub

	}

}
