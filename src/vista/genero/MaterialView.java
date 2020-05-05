package vista.genero;

import controlador.database.GeneralController;
import modelo.genero.Material;
import validaciones.L;

public class MaterialView {

	/***********************************************************************************************
	 * ADMIN
	 * 
	 * @param controlGeneral
	 ***********************************************************************************************/
	public static void operacionesMaterial(GeneralController controlGeneral) {
		byte bOption;
		do {
			bOption = (byte) L.valida(""
					//
					+ "*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *"
					//
					+ "\n			ADMINISTRACION"
					//
					+ "\n*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *"
					//
					+ "\n"
					//
					+ "\n  --Aniadir Material (Compra): 		(1)"
					//
					+ "\n  --Borrar Material (Compra):     	(2)"
					//
					+ "\n  --Buscar Material (Compra): 		(3)"
					//
					+ "\n  --Mostrar Almacen (Material): 	(4)"
					//
					+ "\n  --Salir: 				(5)", 1, 5, 3);

			//

			if (bOption == 1) {
				aniadirMaterial(controlGeneral);
			} else if (bOption == 2) {
				borrarMaterial(controlGeneral);
			} else if (bOption == 3) {
				buscarMaterial(controlGeneral);
			} else if (bOption == 4) {
				mostrarAlmacen(controlGeneral);
			}
		} while (bOption != 5);
	}

	private static void aniadirMaterial(GeneralController controlGeneral) {

		String sResultado = "";
		float fPrecio = 0;
		short shStock = 0;

		String sNombre = L.leer("Nombre (Material): ");

		boolean errorControl = true;
		do {
			try {
				fPrecio = (float) L.valida("Precio (Material):", 0.1F, 3000F, 2);
				errorControl = false;
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (errorControl);

		errorControl = true;
		do {
			try {
				shStock = (short) L.valida("Stock (Material):", 1, 100, 4);
				errorControl = false;
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (errorControl);

		String sTipo = "COMPRA";

		Material oMaterial = new Material(sNombre, fPrecio, shStock, sTipo);

		if (controlGeneral.getMaterialController().add(oMaterial)) {
			sResultado = "Aniadido correctamente.";

		} else {
			sResultado = "No se pudo aniadir.";

		}
		System.out.println(sResultado);

	}

	private static void borrarMaterial(GeneralController controlGeneral) {

		String sResultado = "No se pudo borrar";

		String sNombre = L.leer("Nombre (Material): ");

		if (controlGeneral.getMaterialController().remove(sNombre)) {
			sResultado = "borrado correctamente";
		}
		System.out.println(sResultado);
	}

	private static void buscarMaterial(GeneralController controlGeneral) {

		String sResultado = "No esta registrado.";

		String sNombre = L.leer("Nombre (Material): ");

		if (controlGeneral.getMaterialController().search(sNombre)) {
			sResultado = "Encontrado, esta registrado.";
		}
		System.out.println(sResultado);
	}

	private static void mostrarAlmacen(GeneralController controlGeneral) {
		String sResultado = null;

		byte bOption = 0;

		boolean errorControl = true;

		do {
			try {
				bOption = (byte) L.valida(""
						//
						+ "Un Material: 		(1)"
						//
						+ "\nLista de Materiales: 	(2)", 1, 2, 3);

				errorControl = false;

			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (errorControl);

		if (bOption == 1) {

			String sNombre = L.leer("Nombre (Material):");

			if (controlGeneral.getMaterialController().search(sNombre)) {
				sResultado = controlGeneral.getMaterialController().mostrarMaterial(sNombre);

			} else {
				sResultado = "Proveedor no encontrado";
			}

		} else if (bOption == 2) {
			sResultado = controlGeneral.getMaterialController().mostrarMateriales();
		}
		System.out.println(sResultado);
	}

}
