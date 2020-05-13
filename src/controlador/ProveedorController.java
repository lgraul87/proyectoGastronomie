package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controlador.database.ConexionDB;
import modelo.proveedor.Proveedor;

public class ProveedorController implements IProveedorController {

	@Override
	public boolean add(Proveedor oProveedor) {

		boolean bAniadido = false;
		boolean bCrearProveedor = false;
		boolean bCrearTipoProveedor = false;

		String sNombre = oProveedor.getsNombreProveedor();
		String sTelefono = oProveedor.getsTelefono();
		int iTelefono;
		try {
			 iTelefono = Integer.parseInt(sTelefono);

		} catch (Exception e) {
			iTelefono = 0;
		}
		String sCorreo = oProveedor.getsCorreoElectronico();
		String sDireccion = oProveedor.getsDireccion();
		String sTipo = oProveedor.getoTipoProveedor().getsNombreTipoProveedor();

		if (!searchProveedor(oProveedor)) {
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

	@Override
	public void createTipoProveedor(String sTipo) {

		String sql = "INSERT INTO tipo_proveedor VALUES ('" + sTipo + "');";

		ConexionDB.executeUpdate(sql);

	}

	@Override
	public boolean searchTipoProveedor(String sTipo) {
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

	@Override
	public boolean remove(Proveedor oProveedor) {
		boolean bRemove = false;

		if (searchProveedor(oProveedor)) {
			if (deleteProveedor(oProveedor)) {
				bRemove = true;
			}
		}
		return bRemove;
	}

	@Override
	public boolean deleteProveedor(Proveedor oProveedor) {

		boolean bDelete = false;
		String sTipo = null;

		String sNombre = oProveedor.getsNombreProveedor();

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
	@Override
	public boolean searchProveedor(Proveedor oProveedor) {
		boolean bEncontrado = false;

		String sNombre = oProveedor.getsNombreProveedor();

		String sql = "SELECT COUNT(*) FROM Proveedor WHERE nombre_proveedor = '" + sNombre + "';";

		if (ConexionDB.executeCount(sql) != 0) {
			bEncontrado = true;
		}

		return bEncontrado;

	}

	/***************************************************************************************************************
	 * 1-. MOSTRAR Proveedor
	 ***************************************************************************************************************/
	@Override
	public String mostrarProveedor(Proveedor oProveedor) {
		String sResultado = null;

		String sNombreBD = null;
		int iTelefonoBD = 0;
		String sCorreoBD = null;
		String sDireccionBD = null;
		String sTipoBD = null;

		String sNombre = oProveedor.getsNombreProveedor();

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

	@Override
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
