package vista.instalacion;

import validaciones.L;

public class InstalacionView {

	
	/***********************************************************************************************
	 * ADMIN
	 ***********************************************************************************************/
	public static void operacionesInstalacion() {
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
					+ "\n  --Aniadir Instalacion (Alquiler): (1)"
					//
					+ "\n  --Borrar Instalacion (Alquiler):     (2)"
					//
					+ "\n  --Buscar Instalacion (Alquiler): (3)"
					//
					+ "\n  --Mostrar Instalacion (Alquiler): (4)"
					//
					+ "\n  --Salir: (5)", 1, 5, 3);
			//

			if (bOption == 1) {
				aniadirInstalacion();
			} else if (bOption == 2) {
				borrarInstalacion();
			} else if (bOption == 3) {
				buscarInstalacion();
			} else if (bOption == 4) {
				mostrarInstalacion();
			}
		} while (bOption != 5);
	}

	private static void aniadirInstalacion() {
		// TODO Auto-generated method stub

	}

	private static void borrarInstalacion() {
		// TODO Auto-generated method stub

	}

	private static void buscarInstalacion() {
		// TODO Auto-generated method stub

	}

	private static void mostrarInstalacion() {
		// TODO Auto-generated method stub

	}

}
