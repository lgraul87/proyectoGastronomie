package controlador;

import modelo.pedido.LineaPedido;

public class LineaPedidoController implements ILineaPedidoController {
	private int autoId;

	public LineaPedidoController() {
		setAutoId(MIN_ID);
	}

	public int getAutoId() {
		return this.autoId;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	public int asignarId() {
		int id = this.autoId;
		id++;
		this.autoId = id;
		return id;
	}

	/******************************************************************************
	 * UPDATE BDD
	 ******************************************************************************/
	public void upDateLineaPedido(LineaPedido oLineaPedido) {
/*
		int idLineaPedido = oLineaPedido.getiIdLineaPedido();

		byte bCantidad = oLineaPedido.getbCantidad();

		String sTipo = oLineaPedido.getsTipo();
		int idPedido = oLineaPedido.getoPedido().getiIdPedido();

		if (oLineaPedido.getoMaterial() != null) {
			String sNombreGenero = oLineaPedido.getoMaterial().getsNombreMaterial();

		}

		if (oLineaPedido.getoProducto() != null) {
			String sNombreGenero = oLineaPedido.getoProducto().getsNombreProducto();

		}

		String query = "";
		// HACER LA QUERY
		// *************************************************************************
		
		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			 statement.executeUpdate(query);
			 statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
*/
	}
}
