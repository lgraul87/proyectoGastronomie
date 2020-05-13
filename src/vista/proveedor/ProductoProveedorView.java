package vista.proveedor;

import controlador.database.GeneralController;
import modelo.genero.Producto;
import modelo.proveedor.Proveedor;
import validaciones.L;

public class ProductoProveedorView {
	/***********************************************************************************************
	 * ADMIN
	 ***********************************************************************************************/
	public static void operacionesProductoProveedor(GeneralController controlGeneral) {
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
					+ "\n  --Asignar Producto a Proveedor (Lista): 	(1)"
					//
					+ "\n  --Borrar Producto a Proveedor (Lista):     	(2)"
					//
					+ "\n  --Buscar Producto de Proveedor (Lista): 	(3)"
					//
					+ "\n  --Mostrar Producto de Proveedor (Lista): 	(4)"
					//
					+ "\n  --Salir: (5)", 1, 5, 3);
			//

			if (bOption == 1) {
				asignarProductoProveedor(controlGeneral);
			} else if (bOption == 2) {
				borrarProductoProveedor(controlGeneral);
			} else if (bOption == 3) {
				buscarProductoProveedor(controlGeneral);
			} else if (bOption == 4) {
				mostrarProductoProveedor(controlGeneral);
			}
		} while (bOption != 5);
	}

	public static void asignarProductoProveedor(GeneralController controlGeneral) {

		String sResultado = "No se pudo aniadir";
		String sNombreProducto = L.leer("Nombre de producto a integrar en la lista: ");
		String sNombreProveedor = L.leer("Nombre de proveedor a integrar en la lista: ");
		Producto oProducto = new Producto(sNombreProducto);
		Proveedor oProveedor = new Proveedor(sNombreProveedor);

		if (controlGeneral.getProductoProveedorController().add(oProducto, oProveedor)) {
			sResultado = "Aniadido";
		}
		System.out.println(sResultado);
	}

	public static void borrarProductoProveedor(GeneralController controlGeneral) {

		String sResultado = "No se pudo borrar";

		String sNombreProducto = L.leer("Nombre de producto a borrar en la lista: ");
		String sNombreProveedor = L.leer("Nombre de proveedor a borrar en la lista: ");
		Producto oProducto = new Producto(sNombreProducto);
		Proveedor oProveedor = new Proveedor(sNombreProveedor);

		if (controlGeneral.getProductoProveedorController().remove(oProducto, oProveedor)) {
			sResultado = "Borrado";
		}
		System.out.println(sResultado);
	}

	public static void buscarProductoProveedor(GeneralController controlGeneral) {

		String sResultado = "No se pudo encontrar";

		String sNombreProducto = L.leer("Nombre de producto a buscar en la lista: ");
		String sNombreProveedor = L.leer("Nombre de proveedor a buscar en la lista: ");
		Producto oProducto = new Producto(sNombreProducto);
		Proveedor oProveedor = new Proveedor(sNombreProveedor);

		if (controlGeneral.getProductoProveedorController().search(oProducto, oProveedor)) {
			sResultado = "Encontrado";
		}
		System.out.println(sResultado);
	}

	public static void mostrarProductoProveedor(GeneralController controlGeneral) {

		String sResultado = "No hay Listas";

		if (!controlGeneral.getProductoProveedorController().mostrarProductoProveedores().equals("No hay Listas")) {

			byte bOption = 0;

			boolean errorControl = true;

			do {
				try {
					bOption = (byte) L.valida(""
							//
							+ "Un Proveedor y su lista de productos: 		(1)"
							//
							+ "\nLista de Proveedores y sus productos: 	(2)", 1, 2, 3);

					errorControl = false;

				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			if (bOption == 1) {

				String sNombreProveedor = L.leer("Nombre de proveedor a buscar en la lista: ");
				Proveedor oProveedor = new Proveedor(sNombreProveedor);
				sResultado = controlGeneral.getProductoProveedorController().mostrarProductoProveedor(oProveedor);

			} else if (bOption == 2) {
				sResultado = controlGeneral.getProductoProveedorController().mostrarProductoProveedores();
			}
		}
		System.out.println(sResultado);
	}

}
