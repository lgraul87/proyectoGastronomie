package vista.usuario;

import validaciones.L;

public class UsuarioView {

	/***********************************************************************************************
	 * ADMIN
	 ***********************************************************************************************/
	public static void operacionesUsuario() {
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
					+ "\n  --Aniadir Usuario: (1)"
					//
					+ "\n  --Borrar Usuario:     (2)"
					//
					+ "\n  --Buscar Usuario: (3)"
					//
					+ "\n  --Mostrar Usuario: (4)"
					//
					+ "\n  --Salir: (5)", 1, 5, 3);
			//

			if (bOption == 1) {
				aniadirUsuario();
			} else if (bOption == 2) {
				borrarUsuario();
			} else if (bOption == 3) {
				buscarUsuario();
			} else if (bOption == 4) {
				mostrarUsuario();
			}
		} while (bOption != 5);
	}

	private static void aniadirUsuario() {
		// TODO Auto-generated method stub

	}

	private static void borrarUsuario() {
		// TODO Auto-generated method stub

	}

	private static void buscarUsuario() {
		// TODO Auto-generated method stub

	}

	private static void mostrarUsuario() {
		// TODO Auto-generated method stub

	}

}
