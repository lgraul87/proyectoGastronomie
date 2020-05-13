package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controlador.database.ConexionDB;
import modelo.genero.Material;
import modelo.proveedor.Proveedor;
import modelo.proveedor.TipoProveedor;

public class MaterialProveedorController implements IMaterialProveedorController {

	@Override
	public boolean determinarMaterial(Material oMaterial) {
		boolean bEncontrado = false;
		String sNombreMaterial = oMaterial.getsNombreMaterial();
		String sql = "SELECT COUNT(*) FROM PROVEEDOR_MATERIAL WHERE NOMBRE_MATERIAL = '" + sNombreMaterial + "';";

		if (ConexionDB.executeCount(sql) != 0) {
			bEncontrado = true;

		}
		return bEncontrado;
	}

	@Override
	public Proveedor obtenerProveedorMaterial(Material oMaterial) {
		Proveedor oProveedor = null;
		String sNombreBD = null;
		String sCorreoBD = null;
		String sTipoBD;
		int iTelefonoBD = 0;
		String sDireccionBD = null;
		TipoProveedor oTipoProveedor = null;
		String sTelefonoParse = null;

		String sNombreMaterial = oMaterial.getsNombreMaterial();
		String sql = "SELECT * FROM PROVEEDOR_MATERIAL WHERE NOMBRE_MATERIAL = '" + sNombreMaterial + "'";

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
	public boolean add(Material oMaterial, Proveedor oProveedor) {

		boolean bAdd = false;
		String sNombreMaterial = oMaterial.getsNombreMaterial();
		String sNombreProveedor = oProveedor.getsNombreProveedor();

		String sql = "SELECT COUNT(*) FROM PROVEEDOR_MATERIAL WHERE NOMBRE_PROVEEDOR = '" + sNombreProveedor
				+ "' AND NOMBRE_MATERIAL = '" + sNombreMaterial + "';";

		if (ConexionDB.executeCount(sql) == 0) {
			String sql2 = "INSERT INTO PROVEEDOR_MATERIAL VALUES ('" + sNombreProveedor + "','" + sNombreMaterial
					+ "')";
			ConexionDB.executeUpdate(sql2);
			bAdd = true;
		}
		return bAdd;

	}

	@Override
	public boolean remove(Material oMaterial, Proveedor oProveedor) {

		boolean bRemove = false;
		String sNombreMaterial = oMaterial.getsNombreMaterial();
		String sNombreProveedor = oProveedor.getsNombreProveedor();

		String sql = "SELECT COUNT(*) FROM PROVEEDOR_MATERIAL WHERE NOMBRE_PROVEEDOR = '" + sNombreProveedor
				+ "' AND NOMBRE_MATERIAL = '" + sNombreMaterial + "';";

		if (ConexionDB.executeCount(sql) != 0) {
			String sql2 = "DELETE FROM PROVEEDOR_MATERIAL WHERE NOMBRE_PROVEEDOR = '" + sNombreProveedor
					+ "' AND NOMBRE_MATERIAL = '" + sNombreMaterial + "';";

			ConexionDB.executeUpdate(sql2);
			bRemove = true;
		}
		return bRemove;
	}

	@Override
	public boolean search(Material oMaterial, Proveedor oProveedor) {

		boolean bSearch = false;

		String sNombreMaterial = oMaterial.getsNombreMaterial();
		String sNombreProveedor = oProveedor.getsNombreProveedor();

		String sql = "SELECT COUNT(*) FROM PROVEEDOR_MATERIAL WHERE NOMBRE_PROVEEDOR = '" + sNombreProveedor
				+ "' AND NOMBRE_MATERIAL = '" + sNombreMaterial + "';";

		if (ConexionDB.executeCount(sql) != 0) {
			bSearch = true;
		}
		return bSearch;
	}

	@Override
	public String mostrarMaterialProveedores() {

		String sProveedor = "No hay Listas";
		String sNombre = null;

		String sql2 = "SELECT COUNT(*) FROM PROVEEDOR_MATERIAL ;";

		if (ConexionDB.executeCount(sql2) != 0) {
			sProveedor = "";

		}

		String sql = "SELECT DISTINCT NOMBRE_PROVEEDOR FROM PROVEEDOR_MATERIAL";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				String sNombreProveedorBD = resultSet.getString("nombre_proveedor");

				String sql3 = "SELECT * FROM PROVEEDOR_MATERIAL WHERE NOMBRE_PROVEEDOR = '" + sNombreProveedorBD + "';";

				try {
					Statement statement2 = ConexionDB.getConnection().createStatement();
					ResultSet resulSet2 = statement.executeQuery(sql3);

					while (resulSet2.next()) {
						if (sNombre != sNombreProveedorBD) {
							sNombre = sNombreProveedorBD;
							sProveedor += "\nProveedor: " + sNombre
									+ "\n---------------------------------------------\n";
						}

						String sMaterialDB = resulSet2.getString("nombre_material");
						sProveedor += "  --Material: " + sMaterialDB + "\n";
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
	public String mostrarMaterialProveedor(Proveedor oProveedor) {

		String sProveedor = "No hay lista de productos con ese proveedor";

		String sNombreProveedor = oProveedor.getsNombreProveedor();

		String sql2 = "SELECT COUNT(*) FROM PROVEEDOR_MATERIAL WHERE NOMBRE_PROVEEDOR = '" + sNombreProveedor + "';";

		if (ConexionDB.executeCount(sql2) != 0) {
			sProveedor = "  --Proveedor: " + sNombreProveedor
					+ "\n---------------------------------------------------\n";

		}

		String sql = "SELECT * FROM PROVEEDOR_MATERIAL WHERE NOMBRE_PROVEEDOR = '" + sNombreProveedor + "';";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resulSet = statement.executeQuery(sql);
			while (resulSet.next()) {

				String sMaterialBD = resulSet.getString("nombre_material");
				sProveedor += "  --Producto: " + sMaterialBD + "\n";
			}
			resulSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sProveedor;
	}
}