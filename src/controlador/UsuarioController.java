package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import controlador.database.ConexionDB;
import modelo.usuario.TipoUsuario;
import modelo.usuario.Usuario;
import vista.usuario.UsuarioView;

public class UsuarioController implements IUsuarioController {

	/***************************************************************************************************************
	 * ADD USUARIO
	 ***************************************************************************************************************/
	@Override
	public boolean add(Usuario oUsuario) {
		boolean bAniadido = false;
		boolean bCrearUsuario = false;
		boolean bCrearTipoUsuario = false;

		String sNombre = oUsuario.getsNombre();
		String sApellidos = oUsuario.getsApellidos();
		String sDni = oUsuario.getsDni();
		String sCorreo = oUsuario.getsCorreoElectronico();
		String sContrasenia = oUsuario.getsContrasenia();

		String hash = UsuarioView.encryptThisString(sContrasenia);

		String sTipo = oUsuario.getoTipoUsuario().getsNombreTipoUsuario();

		String sTelefono = oUsuario.getsTelefono();

		int iTelefono;
		try {
			iTelefono = Integer.parseInt(sTelefono);
		} catch (Exception e) {
			iTelefono = 0;
		}

		Usuario oUsuario2 = new Usuario(sDni);

		if (!searchUser(oUsuario2)) {
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
						+ ",NULL,'" + hash + "','" + sTipo + "');";

			} else if (iTelefono != 0 && sCorreo == null) {
				sql = "INSERT INTO USUARIO VALUES ('" + sDni + "','" + sNombre + "','" + sApellidos + "'," + iTelefono
						+ ",NULL,'" + hash + "','" + sTipo + "');";

			} else if (iTelefono == 0 && sCorreo != null) {
				sql = "INSERT INTO USUARIO VALUES ('" + sDni + "','" + sNombre + "','" + sApellidos + "'," + iTelefono
						+ ",'" + sCorreo + "','" + hash + "','" + sTipo + "');";

			} else if (iTelefono != 0 && sCorreo != null) {
				sql = "INSERT INTO USUARIO VALUES ('" + sDni + "','" + sNombre + "','" + sApellidos + "'," + iTelefono
						+ ",'" + sCorreo + "','" + hash + "','" + sTipo + "');";

			}

			if (ConexionDB.executeUpdate(sql) != 0) {
				bAniadido = true;

			}
		}

		return bAniadido;

	}

	@Override
	public void createTipoUsuario(String sTipo) {

		String sql = "INSERT INTO TIPO_USUARIO VALUES ('" + sTipo + "');";

		ConexionDB.executeUpdate(sql);

	}

	@Override
	public boolean searchTipoUsuario(String sTipo) {
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

	@Override
	public boolean remove(Usuario oUsuario) {
		boolean bRemove = false;
		if (searchUser(oUsuario)) {
			if (deleteUser(oUsuario)) {
				bRemove = true;
			}
		}
		return bRemove;
	}

	@Override
	public boolean deleteUser(Usuario oUsuario) {

		boolean bDelete = false;
		String sTipo = null;

		String sDni = oUsuario.getsDni();

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
	@Override
	public boolean searchUser(Usuario oUsuario) {
		boolean bEncontrado = false;
		String sDni = oUsuario.getsDni();
		String sql = "SELECT COUNT(*) FROM usuario WHERE dni = '" + sDni + "';";

		if (ConexionDB.executeCount(sql) != 0) {
			bEncontrado = true;
		}

		return bEncontrado;

	}

	/***************************************************************************************************************
	 * 1-. MOSTRAR USUARIO
	 ***************************************************************************************************************/
	@Override
	public String mostrarUsuario(Usuario oUsuario) {
		String sResultado = null;
		int iTelefonoBD = 0;

		String sNombreBD = null;
		String sApellidosBD = null;
		String sDniBD = null;
		String sCorreoBD = null;
		String sTipoBD = null;

		String sDni = oUsuario.getsDni();

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
	@Override
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

	@Override
	public Usuario determinarUsuarioPedido(Usuario oUsuario) {

		String sDni = oUsuario.getsDni();

		String sql2 = "SELECT COUNT(*) FROM USUARIO WHERE DNI = '" + sDni + "';";

		if (ConexionDB.executeCount(sql2) != 0) {

			String sql = "SELECT * FROM USUARIO WHERE DNI = '" + sDni + "';";

			try {
				Statement statement = ConexionDB.getConnection().createStatement();
				ResultSet resulset = statement.executeQuery(sql);

				while (resulset.next()) {
					String sDniBD = resulset.getString("dni");
					String sNombreBD = resulset.getString("nombre");
					String sApellidosBD = resulset.getString("apellidos");
					int iTelefonoBD = resulset.getInt("telefono");
					String sCorreoBD = resulset.getString("correo");
					String sContrasenia = resulset.getString("contrasenia");
					String sTipoBD = resulset.getString("nombre_usuario");

					String sTelefonoParse = Integer.toString(iTelefonoBD);

					TipoUsuario oTipoUsuario = new TipoUsuario(sTipoBD);

					if (iTelefonoBD == 0 && sCorreoBD == null) {
						oUsuario = new Usuario(sNombreBD, sDniBD, sApellidosBD, sContrasenia, oTipoUsuario);
					} else if (iTelefonoBD != 0 && sCorreoBD == null) {
						oUsuario = new Usuario(sNombreBD, sDniBD, sApellidosBD, sTelefonoParse, "null", sContrasenia,
								oTipoUsuario);
					} else if (iTelefonoBD == 0 && sCorreoBD != null) {
						oUsuario = new Usuario(sNombreBD, sDniBD, sApellidosBD, "null", sCorreoBD, sContrasenia,
								oTipoUsuario);
					} else if (iTelefonoBD != 0 && sCorreoBD != null) {
						oUsuario = new Usuario(sNombreBD, sDniBD, sApellidosBD, sTelefonoParse, sCorreoBD, sContrasenia,
								oTipoUsuario);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return oUsuario;

	}

	@Override
	public boolean login(String hash) {
		boolean bLogin = false;

		String sql = "SELECT COUNT(*) FROM USUARIO WHERE CONTRASENIA = '" + hash + "';";

		if (ConexionDB.executeCount(sql) != 0) {
			bLogin = true;
		}

		return bLogin;
	}

	
	
}
