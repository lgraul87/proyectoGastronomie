package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import controlador.database.ConexionDB;
import modelo.usuario.Usuario;

public class UsuarioController {

	/***************************************************************************************************************
	 * ADD USUARIO
	 ***************************************************************************************************************/
	public boolean add(Usuario oUsuario) {
		boolean bAniadido = false;
		boolean bCrearUsuario = false;
		boolean bCrearTipoUsuario = false;

		String sNombre = oUsuario.getsNombre();
		String sApellidos = oUsuario.getsApellidos();
		String sDni = oUsuario.getsDni();
		String sCorreo = oUsuario.getsCorreoElectronico();
		String sTipo = oUsuario.getoTipoUsuario().getsNombreTipoUsuario();

		int iTelefono = oUsuario.getiTelefono();

		if (!searchUser(sDni)) {
			bCrearUsuario = true;
		}
		if (!searchTipoUsuario(sTipo)) {
			bCrearTipoUsuario = true;
		}

		if (bCrearTipoUsuario) {
			createTipoUsuario(sTipo);
		}

		if (bCrearUsuario) {

			String sql = "";
			if (iTelefono == 0 && sCorreo == null) {
				sql = "INSERT INTO USUARIO VALUES ('" + sDni + "','" + sNombre + "','" + sApellidos + "'," + iTelefono
						+ ",NULL,'" + sTipo + "');";

			} else if (iTelefono != 0 && sCorreo == null) {
				sql = "INSERT INTO USUARIO VALUES ('" + sDni + "','" + sNombre + "','" + sApellidos + "'," + iTelefono
						+ ",NULL,'" + sTipo + "');";

			} else if (iTelefono == 0 && sCorreo != null) {
				sql = "INSERT INTO USUARIO VALUES ('" + sDni + "','" + sNombre + "','" + sApellidos + "'," + iTelefono
						+ ",'" + sCorreo + "','" + sTipo + "');";

			} else if (iTelefono != 0 && sCorreo != null) {
				sql = "INSERT INTO USUARIO VALUES ('" + sDni + "','" + sNombre + "','" + sApellidos + "'," + iTelefono
						+ ",'" + sCorreo + "','" + sTipo + "');";

			}

			ConexionDB.executeCount(sql);
			bAniadido = true;
		}

		return bAniadido;

	}

	private void createTipoUsuario(String sTipo) {

		String sql = "INSERT INTO TIPO_USUARIO VALUES ('" + sTipo + "');";

		ConexionDB.executeUpdate(sql);

	}

	private boolean searchTipoUsuario(String sTipo) {
		boolean bEncontrado = false;

		String sql = "SELECT COUNT(*) FROM tipo_usuario WHERE nombre_tipo = '" + sTipo + "';";

		if (ConexionDB.executeCount(sql) != 0) {
			bEncontrado = true;
		}

		return bEncontrado;

	}

	/***************************************************************************************************************
	 * REMOVE USUARIO
	 ***************************************************************************************************************/

	public boolean remove(String sDni) {
		boolean bRemove = false;
		if (searchUser(sDni)) {
			if (deleteUser(sDni)) {
				bRemove = true;
			}
		}
		return bRemove;
	}

	private boolean deleteUser(String sDni) {

		boolean bDelete = false;
		String sTipo = null;

		String sql3 = "SELECT * FROM USUARIO WHERE DNI = '" + sDni + "';";

		try {

			Statement statement = ConexionDB.getConnection().createStatement();

			ResultSet resultSet = statement.executeQuery(sql3);

			while (resultSet.next()) {
				sTipo = resultSet.getString("NOMBRE_USUARIO");
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sql = "DELETE FROM usuario WHERE dni = '" + sDni + "';";

		if (ConexionDB.executeUpdate(sql) > 0) {
			bDelete = true;

			String sql2 = "SELECT COUNT(*) FROM USUARIO WHERE NOMBRE_USUARIO = '" + sTipo + "';";

			if (ConexionDB.executeCount(sql2) == 0) {

				String sql4 = "DELETE FROM TIPO_USUARIO WHERE NOMBRE_TIPO = '" + sTipo + "';";
				ConexionDB.executeUpdate(sql4);
			}

		}

		return bDelete;
	}

	/***************************************************************************************************************
	 * SEARCH USER
	 ***************************************************************************************************************/
	public boolean searchUser(String sDni) {
		boolean bEncontrado = false;

		String sql = "SELECT COUNT(*) FROM usuario WHERE dni = '" + sDni + "';";

		if (ConexionDB.executeCount(sql) != 0) {
			bEncontrado = true;
		}

		return bEncontrado;

	}

	/***************************************************************************************************************
	 * 1-. MOSTRAR USUARIO
	 ***************************************************************************************************************/
	public String mostrarUsuario(String sDni) {
		String sResultado = null;
		int iTelefonoBD = 0;

		String sNombreBD = null;
		String sApellidosBD = null;
		String sDniBD = null;
		String sCorreoBD = null;
		String sTipoBD = null;

		String sql = "SELECT * FROM USUARIO WHERE DNI = '" + sDni + "';";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				sNombreBD = resultSet.getString("NOMBRE");
				sApellidosBD = resultSet.getString("APELLIDOS");
				sDniBD = resultSet.getString("DNI");
				iTelefonoBD = resultSet.getInt("TELEFONO");
				sCorreoBD = resultSet.getString("CORREO");
				sTipoBD = resultSet.getString("NOMBRE_USUARIO");

			}
			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (iTelefonoBD == 0 && sCorreoBD == null) {

			sResultado = "  --Nombre: " + sNombreBD + "  --Apellidos: " + sApellidosBD + "  --DNI: " + sDniBD
					+ "  --Tipo: " + sTipoBD;
		} else if (iTelefonoBD != 0 && sCorreoBD == null) {
			sResultado = "  --Nombre: " + sNombreBD + "  --Apellidos: " + sApellidosBD + "  --DNI: " + sDniBD
					+ "  --Telefono: " + iTelefonoBD + "  --Tipo: " + sTipoBD;
		} else if (iTelefonoBD == 0 && sCorreoBD != null) {
			sResultado = "  --Nombre: " + sNombreBD + "  --Apellidos: " + sApellidosBD + "  --DNI: " + sDniBD
					+ "  --email: " + sCorreoBD + "  --Tipo: " + sTipoBD;

		} else if (iTelefonoBD != 0 && sCorreoBD != null) {
			sResultado = "  --Nombre: " + sNombreBD + "  --Apellidos: " + sApellidosBD + "  --DNI: " + sDniBD
					+ "  --Telefono: " + iTelefonoBD + "  --email: " + sCorreoBD + "  --Tipo: " + sTipoBD;

		}

		return sResultado;
	}

	/***************************************************************************************************************
	 * 2-. MOSTRAR LISTA DE USUARIOS
	 ***************************************************************************************************************/
	public String mostrarUsuarios() {
		String sResultado = "No hay usuarios";

		int iTelefonoBD = 0;

		String sNombreBD = null;
		String sApellidosBD = null;
		String sDniBD = null;
		String sCorreoBD = null;
		String sTipoBD = null;

		String sql = "SELECT COUNT(*) FROM USUARIO;";
		if (ConexionDB.executeCount(sql) > 0) {

			sResultado = "";

			String sql2 = "SELECT * FROM USUARIO;";

			try {
				Statement statement = ConexionDB.getConnection().createStatement();
				ResultSet resultSet = statement.executeQuery(sql2);
				while (resultSet.next()) {

					sNombreBD = resultSet.getString("nombre");
					sApellidosBD = resultSet.getString("apellidos");
					sDniBD = resultSet.getString("dni");
					iTelefonoBD = resultSet.getInt("telefono");
					sCorreoBD = resultSet.getString("correo");
					sTipoBD = resultSet.getString("nombre_usuario");

					if (iTelefonoBD == 0 && sCorreoBD == null) {
						sResultado += "  --Nombre: " + sNombreBD + "  --Apellidos: " + sApellidosBD + "  --DNI: "
								+ sDniBD + "  --Tipo: " + sTipoBD + "\n";

					} else if (iTelefonoBD != 0 && sCorreoBD == null) {
						sResultado += "  --Nombre: " + sNombreBD + "  --Apellidos: " + sApellidosBD + "  --DNI: "
								+ sDniBD + "  --Telefono: " + iTelefonoBD + "  --Tipo: " + sTipoBD + "\n";

					} else if (iTelefonoBD == 0 && sCorreoBD != null) {
						sResultado += "  --Nombre: " + sNombreBD + "  --Apellidos: " + sApellidosBD + "  --DNI: "
								+ sDniBD + "  --email: " + sCorreoBD + "  --Tipo: " + sTipoBD + "\n";
					} else if (iTelefonoBD != 0 && sCorreoBD != null) {
						sResultado += "  --Nombre: " + sNombreBD + "  --Apellidos: " + sApellidosBD + "  --DNI: "
								+ sDniBD + "  --Telefono: " + iTelefonoBD + "  --email: " + sCorreoBD + "  --Tipo: "
								+ sTipoBD + "\n";
					}

				}
				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return sResultado;
	}
}
