package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controlador.database.ConexionDB;
import modelo.proveedor.Proveedor;
import modelo.proveedor.TipoProveedor;

public class ProductoProveedorController {

	public boolean determinarProducto(String sNombreProducto) {

		boolean bEncontrado = false;
		String sql = "SELECT COUNT(*) FROM PROVEEDOR_PRODUCTO WHERE NOMBRE_PRODUCTO = '" + sNombreProducto + "';";

		if (ConexionDB.executeCount(sql) != 0) {
			bEncontrado = true;

		}
		return bEncontrado;
	}

	public Proveedor obtenerProveedorProducto(String sNombreProducto) {
		Proveedor oProveedor = null;
		String sNombreBD = null;
		String sCorreoBD = null;
		String sTipoBD;
		int iTelefonoBD = 0;
		String sDireccionBD = null;
		TipoProveedor oTipoProveedor = null;

		String sql = "SELECT * FROM PROVEEDOR_PRODUCTO WHERE NOMBRE_PRODUCTO = '" + sNombreProducto + "'";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resulset = statement.executeQuery(sql);

			while (resulset.next()) {
				sNombreBD = resulset.getString("nombre_proveedor");
			}
			resulset.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sql2 = "SELECT * FROM PROVEEDOR WHERE NOMBRE_PROVEEDOR = '" + sNombreBD + "';";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql2);

			while (resultSet.next()) {
				sNombreBD = resultSet.getString("nombre_proveedor");
				iTelefonoBD = resultSet.getInt("telefono");
				sCorreoBD = resultSet.getString("correo");
				sDireccionBD = resultSet.getString("direccion");
				sTipoBD = resultSet.getString("nombre_tipo");

				oTipoProveedor = new TipoProveedor(sTipoBD);

			}
			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (sCorreoBD == null) {
			oProveedor = new Proveedor(sNombreBD, iTelefonoBD, sDireccionBD, oTipoProveedor);
		} else if (sCorreoBD != null) {
			oProveedor = new Proveedor(sNombreBD, iTelefonoBD, sCorreoBD, sDireccionBD, oTipoProveedor);

		}

		return oProveedor;
	}

	

}
