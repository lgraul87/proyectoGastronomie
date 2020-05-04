package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controlador.database.ConexionDB;
import modelo.proveedor.Proveedor;

public class ProveedorController {

	public boolean add(Proveedor oProveedor) {

		boolean bAniadido = false;
		boolean bCrearProveedor = false;
		boolean bCrearTipoProveedor = false;

		String sNombre = oProveedor.getsNombreProveedor();
		int iTelefono = oProveedor.getiTelefono();
		String sCorreo = oProveedor.getsCorreoElectronico();
		String sDireccion = oProveedor.getsDireccion();
		String sTipo = oProveedor.getoTipoProveedor().getsNombreTipoProveedor();

		if (!searchProveedor(sNombre)) {
			bCrearProveedor = true;
		}
		if (!searchTipoProveedor(sTipo)) {
			bCrearTipoProveedor = true;
		}

		if (bCrearTipoProveedor) {
			createTipoProveedor(sTipo);
		}

		if (bCrearProveedor) {

			String sql = "";
			if (sCorreo == null) {
				sql = "INSERT INTO Proveedor VALUES ('" + sNombre + "'," + iTelefono + ",null,'" + sDireccion + "','"
						+ sTipo + "');";

			} else if (sCorreo != null) {
				sql = "INSERT INTO Proveedor VALUES ('" + sNombre + "'," + iTelefono + ",'" + sCorreo + "','"
						+ sDireccion + "','" + sTipo + "');";

			}

			ConexionDB.executeCount(sql);
			bAniadido = true;
		}

		return bAniadido;

	}

	private void createTipoProveedor(String sTipo) {

		String sql = "INSERT INTO tipo_proveedor VALUES ('" + sTipo + "');";

		ConexionDB.executeUpdate(sql);

	}

	private boolean searchTipoProveedor(String sTipo) {
		boolean bEncontrado = false;

		String sql = "SELECT COUNT(*) FROM tipo_Proveedor WHERE nombre_tipo = '" + sTipo + "';";

		if (ConexionDB.executeCount(sql) != 0) {
			bEncontrado = true;
		}

		return bEncontrado;

	}

	/***************************************************************************************************************
	 * REMOVE Proveedor
	 ***************************************************************************************************************/

	public boolean remove(String sNombre) {
		boolean bRemove = false;
		if (searchProveedor(sNombre)) {
			if (deleteProveedor(sNombre)) {
				bRemove = true;
			}
		}
		return bRemove;
	}

	private boolean deleteProveedor(String sNombre) {

		boolean bDelete = false;
		String sTipo = null;

		String sql3 = "SELECT * FROM Proveedor WHERE nombre_proveedor = '" + sNombre + "';";

		try {

			Statement statement = ConexionDB.getConnection().createStatement();

			ResultSet resultSet = statement.executeQuery(sql3);

			while (resultSet.next()) {
				sTipo = resultSet.getString("nombre_tipo");
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sql = "DELETE FROM Proveedor WHERE nombre_proveedor = '" + sNombre + "';";

		if (ConexionDB.executeUpdate(sql) > 0) {
			bDelete = true;

			String sql2 = "SELECT COUNT(*) FROM Proveedor WHERE NOMBRE_tipo = '" + sTipo + "';";

			if (ConexionDB.executeCount(sql2) == 0) {

				String sql4 = "DELETE FROM tipo_proveedor WHERE NOMBRE_TIPO = '" + sTipo + "';";
				ConexionDB.executeUpdate(sql4);
			}

		}

		return bDelete;
	}

	/***************************************************************************************************************
	 * SEARCH USER
	 ***************************************************************************************************************/
	public boolean searchProveedor(String sNombre) {
		boolean bEncontrado = false;

		String sql = "SELECT COUNT(*) FROM Proveedor WHERE nombre_proveedor = '" + sNombre + "';";

		if (ConexionDB.executeCount(sql) != 0) {
			bEncontrado = true;
		}

		return bEncontrado;

	}

	/***************************************************************************************************************
	 * 1-. MOSTRAR Proveedor
	 ***************************************************************************************************************/
	public String mostrarProveedor(String sNombre) {
		String sResultado = null;

		String sNombreBD = null;
		int iTelefonoBD = 0;
		String sCorreoBD = null;
		String sDireccionBD = null;
		String sTipoBD = null;

		String sql = "SELECT * FROM Proveedor WHERE nombre_proveedor = '" + sNombre + "';";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				sNombreBD = resultSet.getString("nombre_proveedor");
				iTelefonoBD = resultSet.getInt("telefono");
				sCorreoBD = resultSet.getString("correo");
				sDireccionBD = resultSet.getString("direccion");
				sTipoBD = resultSet.getString("nombre_tipo");

			}
			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (sCorreoBD == null) {

			sResultado = "  --Nombre: " + sNombreBD + "  --Telefono: " + iTelefonoBD + "  --Direccion: " + sDireccionBD
					+ "  --Tipo: " + sTipoBD;
		} else if (sCorreoBD != null) {
			sResultado = "  --Nombre: " + sNombreBD + "  --Telefono: " + iTelefonoBD + "  --email:" + sCorreoBD
					+ "  --Direccion: " + sDireccionBD + "  --Tipo: " + sTipoBD;
		}

		return sResultado;
	}

	/***************************************************************************************************************
	 * 2-. MOSTRAR LISTA DE ProveedoreS
	 ***************************************************************************************************************/
	public String mostrarProveedores() {
		String sResultado = "No hay Proveedores";

		String sNombreBD = null;
		int iTelefonoBD = 0;
		String sCorreoBD = null;
		String sDireccionBD = null;
		String sTipoBD = null;

		String sql = "SELECT COUNT(*) FROM Proveedor;";
		if (ConexionDB.executeCount(sql) > 0) {

			sResultado = "";

			String sql2 = "SELECT * FROM Proveedor;";

			try {
				Statement statement = ConexionDB.getConnection().createStatement();
				ResultSet resultSet = statement.executeQuery(sql2);
				while (resultSet.next()) {

					sNombreBD = resultSet.getString("nombre_proveedor");
					iTelefonoBD = resultSet.getInt("telefono");
					sCorreoBD = resultSet.getString("correo");
					sDireccionBD = resultSet.getString("direccion");
					sTipoBD = resultSet.getString("nombre_tipo");

					if (sCorreoBD == null) {

						sResultado += "  --Nombre: " + sNombreBD + "  --Telefono: " + iTelefonoBD + "  --Direccion: "
								+ sDireccionBD + "  --Tipo: " + sTipoBD + "\n";
					} else if (sCorreoBD != null) {
						sResultado += "  --Nombre: " + sNombreBD + "  --Telefono: " + iTelefonoBD + "  --email:"
								+ sCorreoBD + "  --Direccion: " + sDireccionBD + "  --Tipo: " + sTipoBD + "\n";
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
