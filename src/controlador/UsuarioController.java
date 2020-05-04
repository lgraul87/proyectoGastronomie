package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controlador.database.ConexionDB;
import modelo.usuario.Usuario;

public class UsuarioController {

	public boolean add(Usuario oUsuario) {

		boolean bTelefono = false;

		String sNombre = oUsuario.getsNombre();
		String sApellidos = oUsuario.getsApellidos();
		String sDni = oUsuario.getsDni();
		int iTelefono = oUsuario.getiTelefono();

		if (iTelefono != 0) {
			bTelefono = true;
		}
		String sCorreo = oUsuario.getsCorreoElectronico();

		String sTipoUsuario = oUsuario.getoTipoUsuario().getsNombreTipoUsuario();

		String sql = "SELECT COUNT(*) FROM usuario WHERE dni = '" + sDni + "';";

		boolean bExito = checkExisteUsuario(sql, sNombre, sApellidos, sDni, iTelefono, sCorreo, sTipoUsuario,
				bTelefono);

		return bExito;

	}

	public boolean checkExisteUsuario(String sql, String sNombre, String sApellidos, String sDni, int iTelefono,
			String sCorreo, String sTipoUsuario, boolean bTelefono) {

		boolean bExito = false;

		if (ConexionDB.executeCount(sql) == 0) {

			bExito = checkExisteTipoUsuario(sNombre, sApellidos, sDni, iTelefono, sCorreo, sTipoUsuario, bExito,
					bTelefono);

		}
		return bExito;
	}

	public boolean checkExisteTipoUsuario(String sNombre, String sApellidos, String sDni, int iTelefono, String sCorreo,
			String sTipoUsuario, boolean bExito, boolean bTelefono) {

		String sql2 = "SELECT COUNT(*) FROM tipo_usuario WHERE nombre_tipo = '" + sTipoUsuario + "';";

		if (ConexionDB.executeCount(sql2) == 0) {

			String sql3 = "INSERT INTO tipo_usuario VALUES ('" + sTipoUsuario + "');";
			ConexionDB.executeUpdate(sql3);
			String query = "";

			if (bTelefono) {
				query = ""//
							//
						+ "INSERT INTO usuario VALUES ('" + sDni + "','" + sNombre + "','" + sApellidos + "',"
						+ iTelefono + ",'" + sCorreo + "','" + sTipoUsuario + "');";
			} else {
				query = ""//
							//
						+ "INSERT INTO usuario VALUES ('" + sDni + "','" + sNombre + "','" + sApellidos + "',null,'"
						+ sCorreo + "','" + sTipoUsuario + "');";
			}

			if (ConexionDB.executeUpdate(query) > 0) {
				bExito = true;

			}

		} else {
			String query = ""//
					//
					+ "INSERT INTO usuario VALUES ('" + sDni + "','" + sNombre + "','" + sApellidos + "'," + iTelefono
					+ ",'" + sCorreo + "','" + sTipoUsuario + "');";
			if (ConexionDB.executeUpdate(query) > 0) {
				bExito = true;

			}
		}
		return bExito;
	}

	public boolean remove(String sDni) {
		boolean bRemove = false;
		if (searchUser(sDni)) {
			if (deleteUser(sDni)) {
				bRemove = true;
			}
		}
		return bRemove;
	}

	private boolean searchUser(String sDni) {
		boolean bEncontrado = false;

		String sql = "SELECT COUNT(*) FROM usuario WHERE dni = '" + sDni + "';";

		if (ConexionDB.executeCount(sql) != 0) {
			bEncontrado = true;
		}

		return bEncontrado;

	}

	private boolean deleteUser(String sDni) {

		boolean bDelete = false;
		String sTipo = null;

		String sql3 = "SELECT NOMBRE_USUARIO FROM USUARIO WHERE DNI = '" + sDni + "';";

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

}
