package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controlador.database.ConexionDB;
import modelo.genero.Producto;

public class ProductoController {

	public boolean add(Producto oProducto) {

		boolean bAniadido = false;
		String sNombre = oProducto.getsNombreProducto();
		float fPrecio = oProducto.getfPrecio();
		short shStock = oProducto.getShStock();
		String sTipo = oProducto.getsTipo();

		String sql = "INSERT INTO PRODUCTO VALUES ('" + sNombre + "'," + fPrecio + "," + shStock + ",'" + sTipo + "');";

		if (ConexionDB.executeUpdate(sql) > 0) {
			bAniadido = true;
		}
		return bAniadido;

	}

	public boolean remove(String sNombre) {
		boolean bDelete = false;
		String sql = "DELETE FROM PRODUCTO WHERE NOMBRE_PRODUCTO = '" + sNombre + "';";
		if (ConexionDB.executeUpdate(sql) > 0) {
			bDelete = true;
		}
		return bDelete;

	}

	public boolean search(String sNombre) {
		boolean bSearch = false;
		String sql = "SELECT COUNT(*) FROM PRODUCTO WHERE NOMBRE_PRODUCTO = '" + sNombre + "';";

		int iCount = 0;

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				iCount = 0;
				iCount++;
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (iCount != 0) {
			bSearch = true;
		}
		return bSearch;

	}

	public String mostrarProducto(String sNombre) {
		String sProducto = "";

		String sql = "SELECT * FROM PRODUCTO WHERE NOMBRE_PRODUCTO = '" + sNombre + "';";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resulset = statement.executeQuery(sql);

			while (resulset.next()) {

				String sNombreBD = resulset.getString("nombre_producto");
				float fPrecioBD = resulset.getFloat("precio");
				short shStockBD = resulset.getShort("stock");
				String sTipoBD = resulset.getString("tipo");

				sProducto = "  --Nombre: " + sNombreBD + "  --Precio: " + fPrecioBD + "  --Stock: " + shStockBD
						+ "  --Tipo: " + sTipoBD;

			}
			resulset.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sProducto;
	}

	public String mostrarProductos() {

		int iCount = 0;
		String sProducto = "";
		String sql = "SELECT * FROM PRODUCTO";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				iCount = 0;
				iCount++;
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (iCount == 0) {
			sProducto = "No hay productos";
		}
		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resulset = statement.executeQuery(sql);

			while (resulset.next()) {

				String sNombreBD = resulset.getString("nombre_producto");
				float fPrecioBD = resulset.getFloat("precio");
				short shStockBD = resulset.getShort("stock");
				String sTipoBD = resulset.getString("tipo");

				sProducto += "  --Nombre: " + sNombreBD + "  --Precio: " + fPrecioBD + "  --Stock: " + shStockBD
						+ "  --Tipo: " + sTipoBD + "\n";

			}
			resulset.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sProducto;
	}

	public String mostrarCartaBebida() {
		// TODO Auto-generated method stub
		return null;
	}

	public Producto obtenerProducto(String sBebida) {
		// TODO Auto-generated method stub
		return null;
	}
}