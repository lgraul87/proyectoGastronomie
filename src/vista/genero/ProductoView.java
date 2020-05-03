package vista.genero;

import validaciones.L;

public class ProductoView {

	/***********************************************************************************************
	 * ADMIN
	 ***********************************************************************************************/
	public static void operacionesProducto() {
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
					+ "\n  --Aniadir Producto (Compra): (1)"
					//
					+ "\n  --Borrar Producto (Compra):     (2)"
					//
					+ "\n  --Buscar Producto (Compra): (3)"
					//
					+ "\n  --Mostrar Almacen (Producto): (4)"
					//
					+ "\n  --Salir: (5)", 1, 5, 3);
			//

			if (bOption == 1) {
				aniadirProducto();
			} else if (bOption == 2) {
				borrarProducto();
			} else if (bOption == 3) {
				buscarProducto();
			} else if (bOption == 4) {
				mostrarAlmacen();
			}
		} while (bOption != 5);
	}

	private static void aniadirProducto() {
		// TODO Auto-generated method stub

	}

	private static void borrarProducto() {
		// TODO Auto-generated method stub

	}

	private static void buscarProducto() {
		// TODO Auto-generated method stub

	}

	private static void mostrarAlmacen() {
		// TODO Auto-generated method stub

	}

}
