package vista.pago;

import validaciones.L;

public class PagoView {

	/***********************************************************************************************
	 * ADMIN
	 ***********************************************************************************************/
	public static void operacionesPago() {
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
					+ "\n  --Aniadir Pago (Compra): (1)"
					//
					+ "\n  --Borrar Pago (Compra):     (2)"
					//
					+ "\n  --Buscar Pago (Compra): (3)"
					//
					+ "\n  --Mostrar Pago (Compra): (4)"
					//
					+ "\n  --Salir: (5)", 1, 5, 3);
			//

			if (bOption == 1) {
				aniadirPago();
			} else if (bOption == 2) {
				borrarPago();
			} else if (bOption == 3) {
				buscarPago();
			} else if (bOption == 4) {
				mostrarPago();
			}
		} while (bOption != 5);
	}

	private static void aniadirPago() {
		// TODO Auto-generated method stub

	}

	private static void borrarPago() {
		// TODO Auto-generated method stub

	}

	private static void buscarPago() {
		// TODO Auto-generated method stub

	}

	private static void mostrarPago() {
		// TODO Auto-generated method stub

	}

}
