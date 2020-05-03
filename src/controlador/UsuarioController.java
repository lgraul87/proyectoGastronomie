package controlador;

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

		boolean bExito = checkExisteUsuario(sql, sNombre, sApellidos, sDni, iTelefono, sCorreo, sTipoUsuario,bTelefono);

		return bExito;

	}

	public boolean checkExisteUsuario(String sql, String sNombre, String sApellidos, String sDni, int iTelefono,
			String sCorreo, String sTipoUsuario,boolean bTelefono) {

		boolean bExito = false;

		if (ConexionDB.executeCount(sql) == 0) {

			bExito = checkExisteTipoUsuario(sNombre, sApellidos, sDni, iTelefono, sCorreo, sTipoUsuario, bExito,bTelefono);

		}
		return bExito;
	}

	public boolean checkExisteTipoUsuario(String sNombre, String sApellidos, String sDni, int iTelefono, String sCorreo,
			String sTipoUsuario, boolean bExito,boolean bTelefono) {
		
		String sql2 = "SELECT COUNT(*) FROM tipo_usuario WHERE nombre_tipo = '" + sTipoUsuario + "';";

		if (ConexionDB.executeCount(sql2) == 0) {

			String sql3 = "INSERT INTO tipo_usuario VALUES ('" + sTipoUsuario + "');";
			ConexionDB.executeUpdate(sql3);
			String query = "";
			
			if (bTelefono) {
				 query = ""//
						//
						+ "INSERT INTO usuario VALUES ('" + sNombre + "','" + sApellidos + "','" + sDni + "'," + iTelefono
						+ ",'" + sCorreo + "','" + sTipoUsuario + "');";
			}else {
				 query = ""//
						//
						+ "INSERT INTO usuario VALUES ('" + sNombre + "','" + sApellidos + "','" + sDni + "',null,'" + sCorreo + "','" + sTipoUsuario + "');";
			}
			
			if (ConexionDB.executeUpdate(query) > 0) {
				bExito = true;

			}

		} else {
			String query = ""//
					//
					+ "INSERT INTO usuario VALUES ('" + sNombre + "','" + sApellidos + "','" + sDni + "'," + iTelefono
					+ ",'" + sCorreo + "','" + sTipoUsuario + "');";
			if (ConexionDB.executeUpdate(query) > 0) {
				bExito = true;

			}
		}
		return bExito;
	}

}
