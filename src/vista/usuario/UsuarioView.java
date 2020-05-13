package vista.usuario;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import controlador.database.GeneralController;
import modelo.usuario.TipoUsuario;
import modelo.usuario.Usuario;
import validaciones.L;

public class UsuarioView {

	/***********************************************************************************************
	 * ADMIN
	 ***********************************************************************************************/
	public static void operacionesUsuario(GeneralController controlGeneral) {
		byte bOption = 0;
		boolean errorControl = true;
		do {
			try {
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
						+ "\n  --Aniadir Usuario:    (1)"
						//
						+ "\n  --Borrar Usuario:     (2)"
						//
						+ "\n  --Buscar Usuario:     (3)"
						//
						+ "\n  --Mostrar Usuario:    (4)"
						//
						+ "\n  --Salir:              (5)", 1, 5, 3);

				errorControl = false;

			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			//

			if (bOption == 1) {
				aniadirUsuario(controlGeneral);
			} else if (bOption == 2) {
				borrarUsuario(controlGeneral);
			} else if (bOption == 3) {
				buscarUsuario(controlGeneral);
			} else if (bOption == 4) {
				mostrarUsuario(controlGeneral);
			}
		} while (errorControl);
	}

	/***********************************************************************************************
	 * ANIADIR USUARIO
	 ***********************************************************************************************/
	public static void aniadirUsuario(GeneralController controlGeneral) {

		String sResultado = "";
		String sTelefono = null;
		String sCorreo = null;
		boolean bTelefono = false;
		boolean bCorreo = false;
		Usuario oUsuario = null;
		int bOptionTelefono = 0;
		int bOptionCorreo = 0;

		String sNombre = L.leer("Nombre (Usuario): ");
		String sApellidos = L.leer("Apellido (Usuario): ");

		String sDni = L.leer("Dni (Usuario): ");

		String sTipoUsuario = L.leer("Tipo (Usuario): ");

		boolean errorControl = true;
		do {

			try {
				bOptionTelefono = (byte) L.valida("¿Desea aniadir un telefono (Usuario):?" + "\nSi: (1)" + "\nNo: (2)",
						1, 2, 3);

				errorControl = false;

			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} while (errorControl);

		if (bOptionTelefono == 1) {

			sTelefono = L.leer("Telefono (Usuario): ");

			bTelefono = true;

		}

		errorControl = true;
		do {
			try {
				bOptionCorreo = (byte) L.valida("¿Desea aniadir un correo (Usuario):?" + "\nSi: (1)" + "\nNo: (2)", 1,
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

		String sUsuarioLogin = L.leer("Contrasenia: (Usuario)");

		// String hash = Gastronomie.encryptThisString(sUsuarioLogin);

		if (!bTelefono && !bCorreo) {
			oUsuario = new Usuario(sNombre, sDni, sApellidos, sUsuarioLogin, oTipoUsuario);

		} else if (bTelefono && !bCorreo) {
			oUsuario = new Usuario(sNombre, sDni, sApellidos, sTelefono, "null", sUsuarioLogin, oTipoUsuario);

		} else if (!bTelefono && bCorreo) {
			oUsuario = new Usuario(sNombre, sDni, sApellidos, "null", sCorreo, sUsuarioLogin, oTipoUsuario);

		} else if (bTelefono && bCorreo) {
			oUsuario = new Usuario(sNombre, sDni, sApellidos, sTelefono, sCorreo, sUsuarioLogin, oTipoUsuario);

		}

		boolean bAccion = controlGeneral.getUsuarioController().add(oUsuario);

		if (bAccion) {
			sResultado = "Aniadido correctamente.";
		} else {
			sResultado = "No se pudo aniadir.";
		}
		System.out.println(sResultado);
	}

	/***********************************************************************************************
	 * BORRAR USUARIO
	 ***********************************************************************************************/

	public static void borrarUsuario(GeneralController controlGeneral) {

		String sResultado = "No hay usuarios";


		if (!controlGeneral.getUsuarioController().mostrarUsuarios().equals("No hay usuarios")) {
			System.out.println(controlGeneral.getUsuarioController().mostrarUsuarios());

			sResultado = "No se pudo borrar el usuario";
			String sDni = L.leer("Dni (Usuario):");
			Usuario oUsuario = new Usuario(sDni);

			if (controlGeneral.getUsuarioController().remove(oUsuario)) {
				sResultado = "Usuario borrado";

			}
		}
		System.out.println(sResultado);

	}

	/***********************************************************************************************
	 * BUSCAR USUARIO
	 ***********************************************************************************************/
	public static void buscarUsuario(GeneralController controlGeneral) {
		String sResultado = "No hay usuarios";

		if (!controlGeneral.getUsuarioController().mostrarUsuarios().equals("No hay usuarios")) {
			System.out.println(controlGeneral.getUsuarioController().mostrarUsuarios());

			sResultado = "Usuario no registrado";
			String sDni = L.leer("Dni (Usuario): ");

			Usuario oUsuario = new Usuario(sDni);

			if (controlGeneral.getUsuarioController().searchUser(oUsuario)) {
				sResultado = "Usuario registrado";
			}
		}
		System.out.println(sResultado);
	}

	/***********************************************************************************************
	 * MOSTRAR USUARIO
	 ***********************************************************************************************/
	public static void mostrarUsuario(GeneralController controlGeneral) {

		String sResultado = "No hay usuarios";

		if (!controlGeneral.getUsuarioController().mostrarUsuarios().equals("No hay usuarios")) {

			byte bOption = 0;

			boolean errorControl = true;

			do {
				try {
					bOption = (byte) L.valida(""
							//
							+ "Un usuario: 		(1)"
							//
							+ "\nLista de usuarios: 	(2)", 1, 2, 3);

					errorControl = false;

				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			if (bOption == 1) {

				String sDni = L.leer("Dni (Usuario):");
				Usuario oUsuario = new Usuario(sDni);

				if (controlGeneral.getUsuarioController().searchUser(oUsuario)) {
					sResultado = controlGeneral.getUsuarioController().mostrarUsuario(oUsuario);

				} else {
					sResultado = "Usuario no encontrado";
				}

			} else if (bOption == 2) {
				sResultado = controlGeneral.getUsuarioController().mostrarUsuarios();
			}
		}
		System.out.println(sResultado);
	}

	public static String buscarUsuarioLogin(GeneralController controlGeneral) {
		String sUsuario = "";
		String sUsuarioLogin = "";

		if (!controlGeneral.getUsuarioController().mostrarUsuarios().equals("No hay usuarios")) {

			sUsuarioLogin = L.leer("Contrasenia: (Usuario)");

			String hash = UsuarioView.encryptThisString(sUsuarioLogin);

			if (controlGeneral.getUsuarioController().login(hash)) {
				sUsuario = sUsuarioLogin;
			}
		} else {
			System.out.println("No hay usuarios");
		}
		return sUsuario;
	}
	public  static String encryptThisString(String input) {
		try {
			// getInstance() method is called with algorithm SHA-512
			MessageDigest md = MessageDigest.getInstance("SHA-512");

			// digest() method is called
			// to calculate message digest of the input string
			// returned as array of byte
			byte[] messageDigest = md.digest(input.getBytes());

			// Convert byte array into signum representation
			BigInteger no = new BigInteger(1, messageDigest);

			// Convert message digest into hex value
			String hashtext = no.toString(16);

			// Add preceding 0s to make it 32 bit
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			// return the HashText
			return hashtext;
		}

		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
