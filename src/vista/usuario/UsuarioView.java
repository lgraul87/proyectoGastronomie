package vista.usuario;

import controlador.database.GeneralController;
import modelo.usuario.TipoUsuario;
import modelo.usuario.Usuario;
import validaciones.L;

public class UsuarioView {

	/***********************************************************************************************
	 * ADMIN
	 * 
	 * @param controlGeneral
	 ***********************************************************************************************/
	public static void operacionesUsuario(GeneralController controlGeneral) {
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
				aniadirUsuario(controlGeneral);
			} else if (bOption == 2) {
				borrarUsuario();
			} else if (bOption == 3) {
				buscarUsuario();
			} else if (bOption == 4) {
				mostrarUsuario();
			}
		} while (bOption != 5);
	}

	private static void aniadirUsuario(GeneralController controlGeneral) {

		String sResultado = "";
		int iTelefono = 0;
		String sCorreo = null;
		boolean bTelefono = false;
		boolean bCorreo = false;
		Usuario oUsuario = null;
		int bOptionTelefono = 0;
		int bOptionCorreo = 0;
		String sDni;

		String sNombre = L.leer("Nombre (Usuario): ");
		String sApellidos = L.leer("Apellido (Usuario): ");

		
			sDni = L.leer("Dni (Usuario): ");


		String sTipoUsuario = L.leer("Tipo (Usuario): ");

		boolean errorControl = true;
		do {

			try {
				bOptionTelefono = (byte) L.valida("�Desea aniadir un telefono (Usuario):?" + "\nSi: (1)" + "\nNo: (2)",
						1, 2, 3);

				errorControl = false;

			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} while (errorControl);

		if (bOptionTelefono == 1) {
			errorControl = true;
			do {
				try {
					iTelefono = (int) L.valida("Telefono (Usuario):?", 100000000, 999999999, 1);
					errorControl = false;
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			bTelefono = true;

		}

		errorControl = true;
		do {
			try {
				bOptionCorreo = (byte) L.valida("�Desea aniadir un correo (Usuario):?" + "\nSi: (1)" + "\nNo: (2)", 1,
						2, 3);
				errorControl = false;
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (errorControl);

		if (bOptionCorreo == 1) {
			sCorreo = L.leer("E-mail (Usuario):");
			bCorreo = true;

		}

		TipoUsuario oTipoUsuario = new TipoUsuario(sTipoUsuario);

		if (!bTelefono && !bCorreo) {
			oUsuario = new Usuario(sNombre, sDni, sApellidos, oTipoUsuario);

		} else if (bTelefono && !bCorreo) {
			oUsuario = new Usuario(sNombre, sDni, sApellidos, iTelefono, oTipoUsuario);

		} else if (!bTelefono && bCorreo) {
			oUsuario = new Usuario(sNombre, sDni, sApellidos, sCorreo, oTipoUsuario);

		} else {
			oUsuario = new Usuario(sNombre, sDni, sApellidos, iTelefono, sCorreo, oTipoUsuario);

		}

		boolean bAccion = controlGeneral.getUsuarioController().add(oUsuario);

		if (bAccion) {
			sResultado = "Aniadido correctamente.";
		} else {
			sResultado = "No se pudo aniadir.";
		}
		System.out.println(sResultado);
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
