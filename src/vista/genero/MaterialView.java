package vista.genero;

import validaciones.L;

public class MaterialView {

	/***********************************************************************************************
	 * ADMIN
	 ***********************************************************************************************/
	public static void operacionesMaterial() {
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
					+ "\n  --Aniadir Material (Compra): (1)"
					//
					+ "\n  --Borrar Material (Compra):     (2)"
					//
					+ "\n  --Buscar Material (Compra): (3)"
					//
					+ "\n  --Mostrar Almacen (Material): (4)"
					//
					+ "\n  --Salir: (5)", 1, 5, 3);
			//

			if (bOption == 1) {
				aniadirMaterial();
			} else if (bOption == 2) {
				borrarMaterial();
			} else if (bOption == 3) {
				buscarMaterial();
			} else if (bOption == 4) {
				mostrarAlmacen();
			}
		} while (bOption != 5);
	}

	private static void aniadirMaterial() {
		// TODO Auto-generated method stub

	}

	private static void borrarMaterial() {
		// TODO Auto-generated method stub

	}

	private static void buscarMaterial() {
		// TODO Auto-generated method stub

	}

	private static void mostrarAlmacen() {
		// TODO Auto-generated method stub

	}

}
