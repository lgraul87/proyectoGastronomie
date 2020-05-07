package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controlador.database.ConexionDB;
import modelo.instalacion.Instalacion;

public class InstalacionController implements IInstalacionController {

	@Override
	public boolean add(Instalacion oInstalacion) {

		boolean bAniadio = false;

		String sNombre = oInstalacion.getsNombreInstalacion();
		String sDescripcion = oInstalacion.getsDescripcion();
		String sDireccion = oInstalacion.getsDireccion();
		float fCuota = oInstalacion.getfCuotaMensual();

		String sql = "INSERT INTO INSTALACION VALUES ('" + sNombre + "','" + sDescripcion + "','" + sDireccion + "',"
				+ fCuota + ");";

		if (ConexionDB.executeUpdate(sql) > 0) {
			bAniadio = true;
		}

		return bAniadio;
	}
	@Override
	public boolean remove(String sNombre) {
		boolean bDelete = false;
		String sql = "DELETE FROM INSTALACION WHERE NOMBRE = '" + sNombre + "';";
		if (ConexionDB.executeUpdate(sql) > 0) {
			bDelete = true;
		}

		return bDelete;
	}
	@Override
	public boolean search(String sNombre) {

		boolean bSearch = false;

		String sql = "SELECT COUNT(*) FROM INSTALACION WHERE NOMBRE = '" + sNombre + "';";

		if (ConexionDB.executeCount(sql) > 0) {
			bSearch = true;
		}

		return bSearch;
	}
	@Override
	public String mostrarInstalacion(String sNombre) {

		String sInstalacion = "No hay esa instalacion";

		String sql = "SELECT * FROM INSTALACION WHERE NOMBRE = '" + sNombre + "';";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				String sNombreBD = resultSet.getString("nombre");
				String sDescripcionBD = resultSet.getString("descripcion");
				String sDireccionBD = resultSet.getString("direccion");
				float fPrecioBD = resultSet.getFloat("cuota_mensual");

				if (sDescripcionBD != null) {
					sInstalacion = "  --Nombre: " + sNombreBD + "  --Descripcion: " + sDescripcionBD + "  --Direccion: "
							+ sDireccionBD + "  --Cuota mensual: " + fPrecioBD + "\n";

				} else {
					sInstalacion = "  --Nombre: " + sNombreBD + "  --Direccion: " + sDireccionBD + "  --Cuota mensual: "
							+ fPrecioBD + "\n";
				}

			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sInstalacion;
	}
	@Override
	public String mostrarInstalaciones() {
		String sInstalacion = "No hay instalaciones";

		String sql = "SELECT COUNT(*) FROM INSTALACION ;";
		if (ConexionDB.executeCount(sql) > 0) {
			sInstalacion = "";
		}

		String sql2 = "SELECT * FROM INSTALACION ;";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql2);

			while (resultSet.next()) {
				String sNombreBD = resultSet.getString("nombre");
				String sDescripcionBD = resultSet.getString("descripcion");
				String sDireccionBD = resultSet.getString("direccion");
				float fPrecioBD = resultSet.getFloat("cuota_mensual");

				if (sDescripcionBD != null) {
					sInstalacion += "  --Nombre: " + sNombreBD + "  --Descripcion: " + sDescripcionBD
							+ "  --Direccion: " + sDireccionBD + "  --Cuota mensual: " + fPrecioBD + "\n";

				} else {
					sInstalacion += "  --Nombre: " + sNombreBD + "  --Direccion: " + sDireccionBD
							+ "  --Cuota mensual: " + fPrecioBD + "\n";
				}

			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sInstalacion;
	}
	@Override
	public Instalacion determinarInstalacion(String sNombreLocal) {

		Instalacion oInstalacion = null;
		
		String sql2 = "SELECT COUNT(*) FROM INSTALACION WHERE NOMBRE = '" + sNombreLocal + "';";
		
		if (ConexionDB.executeCount(sql2) != 0) {
			
		

		String sql = "SELECT * FROM INSTALACION WHERE NOMBRE = '" + sNombreLocal + "';";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {

				String sNombreBD = resultSet.getString("nombre");
				String sDescripcionBD = resultSet.getString("descripcion");
				String sDireccionBD = resultSet.getString("direccion");
				float fCuota = resultSet.getFloat("cuota_mensual");
				
				if (sDescripcionBD == null) {
					oInstalacion = new Instalacion(sNombreBD, sDireccionBD, fCuota);
				}else if (sDescripcionBD != null){
					oInstalacion = new Instalacion(sNombreBD, sDireccionBD, sDireccionBD, fCuota);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			}
		}

		return oInstalacion;
	}
}
