package vista.instalacion;

import controlador.database.GeneralController;
import modelo.instalacion.Instalacion;
import validaciones.L;

public class InstalacionView {

	/***********************************************************************************************
	 * ADMIN
	 * 
	 * @param controlGeneral
	 ***********************************************************************************************/
	public static void operacionesInstalacion(GeneralController controlGeneral) {
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
					+ "\n  --Aniadir Instalacion (Alquiler): 	(1)"
					//
					+ "\n  --Borrar Instalacion (Alquiler):     	(2)"
					//
					+ "\n  --Buscar Instalacion (Alquiler): 	(3)"
					//
					+ "\n  --Mostrar Instalacion (Alquiler): 	(4)"
					//
					+ "\n  --Salir: 				(5)", 1, 5, 3);
			//

			if (bOption == 1) {
				aniadirInstalacion(controlGeneral);
			} else if (bOption == 2) {
				borrarInstalacion(controlGeneral);
			} else if (bOption == 3) {
				buscarInstalacion(controlGeneral);
			} else if (bOption == 4) {
				mostrarInstalacion(controlGeneral);
			}
		} while (bOption != 5);
	}

	private static void aniadirInstalacion(GeneralController controlGeneral) {

		String sInstalacion = "No se aniadio la instalacion";
		float fCouta = 0;
		Instalacion oInstalacion = null;
		String sDescripcion = null;

		String sNombre = L.leer("Nombre: (Instalacion)");
		
		
		String sDireccion = L.leer("Direccion: (Instalacion)");
		
		

		boolean errorControl = true;
		do {
			try {
				fCouta = (float) L.valida("Couta: (Instalacion)", 1F, 3000F, 2);
				errorControl = false;
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (errorControl);

		byte bOption = 0;
		errorControl = true;
		do {
			try {
				bOption = (byte) L.valida("¿Desea incluir una descripcion? (Instalacion): " + "\nSi: (1)" + "\nNo: (2)",
						1, 2, 3);
				errorControl = false;
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} while (errorControl);

		
		if (bOption == 1) {
			sDescripcion = L.leer("Descripcion: (Instalacion)");
			oInstalacion = new Instalacion(sNombre, sDescripcion, sDireccion, fCouta);

		} else if (bOption == 2) {
			oInstalacion = new Instalacion(sNombre, sDireccion, fCouta);

		}

		if (controlGeneral.getInstalacionController().add(oInstalacion)) {
			sInstalacion = "Aniaido correctamente";
		}

		System.out.println(sInstalacion);

	}

	private static void borrarInstalacion(GeneralController controlGeneral) {

		String sResultado = "No se pudo borrar";

		String sNombre = L.leer("Nombre: (Instalacion)");

		if (controlGeneral.getInstalacionController().remove(sNombre)) {
			sResultado = "Borrado satisfactorio";
		}
		System.out.println(sResultado);
	}

	private static void buscarInstalacion(GeneralController controlGeneral) {

		String sResultado = "No esta registrado";

		String sNombre = L.leer("Nombre: (Instalacion)");

		if (controlGeneral.getInstalacionController().search(sNombre)) {
			sResultado = "Encontrado, esta registrado";
		}
		System.out.println(sResultado);
	}

	private static void mostrarInstalacion(GeneralController controlGeneral) {
		String sResultado = null;

		byte bOption = 0;

		boolean errorControl = true;

		do {
			try {
				bOption = (byte) L.valida(""
						//
						+ "Una instalacion: 		(1)"
						//
						+ "\nLista de instalaciones: 	(2)", 1, 2, 3);

				errorControl = false;

			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (errorControl);

		if (bOption == 1) {

			String sNombre = L.leer("Nombre (Instalacion):");

			if (controlGeneral.getInstalacionController().search(sNombre)) {
				sResultado = controlGeneral.getInstalacionController().mostrarInstalacion(sNombre);

			} else {
				sResultado = "Instalacion no encontrada";
			}

		} else if (bOption == 2) {
			sResultado = controlGeneral.getInstalacionController().mostrarInstalaciones();
		}
		System.out.println(sResultado);
	}

}
