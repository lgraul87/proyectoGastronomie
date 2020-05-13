package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controlador.database.ConexionDB;
import modelo.genero.Material;

public class MaterialController implements IMaterialController{

	@Override
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

	@Override
	public boolean remove(Material oMaterial) {
		boolean bDelete = false;
		
		String sNombre = oMaterial.getsNombreMaterial();
		String sql = "DELETE FROM MATERIAL WHERE NOMBRE_MATERIAL = '" + sNombre + "';";
		if (ConexionDB.executeUpdate(sql) > 0) {
			bDelete = true;
		}
		return bDelete;

	}

	@Override
	public boolean search(Material oMaterial) {
		boolean bSearch = false;
		
		String sNombre = oMaterial.getsNombreMaterial();
		
		String sql = "SELECT COUNT(*) FROM MATERIAL WHERE NOMBRE_MATERIAL = '" + sNombre + "';";

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
	public String mostrarMaterial(Material oMaterial) {
		String sMaterial = "";
		
		String sNombre = oMaterial.getsNombreMaterial();

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

	@Override
	public String mostrarMateriales() {
		int iCount = 0;
		String sMaterial = "";
		String sql = "SELECT * FROM MATERIAL";

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
			sMaterial = "No hay materiales";
		}
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