package vista;

import validaciones.L;

public class ProductoProveedorView {
	/***********************************************************************************************
	 * ADMIN
	 ***********************************************************************************************/
	public static void operacionesProductoProveedor() {
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
					+ "\n  --Asignar Producto a Proveedor (Lista): (1)"
					//
					+ "\n  --Borrar Producto a Proveedor (Lista):     (2)"
					//
					+ "\n  --Buscar Producto de Proveedor (Lista): (3)"
					//
					+ "\n  --Mostrar Producto de Proveedor (Lista): (4)"
					//
					+ "\n  --Salir: (5)", 1, 5, 3);
			//

			if (bOption == 1) {
				asignarProductoProveedor();
			} else if (bOption == 2) {
				borrarProductoProveedor();
			} else if (bOption == 3) {
				buscarProductoProveedor();
			} else if (bOption == 4) {
				mostrarProductoProveedor();
			}
		} while (bOption != 5);
	}

	private static void asignarProductoProveedor() {
		// TODO Auto-generated method stub

	}

	private static void borrarProductoProveedor() {
		// TODO Auto-generated method stub

	}

	private static void buscarProductoProveedor() {
		// TODO Auto-generated method stub

	}

	private static void mostrarProductoProveedor() {
		// TODO Auto-generated method stub

	}

}
