package vista.proveedor;

import controlador.database.GeneralController;
import modelo.proveedor.Proveedor;
import modelo.proveedor.TipoProveedor;
import validaciones.L;

public class ProveedorView {
	/***********************************************************************************************
	 * ADMIN
	 * 
	 * @param controlGeneral
	 ***********************************************************************************************/
	public static void operacionesProveedor(GeneralController controlGeneral) {
		byte bOption = 0;
		boolean errorControl = true;
		do {
			try {
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
						+ "\n  --Aniadir Proveedor (Compra): 	(1)"
						//
						+ "\n  --Borrar Proveedor (Compra):     	(2)"
						//
						+ "\n  --Buscar Proveedor (Compra): 		(3)"
						//
						+ "\n  --Mostrar Proveedor (Compra): 	(4)"
						//
						+ "\n  --Salir: 				(5)", 1, 5, 3);

				errorControl = false;

			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			//

			if (bOption == 1) {
				aniadirProveedor(controlGeneral);
			} else if (bOption == 2) {
				borrarProveedor(controlGeneral);
			} else if (bOption == 3) {
				buscarProveedor(controlGeneral);
			} else if (bOption == 4) {
				mostrarProveedor(controlGeneral);
			}
		} while (errorControl);
	}

	/***********************************************************************************************
	 * ANIADIR Proveedor
	 ***********************************************************************************************/
	public static void aniadirProveedor(GeneralController controlGeneral) {

		String sResultado = "";
		String sCorreo = null;
		boolean bCorreo = false;
		Proveedor oProveedor = null;
		int bOptionCorreo = 0;

		String sNombre = L.leer("Nombre (Proveedor): ");

		String sTelefono = L.leer("Telefono (Proveedor): ");

		boolean errorControl = true;
		do {
			try {
				bOptionCorreo = (byte) L.valida("¿Desea aniadir un correo (Proveedor):?" + "\nSi: (1)" + "\nNo: (2)", 1,
						2, 3);
				errorControl = false;
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (errorControl);

		if (bOptionCorreo == 1) {
			sCorreo = L.leer("E-mail (Proveedor):");
			bCorreo = true;

		}

		String sDireccion = L.leer("Direccion (Proveedor): ");

		String sTipoProveedor = L.leer("Tipo (Proveedor): ");

		TipoProveedor oTipoProveedor = new TipoProveedor(sTipoProveedor);

		if (bCorreo) {
			oProveedor = new Proveedor(sNombre, sTelefono, sCorreo, sDireccion, oTipoProveedor);

		} else if (!bCorreo) {
			oProveedor = new Proveedor(sNombre, sTelefono, sDireccion, oTipoProveedor);

		}

		boolean bAccion = controlGeneral.getProveedorController().add(oProveedor);

		if (bAccion) {
			sResultado = "Aniadido correctamente.";
		} else {
			sResultado = "No se pudo aniadir.";
		}
		System.out.println(sResultado);
	}

	/***********************************************************************************************
	 * BORRAR Proveedor
	 ***********************************************************************************************/

	public static void borrarProveedor(GeneralController controlGeneral) {

		String sResultado = "No hay Proveedores";


		if (!controlGeneral.getProveedorController().mostrarProveedores().equals("No hay Proveedores")) {
			System.out.println(controlGeneral.getProveedorController().mostrarProveedores());

			sResultado = "No se pudo borrar el Proveedor";
			String sNombre = L.leer("Nombre (Proveedor):");

			Proveedor oProveedor = new Proveedor(sNombre);

			if (controlGeneral.getProveedorController().remove(oProveedor)) {
				sResultado = "Proveedor borrado";
			}
		}
		System.out.println(sResultado);

	}

	/***********************************************************************************************
	 * BUSCAR Proveedor
	 ***********************************************************************************************/
	public static void buscarProveedor(GeneralController controlGeneral) {
		String sResultado = "No hay Proveedores";


		if (!controlGeneral.getProveedorController().mostrarProveedores().equals("No hay Proveedores")) {
			
			System.out.println(controlGeneral.getProveedorController().mostrarProveedores());

			sResultado = "Proveedor no registrado";

			String sNombre = L.leer("Nombre (Proveedor): ");

			Proveedor oProveedor = new Proveedor(sNombre);

			if (controlGeneral.getProveedorController().searchProveedor(oProveedor)) {
				sResultado = "Proveedor registrado";
			}
		}
		System.out.println(sResultado);
	}

	/***********************************************************************************************
	 * MOSTRAR Proveedor
	 ***********************************************************************************************/
	public static void mostrarProveedor(GeneralController controlGeneral) {

		String sResultado = "No hay Proveedores";

		if (!controlGeneral.getProveedorController().mostrarProveedores().equals("No hay Proveedores")) {

			byte bOption = 0;

			boolean errorControl = true;

			do {
				try {
					bOption = (byte) L.valida(""
							//
							+ "Un Proveedor: 		(1)"
							//
							+ "\nLista de Proveedores: 	(2)", 1, 2, 3);

					errorControl = false;

				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			if (bOption == 1) {

				String sNombre = L.leer("Nombre (Proveedor):");
				Proveedor oProveedor = new Proveedor(sNombre);
				if (controlGeneral.getProveedorController().searchProveedor(oProveedor)) {
					sResultado = controlGeneral.getProveedorController().mostrarProveedor(oProveedor);

				} else {
					sResultado = "Proveedor no encontrado";
				}

			} else if (bOption == 2) {
				sResultado = controlGeneral.getProveedorController().mostrarProveedores();
			}
		}
		System.out.println(sResultado);
	}

}
