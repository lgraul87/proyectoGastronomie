package vista.proveedor;

import validaciones.L;

public class MaterialProveedorView {

	/***********************************************************************************************
	 * ADMIN
	 ***********************************************************************************************/
	public static void operacionesMaterialProveedor() {
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
					+ "\n  --Asignar Material a Proveedor (Lista): (1)"
					//
					+ "\n  --Borrar Material a Proveedor (Lista):     (2)"
					//
					+ "\n  --Buscar Material de Proveedor (Lista): (3)"
					//
					+ "\n  --Mostrar Material de Proveedor (Lista): (4)"
					//
					+ "\n  --Salir: (5)", 1, 5, 3);
			//

			if (bOption == 1) {
				asignarMaterialProveedor();
			} else if (bOption == 2) {
				borrarMaterialProveedor();
			} else if (bOption == 3) {
				buscarMaterialProveedor();
			} else if (bOption == 4) {
				mostrarMaterialProveedor();
			}
		} while (bOption != 5);
	}

	public static void asignarMaterialProveedor() {
		// TODO Auto-generated method stub

	}

	public static void borrarMaterialProveedor() {
		// TODO Auto-generated method stub

	}

	public static void buscarMaterialProveedor() {
		// TODO Auto-generated method stub

	}

	public static void mostrarMaterialProveedor() {
		// TODO Auto-generated method stub

	}

}
