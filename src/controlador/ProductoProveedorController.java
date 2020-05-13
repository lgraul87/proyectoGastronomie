package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controlador.database.ConexionDB;
import modelo.genero.Producto;
import modelo.proveedor.Proveedor;
import modelo.proveedor.TipoProveedor;

public class ProductoProveedorController implements IProductoProveedorController {

	@Override
	public boolean determinarProducto(String sNombreProducto) {

		boolean bEncontrado = false;
		String sql = "SELECT COUNT(*) FROM PROVEEDOR_PRODUCTO WHERE NOMBRE_PRODUCTO = '" + sNombreProducto + "';";

		if (ConexionDB.executeCount(sql) != 0) {
			bEncontrado = true;

		}
		return bEncontrado;
	}

	@Override
	public Proveedor obtenerProveedorProducto(String sNombreProducto) {
		Proveedor oProveedor = null;
		String sNombreBD = null;
		String sCorreoBD = null;
		String sTipoBD;
		int iTelefonoBD = 0;
		String sDireccionBD = null;
		TipoProveedor oTipoProveedor = null;
		String sTelefonoParse = null;

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

				sTelefonoParse = Integer.toString(iTelefonoBD);

			}
			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (sCorreoBD == null) {
			oProveedor = new Proveedor(sNombreBD, sTelefonoParse, sDireccionBD, oTipoProveedor);
		} else if (sCorreoBD != null) {
			oProveedor = new Proveedor(sNombreBD, sTelefonoParse, sCorreoBD, sDireccionBD, oTipoProveedor);

		}

		return oProveedor;
	}

	@Override
	public boolean add(Producto oProducto, Proveedor oProveedor) {

		boolean bAdd = false;
		String sNombreProducto = oProducto.getsNombreProducto();
		String sNombreProveedor = oProveedor.getsNombreProveedor();

		String sql = "SELECT COUNT(*) FROM PROVEEDOR_PRODUCTO WHERE NOMBRE_PROVEEDOR = '" + sNombreProveedor
				+ "' AND NOMBRE_PRODUCTO = '" + sNombreProducto + "';";

		if (ConexionDB.executeCount(sql) == 0) {
			String sql2 = "INSERT INTO PROVEEDOR_PRODUCTO VALUES ('" + sNombreProveedor + "','" + sNombreProducto
					+ "')";
			ConexionDB.executeUpdate(sql2);
			bAdd = true;
		}
		return bAdd;

	}

	@Override
	public boolean remove(Producto oProducto, Proveedor oProveedor) {

		boolean bRemove = false;
		String sNombreProducto = oProducto.getsNombreProducto();
		String sNombreProveedor = oProveedor.getsNombreProveedor();

		String sql = "SELECT COUNT(*) FROM PROVEEDOR_PRODUCTO WHERE NOMBRE_PROVEEDOR = '" + sNombreProveedor
				+ "' AND NOMBRE_PRODUCTO = '" + sNombreProducto + "';";

		if (ConexionDB.executeCount(sql) != 0) {
			String sql2 = "DELETE FROM PROVEEDOR_PRODUCTO WHERE NOMBRE_PROVEEDOR = '" + sNombreProveedor
					+ "' AND NOMBRE_PRODUCTO = '" + sNombreProducto + "';";

			ConexionDB.executeUpdate(sql2);
			bRemove = true;
		}
		return bRemove;
	}

	@Override
	public boolean search(Producto oProducto, Proveedor oProveedor) {

		boolean bSearch = false;

		String sNombreProducto = oProducto.getsNombreProducto();
		String sNombreProveedor = oProveedor.getsNombreProveedor();

		String sql = "SELECT COUNT(*) FROM PROVEEDOR_PRODUCTO WHERE NOMBRE_PROVEEDOR = '" + sNombreProveedor
				+ "' AND NOMBRE_PRODUCTO = '" + sNombreProducto + "';";

		if (ConexionDB.executeCount(sql) != 0) {
			bSearch = true;
		}
		return bSearch;
	}

	@Override
	public String mostrarProductoProveedores() {

		String sProveedor = "No hay Listas";
		String sNombre = null;

		String sql2 = "SELECT COUNT(*) FROM PROVEEDOR_PRODUCTO ;";

		if (ConexionDB.executeCount(sql2) != 0) {
			sProveedor = "";

		}

		String sql = "SELECT DISTINCT NOMBRE_PROVEEDOR FROM PROVEEDOR_PRODUCTO";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				String sNombreProveedorBD = resultSet.getString("nombre_proveedor");

				String sql3 = "SELECT * FROM PROVEEDOR_PRODUCTO WHERE NOMBRE_PROVEEDOR = '" + sNombreProveedorBD + "';";

				try {
					Statement statement2 = ConexionDB.getConnection().createStatement();
					ResultSet resulSet2 = statement.executeQuery(sql3);

					while (resulSet2.next()) {
						if (sNombre != sNombreProveedorBD) {
							sNombre = sNombreProveedorBD;
							sProveedor += "\nProveedor: " + sNombre
									+ "\n---------------------------------------------\n";
						}

						String sProductoDB = resulSet2.getString("nombre_producto");
						sProveedor += "  --Producto: " + sProductoDB + "\n";
					}
					resulSet2.close();
					statement2.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sProveedor;
	}

	@Override
	public String mostrarProductoProveedor(Proveedor oProveedor) {

		String sProveedor = "No hay lista de productos con ese proveedor";

		String sNombreProveedor = oProveedor.getsNombreProveedor();

		String sql2 = "SELECT COUNT(*) FROM PROVEEDOR_PRODUCTO WHERE NOMBRE_PROVEEDOR = '" + sNombreProveedor + "';";

		if (ConexionDB.executeCount(sql2) != 0) {
			sProveedor = "  --Proveedor: " + sNombreProveedor
					+ "\n---------------------------------------------------\n";

		}

		String sql = "SELECT * FROM PROVEEDOR_PRODUCTO WHERE NOMBRE_PROVEEDOR = '" + sNombreProveedor + "';";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resulSet = statement.executeQuery(sql);
			while (resulSet.next()) {

				String sProductoBD = resulSet.getString("nombre_producto");
				sProveedor += "  --Producto: " + sProductoBD + "\n";
			}
			resulSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sProveedor;
	}
}