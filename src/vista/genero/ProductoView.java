package vista.genero;

import controlador.database.GeneralController;
import modelo.genero.Producto;
import validaciones.L;

public class ProductoView {

	/***********************************************************************************************
	 * ADMIN
	 ***********************************************************************************************/
	public static void operacionesProducto(GeneralController controlGeneral) {
		byte bOption;
		do {
			bOption = (byte) L.valida(""
					//
					+ "*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *"
					//
					+ "\n		ADMINISTRACION"
					//
					+ "\n*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *"
					//
					+ "\n"
					//
					+ "\n  --Aniadir Producto (Compra): 		(1)"
					//
					+ "\n  --Borrar Producto (Compra):     	(2)"
					//
					+ "\n  --Buscar Producto (Compra): 		(3)"
					//
					+ "\n  --Mostrar Almacen (Producto): 	(4)"
					//
					+ "\n  --Salir: 				(5)", 1, 5, 3);
			//

			if (bOption == 1) {
				aniadirProducto(controlGeneral);
			} else if (bOption == 2) {
				borrarProducto(controlGeneral);
			} else if (bOption == 3) {
				buscarProducto(controlGeneral);
			} else if (bOption == 4) {
				mostrarAlmacen(controlGeneral);
			}
		} while (bOption != 5);
	}

	private static void aniadirProducto(GeneralController controlGeneral) {

		String sResultado = "";
		float fPrecio = 0;
		short shStock = 0;

		String sNombre = L.leer("Nombre (Producto): ");

		boolean errorControl = true;
		do {
			try {
				fPrecio = (float) L.valida("Precio (Producto):", 0.1F, 3000F, 2);
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
				shStock = (short) L.valida("Stock (Producto):", 1, 100, 4);
				errorControl = false;
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (errorControl);

		String sTipo = "";
		byte bOption = 0;
		try {
			bOption = (byte) L.valida(
					"Este producto es de: " + "\nCarta bebida: (1)" + "\nCarta comida: (2)" + "\nNi uno ni otro: (3)",
					1, 3, 3);
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (bOption == 1) {
			sTipo = "Carta_bebida";
		} else if (bOption == 2) {
			sTipo = "Carta_comida";
		} else if (bOption == 3) {
			sTipo = "Derivados";
		}

		Producto oProducto = new Producto(sNombre, fPrecio, shStock, sTipo);

		if (controlGeneral.getProductoController().add(oProducto)) {
			sResultado = "Aniadido correctamente.";

		} else {
			sResultado = "No se pudo aniadir.";

		}
		System.out.println(sResultado);

	}

	private static void borrarProducto(GeneralController controlGeneral) {

		String sResultado = "No se pudo borrar";

		String sNombre = L.leer("Nombre (Producto): ");

		if (controlGeneral.getProductoController().remove(sNombre)) {
			sResultado = "borrado correctamente";
		}
		System.out.println(sResultado);
	}

	private static void buscarProducto(GeneralController controlGeneral) {

		String sResultado = "No esta registrado.";

		String sNombre = L.leer("Nombre (Producto): ");

		if (controlGeneral.getProductoController().search(sNombre)) {
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
						+ "Un Producto: 		(1)"
						//
						+ "\nLista de Productos: 	(2)", 1, 2, 3);

				errorControl = false;

			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (errorControl);

		if (bOption == 1) {

			String sNombre = L.leer("Nombre (Producto):");

			if (controlGeneral.getProductoController().search(sNombre)) {
				sResultado = controlGeneral.getProductoController().mostrarProducto(sNombre);

			} else {
				sResultado = "Producto no encontrado";
			}

		} else if (bOption == 2) {
			sResultado = controlGeneral.getProductoController().mostrarProductos();
		}
		System.out.println(sResultado);
	}

}
