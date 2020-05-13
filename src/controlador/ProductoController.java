package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controlador.database.ConexionDB;
import controlador.database.GeneralController;
import modelo.genero.Producto;

public class ProductoController implements IProductoController {

	@Override
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

	@Override
	public boolean remove(Producto oProducto) {
		boolean bDelete = false;

		String sNombre = oProducto.getsNombreProducto();

		String sql = "DELETE FROM PRODUCTO WHERE NOMBRE_PRODUCTO = '" + sNombre + "';";
		if (ConexionDB.executeUpdate(sql) > 0) {
			bDelete = true;
		}
		return bDelete;

	}

	@Override
	public boolean search(Producto oProducto) {
		boolean bSearch = false;

		String sNombre = oProducto.getsNombreProducto();

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

	@Override
	public String mostrarProducto(Producto oProducto) {
		String sProducto = "";
		String sNombre = oProducto.getsNombreProducto();
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

	@Override
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

	@Override
	public String mostrarCartaBebida() {
		String sCarta = "No hay bebidas";
		String sCartaBebida = "Carta_bebida";

		String sql2 = "SELECT COUNT(*) FROM PRODUCTO WHERE TIPO = '" + sCartaBebida + "';";

		if (ConexionDB.executeCount(sql2) != 0) {
			sCarta = "�Carta de bebidas!\n--------------------------------------------------\n";
		}

		String sql = "SELECT * FROM PRODUCTO WHERE TIPO = '" + sCartaBebida + "';";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resulSet = statement.executeQuery(sql);

			while (resulSet.next()) {
				String sGeneroBD = resulSet.getString("nombre_producto");
				float fPrecioBD = resulSet.getFloat("precio");

				sCarta += "  --Producto: " + sGeneroBD + "  --Precio: " + fPrecioBD + "\n";

			}
			resulSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sCarta;
	}

	@Override
	public Producto obtenerProducto(Producto oProducto) {

		String sBebida = oProducto.getsNombreProducto();

		String sql = "SELECT * FROM PRODUCTO WHERE NOMBRE_PRODUCTO = '" + sBebida + "'";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				String sNombreBD = resultSet.getString("nombre_producto");
				float fPrecioBD = resultSet.getFloat("precio");
				short shStockBD = resultSet.getShort("stock");
				String sTipoBD = resultSet.getString("tipo");

				oProducto = new Producto(sNombreBD, fPrecioBD, shStockBD, sTipoBD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return oProducto;
	}

	@Override
	public boolean update(Producto oProducto, byte bCantidad) {

		boolean bUpdate = false;
		int iCantidadTotal = oProducto.getShStock() - bCantidad;
		String sNombre = oProducto.getsNombreProducto();

		String sql = "UPDATE PRODUCTO SET STOCK = " + iCantidadTotal + " WHERE NOMBRE_PRODUCTO = '" + sNombre + "' ;";

		if (ConexionDB.executeUpdate(sql) != 0) {
			bUpdate = true;
		}

		return bUpdate;

	}

	@Override
	public String mostrarCartaComida() {
		String sCarta = "No hay comida";
		String sCartaComida = "Carta_comida";

		String sql2 = "SELECT COUNT(*) FROM PRODUCTO WHERE TIPO = '" + sCartaComida + "';";

		if (ConexionDB.executeCount(sql2) != 0) {
			sCarta = "�Carta de tapas!\n--------------------------------------------------\n";
		}

		String sql = "SELECT * FROM PRODUCTO WHERE TIPO = '" + sCartaComida + "';";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resulSet = statement.executeQuery(sql);

			while (resulSet.next()) {
				String sGeneroBD = resulSet.getString("nombre_producto");
				float fPrecioBD = resulSet.getFloat("precio");

				sCarta += "  --Producto: " + sGeneroBD + "  --Precio: " + fPrecioBD + "\n";

			}
			resulSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sCarta;
	}

	@Override
	public int contarExistencias(Producto oProducto, GeneralController controlGeneral) {
		int iExistencias = 0;

		String sNombre = oProducto.getsNombreProducto();

		String sql = "SELECT * FROM PRODUCTO WHERE nombre_producto = '" + sNombre + "';";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				iExistencias = resultSet.getInt("stock");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return iExistencias;
	}
}