package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import controlador.database.ConexionDB;
import modelo.pago.Pago;

public class PagoController implements IPagoController{

	@Override
	public boolean add(Pago oPago) {
		boolean bAniadido = false;
		int iId = 0;
		int iIdBD = 0;

		Date utilDate = oPago.getdFecha();

		java.sql.Date sqlDate = convert(utilDate);

		String sPago = oPago.getoMetodoPago().getsNombrePago();

		String sql = "SELECT * FROM PAGO";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				iIdBD = 0;
				int iNumeroBD = resultSet.getInt("id_pago");
				if (iIdBD < iNumeroBD) {
					iIdBD = iNumeroBD;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		iId = iIdBD + 1;

		String sql2 = "INSERT INTO PAGO VALUES (" + iId + ",'" + sqlDate + "','" + sPago + "');";

		if (ConexionDB.executeUpdate(sql2) != 0) {
			bAniadido = true;
		}

		return bAniadido;
	}
	
	@Override
	public java.sql.Date convert(java.util.Date uDate) {
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}
	
	@Override
	public String mostrarPago(int iId) {
		String sPago = "Pago no registrado";

		String sql = "SELECT COUNT(*) FROM PAGO WHERE ID_PAGO = " + iId + ";";

		if (ConexionDB.executeCount(sql) != 0) {
			sPago = "Pago registrado. Esta localizado";
		}
		return sPago;
	}

	@Override
	public String mostrarPagos() {
		String sPagos = "No hay pagos";

		String sql = "SELECT COUNT(*) FROM PAGO";

		if (ConexionDB.executeCount(sql) != 0) {
			sPagos = "";
		}

		String sql2 = "SELECT * FROM PAGO";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql2);

			while (resultSet.next()) {

				int iIdBD = resultSet.getInt("id_pago");
				java.sql.Date sFechaBD = resultSet.getDate("fecha");
				String sTipoBD = resultSet.getString("tipo");

				sPagos += "  --Id: " + iIdBD + "  --Fecha: " + sFechaBD + "  --Tipo: " + sTipoBD + "\n";
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sPagos;
	}

	@Override
	public boolean remove(int iNumero) {
		boolean bDelete = false;

		String sql = "DELETE FROM PAGO WHERE ID_PAGO = " + iNumero + ";";

		if (ConexionDB.executeUpdate(sql) != 0) {
			bDelete = true;
		}
		return bDelete;
	}

	@Override
	public boolean search(int iNumero) {
		boolean bSearch = false;
		String sql = "SELECT COUNT(*) FROM PAGO WHERE ID_PAGO = " + iNumero + ";";

		if (ConexionDB.executeCount(sql) != 0) {
			bSearch = true;
		}

		return bSearch;
	}
}
