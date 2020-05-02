package controlador;

import java.sql.ResultSet;
import java.sql.Statement;

import controlador.database.ConexionDB;
import modelo.genero.Producto;

public class ProductoController {

	public String mostrarCartaBebida() {

		String query = "";
		// HACER LA QUERY
		// *************************************************************************

		String sResultado2 = "\n";

		String sResultado = mostrarResultadoCoincidencias(query);

		sResultado2 = mostrarListaProducto(query, sResultado2);

		return sResultado + sResultado2;
	}

	/*********************************************************************************
	 * REFACTORS
	 ********************************************************************************/
	public String mostrarResultadoCoincidencias(String query) {
		int iResultado = ConexionDB.executeCount(query);
		String sResultado = notificarResultado(iResultado);
		return sResultado;
	}

	public String notificarResultado(int iResultado) {
		String sResultado;
		if (iResultado == 0) {
			sResultado = "Operacion fallida";
		} else {
			sResultado = "Operacion aceptada. Numero de elementos: " + iResultado;
		}
		return sResultado;
	}

	/**
	 * ---------------------------------------------------------------------------------------------------------
	 */

	public String mostrarListaProducto(String query, String sResultado2) {
		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resulSet = statement.executeQuery(query);
			while (resulSet.next()) {
				String sNombreProducto = resulSet.getString("nombre_producto");
				float fPrecio = resulSet.getFloat("precio");
				sResultado2 += sNombreProducto + " " + fPrecio + " euro/s";
			}

			resulSet.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sResultado2;
	}

	public Producto obtenerProducto(String sBebida) {

		String query = "";
		// HACER LA QUERY
		// *************************************************************************
		Producto oProducto = null;

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resulSet = statement.executeQuery(query);
			while (resulSet.next()) {
				String sNombreProducto = resulSet.getString("nombre_producto");
				float fPrecio = resulSet.getFloat("precio");
				int shStock = resulSet.getInt("stock");
				String sTipo = resulSet.getString("tipo");

				oProducto = new Producto(sNombreProducto, fPrecio, (short) shStock, sTipo);
			}
			resulSet.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return oProducto;
	}

}
