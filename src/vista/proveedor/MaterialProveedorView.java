package vista.proveedor;

import controlador.database.GeneralController;
import modelo.genero.Material;
import modelo.proveedor.Proveedor;
import validaciones.L;

public class MaterialProveedorView {

	/***********************************************************************************************
	 * ADMIN
	 ***********************************************************************************************/
	public static void operacionesMaterialProveedor(GeneralController controlGeneral) {
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
					+ "\n  --Asignar Material a Proveedor (Lista): 	(1)"
					//
					+ "\n  --Borrar Material a Proveedor (Lista):     	(2)"
					//
					+ "\n  --Buscar Material de Proveedor (Lista): 	(3)"
					//
					+ "\n  --Mostrar Material de Proveedor (Lista): 	(4)"
					//
					+ "\n  --Salir: (5)", 1, 5, 3);
			//

			if (bOption == 1) {
				asignarMaterialProveedor(controlGeneral);
			} else if (bOption == 2) {
				borrarMaterialProveedor(controlGeneral);
			} else if (bOption == 3) {
				buscarMaterialProveedor(controlGeneral);
			} else if (bOption == 4) {
				mostrarMaterialProveedor(controlGeneral);
			}
		} while (bOption != 5);
	}

	public static void asignarMaterialProveedor(GeneralController controlGeneral) {

		String sResultado = "No se pudo aniadir";
		String sNombreMaterial = L.leer("Nombre de producto a integrar en la lista: ");
		String sNombreProveedor = L.leer("Nombre de proveedor a integrar en la lista: ");
		Material oMaterial = new Material(sNombreMaterial);
		Proveedor oProveedor = new Proveedor(sNombreProveedor);

		if (controlGeneral.getMaterialProveedorController().add(oMaterial, oProveedor)) {
			sResultado = "Aniadido";
		}
		System.out.println(sResultado);
	}

	public static void borrarMaterialProveedor(GeneralController controlGeneral) {

		String sResultado = "No se pudo borrar";

		String sNombreMaterial = L.leer("Nombre de producto a borrar en la lista: ");
		String sNombreProveedor = L.leer("Nombre de proveedor a borrar en la lista: ");
		Material oMaterial = new Material(sNombreMaterial);
		Proveedor oProveedor = new Proveedor(sNombreProveedor);

		if (controlGeneral.getMaterialProveedorController().remove(oMaterial, oProveedor)) {
			sResultado = "Borrado";
		}
		System.out.println(sResultado);
	}

	public static void buscarMaterialProveedor(GeneralController controlGeneral) {

		String sResultado = "No se pudo encontrar";

		String sNombreMaterial = L.leer("Nombre de producto a buscar en la lista: ");
		String sNombreProveedor = L.leer("Nombre de proveedor a buscar en la lista: ");
		Material oMaterial = new Material(sNombreMaterial);
		Proveedor oProveedor = new Proveedor(sNombreProveedor);

		if (controlGeneral.getMaterialProveedorController().search(oMaterial, oProveedor)) {
			sResultado = "Encontrado";
		}
		System.out.println(sResultado);
	}

	public static void mostrarMaterialProveedor(GeneralController controlGeneral) {

		String sResultado = "No hay Listas";

		if (!controlGeneral.getMaterialProveedorController().mostrarMaterialProveedores().equals("No hay Listas")) {

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
				sResultado = controlGeneral.getMaterialProveedorController().mostrarMaterialProveedor(oProveedor);

			} else if (bOption == 2) {
				sResultado = controlGeneral.getMaterialProveedorController().mostrarMaterialProveedores();
			}
		}
		System.out.println(sResultado);
	}

}
