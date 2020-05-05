package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controlador.database.ConexionDB;
import modelo.genero.Material;

public class MaterialController {

	public boolean add(Material oMaterial) {

		boolean bAniadido = false;
		String sNombre = oMaterial.getsNombreMaterial();
		float fPrecio = oMaterial.getfPrecio();
		short shStock = oMaterial.getShStock();
		String sTipo = oMaterial.getsTipo();

		String sql = "INSERT INTO MATERIAL VALUES ('" + sNombre + "'," + fPrecio + "," + shStock + ",'" + sTipo + "');";

		if (ConexionDB.executeUpdate(sql) > 0) {
			bAniadido = true;
		}
		return bAniadido;

	}

	public boolean remove(String sNombre) {
		boolean bDelete = false;
		String sql = "DELETE FROM MATERIAL WHERE NOMBRE_MATERIAL = '" + sNombre + "';";
		if (ConexionDB.executeUpdate(sql) > 0) {
			bDelete = true;
		}
		return bDelete;

	}

	public boolean search(String sNombre) {
		boolean bSearch = false;
		String sql = "SELECT COUNT(*) FROM MATERIAL WHERE NOMBRE_MATERIAL = '" + sNombre + "';";

		if (ConexionDB.executeUpdate(sql) > 0) {
			bSearch = true;
		}
		return bSearch;

	}

	public String mostrarMaterial(String sNombre) {
		String sMaterial = "";

		String sql = "SELECT * FROM MATERIAL WHERE NOMBRE_MATERIAL = '" + sNombre + "';";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resulset = statement.executeQuery(sql);

			while (resulset.next()) {

				String sNombreBD = resulset.getString("nombre_material");
				float fPrecioBD = resulset.getFloat("precio");
				short shStockBD = resulset.getShort("stock");
				String sTipoBD = resulset.getString("tipo");

				sMaterial = "  --Nombre: " + sNombreBD + "  --Precio: " + fPrecioBD + "  --Stock: " + shStockBD
						+ "  --Tipo: " + sTipoBD;

			}
			resulset.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sMaterial;
	}

	public String mostrarMateriales() {
		String sMaterial = "";

		String sql = "SELECT * FROM MATERIAL";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resulset = statement.executeQuery(sql);

			while (resulset.next()) {

				String sNombreBD = resulset.getString("nombre_material");
				float fPrecioBD = resulset.getFloat("precio");
				short shStockBD = resulset.getShort("stock");
				String sTipoBD = resulset.getString("tipo");

				sMaterial += "  --Nombre: " + sNombreBD + "  --Precio: " + fPrecioBD + "  --Stock: " + shStockBD
						+ "  --Tipo: " + sTipoBD + "\n";

			}
			resulset.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sMaterial;
	}
}