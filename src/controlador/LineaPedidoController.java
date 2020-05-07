package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import controlador.database.ConexionDB;
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
		 * int idLineaPedido = oLineaPedido.getiIdLineaPedido();
		 * 
		 * byte bCantidad = oLineaPedido.getbCantidad();
		 * 
		 * String sTipo = oLineaPedido.getsTipo(); int idPedido =
		 * oLineaPedido.getoPedido().getiIdPedido();
		 * 
		 * if (oLineaPedido.getoMaterial() != null) { String sNombreGenero =
		 * oLineaPedido.getoMaterial().getsNombreMaterial();
		 * 
		 * }
		 * 
		 * if (oLineaPedido.getoProducto() != null) { String sNombreGenero =
		 * oLineaPedido.getoProducto().getsNombreProducto();
		 * 
		 * }
		 * 
		 * String query = ""; // HACER LA QUERY //
		 * *************************************************************************
		 * 
		 * try { Statement statement = ConexionDB.getConnection().createStatement();
		 * statement.executeUpdate(query); statement.close();
		 * 
		 * } catch (SQLException e) { e.printStackTrace(); }
		 */
	}

	public boolean add(LineaPedido oLineaPedido) {

		boolean dAdd = false;
		boolean bAddPago = false;
		boolean bAddPedido = false;
		String sNombreProducto = null;
		String sNombreMaterial = null;

		// #### PARA LINEA PEDIDO #######
		int iId = 0;
		int iCantidad = (int) oLineaPedido.getbCantidad();
		String sTipo = oLineaPedido.getsTipo();
		int iIdPedido = 0;

		try {
			sNombreProducto = oLineaPedido.getoProducto().getsNombreProducto();

		} catch (Exception e) {
			sNombreProducto = null;
		}

		try {
			sNombreMaterial = oLineaPedido.getoMaterial().getsNombreMaterial();

		} catch (Exception e) {
			sNombreMaterial = null;
		}

		String sNombreProveedor = oLineaPedido.getoProveedor().getsNombreProveedor();

		// #### PARA PEDIDO #######
		Date utilDate = oLineaPedido.getoPedido().getdFecha();

		java.sql.Date sqlDate = convert(utilDate);

		String sDni = oLineaPedido.getoPedido().getoUsuario().getsDni();

		int idPago = 0;

		String sNombreInstalacion = oLineaPedido.getoPedido().getoInstalacion().getsNombreInstalacion();

		// #### PARA PAGO #######

		Date utilDate2 = oLineaPedido.getoPedido().getoPago().getdFecha();

		java.sql.Date sqlDate2 = convert(utilDate2);

		String sTipoPago = oLineaPedido.getoPedido().getoPago().getoMetodoPago().getsNombrePago();

		String sql4 = "SELECT * FROM PAGO";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql4);
			while (resultSet.next()) {

				int iNumeroBD = resultSet.getInt("id_pago");
				if (idPago < iNumeroBD) {
					idPago = iNumeroBD;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		idPago = idPago + 1;

		String sql6 = "INSERT INTO PAGO VALUES (" + idPago + ",'" + sqlDate2 + "','" + sTipoPago + "');";

		if (ConexionDB.executeUpdate(sql6) != 0) {
			bAddPago = true;
		}

		String sql2 = "SELECT * FROM PEDIDO";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql2);
			while (resultSet.next()) {

				int iNumeroBD = resultSet.getInt("id_pedido");
				if (iIdPedido < iNumeroBD) {
					iIdPedido = iNumeroBD;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		iIdPedido = iIdPedido + 1;

		String sql5 ="";
		
		if (sDni != null) {
			 sql5 = "INSERT INTO PEDIDO VALUES (" + iIdPedido + ",'" + sqlDate + "','" + sDni + "'," + idPago
					+ ",'" + sNombreInstalacion + "');";
		} else if (sDni == null) {
			 sql5 = "INSERT INTO PEDIDO VALUES (" + iIdPedido + ",'" + sqlDate + "',null," + idPago + ",'"
					+ sNombreInstalacion + "');";
		}

		if (ConexionDB.executeUpdate(sql5) != 0) {
			bAddPedido = true;
		}

		String sql = "SELECT * FROM LINEA_PEDIDO";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {

				int iNumeroBD = resultSet.getInt("id_linea_pedido");
				if (iId < iNumeroBD) {
					iId = iNumeroBD;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		iId = iId + 1;

		if (sNombreProducto != null && sNombreMaterial == null) {

			String sql3 = "INSERT INTO LINEA_PEDIDO VALUES (" + iId + "," + iCantidad + ",'" + sTipo + "'," + iIdPedido
					+ ",'" + sNombreProducto + "',null,'" + sNombreProveedor + "');";

			if (ConexionDB.executeUpdate(sql3) != 0 && bAddPago && bAddPedido) {
				dAdd = true;

			}

		} else if (sNombreProducto == null && sNombreMaterial != null) {

			String sql3 = "INSERT INTO LINEA_PEDIDO VALUES (" + iId + "," + iCantidad + ",'" + sTipo + "'," + iIdPedido
					+ ",null,'" + sNombreMaterial + "','" + sNombreProveedor + "');";

			if (ConexionDB.executeUpdate(sql3) != 0 && bAddPago && bAddPedido) {
				dAdd = true;

			}
		}

		return dAdd;
	}

	private java.sql.Date convert(java.util.Date uDate) {
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}

	public boolean addSinPago(LineaPedido oLineaPedido) {
		boolean dAdd = false;
		boolean bAddPedido = false;
		String sNombreProducto = null;
		String sNombreMaterial = null;

		// #### PARA LINEA PEDIDO #######
		int iId = 0;
		int iCantidad = (int) oLineaPedido.getbCantidad();
		String sTipo = oLineaPedido.getsTipo();
		int iIdPedido = 0;

		try {
			sNombreProducto = oLineaPedido.getoProducto().getsNombreProducto();

		} catch (Exception e) {
			sNombreProducto = null;
		}

		try {
			sNombreMaterial = oLineaPedido.getoMaterial().getsNombreMaterial();

		} catch (Exception e) {
			sNombreMaterial = null;
		}

		String sNombreProveedor = oLineaPedido.getoProveedor().getsNombreProveedor();

		// #### PARA PEDIDO #######
		Date utilDate = oLineaPedido.getoPedido().getdFecha();

		java.sql.Date sqlDate = convert(utilDate);

		String sDni = oLineaPedido.getoPedido().getoUsuario().getsDni();

		String sNombreInstalacion = oLineaPedido.getoPedido().getoInstalacion().getsNombreInstalacion();

		String sql2 = "SELECT * FROM PEDIDO";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql2);
			while (resultSet.next()) {

				int iNumeroBD = resultSet.getInt("id_pedido");
				if (iIdPedido < iNumeroBD) {
					iIdPedido = iNumeroBD;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		iIdPedido = iIdPedido + 1;

		String sql5 = "INSERT INTO PEDIDO VALUES (" + iIdPedido + ",'" + sqlDate + "','" + sDni + "',null,'"
				+ sNombreInstalacion + "');";

		if (ConexionDB.executeUpdate(sql5) != 0) {
			bAddPedido = true;
		}

		String sql = "SELECT * FROM LINEA_PEDIDO";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {

				int iNumeroBD = resultSet.getInt("id_linea_pedido");
				if (iId < iNumeroBD) {
					iId = iNumeroBD;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		iId = iId + 1;

		if (sNombreProducto != null && sNombreMaterial == null) {

			String sql3 = "INSERT INTO LINEA_PEDIDO VALUES (" + iId + "," + iCantidad + ",'" + sTipo + "'," + iIdPedido
					+ ",'" + sNombreProducto + "',null,'" + sNombreProveedor + "');";

			if (ConexionDB.executeUpdate(sql3) != 0 && bAddPedido) {
				dAdd = true;

			}

		} else if (sNombreProducto == null && sNombreMaterial != null) {

			String sql3 = "INSERT INTO LINEA_PEDIDO VALUES (" + iId + "," + iCantidad + ",'" + sTipo + "'," + iIdPedido
					+ ",null,'" + sNombreMaterial + "','" + sNombreProveedor + "');";

			if (ConexionDB.executeUpdate(sql3) != 0 && bAddPedido) {
				dAdd = true;

			}
		}

		return dAdd;
	}

	public String mostrarPedidosPorId() {

		String sPedidos = "No hay lineas de pedidos";

		String sql2 = "SELECT COUNT(*) FROM LINEA_PEDIDO";

		if (ConexionDB.executeCount(sql2) != 0) {
			sPedidos = "";
		}

		String sql = "SELECT * FROM LINEA_PEDIDO";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resulSet = statement.executeQuery(sql);

			while (resulSet.next()) {
				int iIdBD = resulSet.getInt("id_linea_pedido");
				int iCantidadBD = resulSet.getInt("cantidad");
				String sTipoBD = resulSet.getString("sTipo");
				String sNombreProductoBD = resulSet.getString("nombre_producto");
				String sNombreMaterialBD = resulSet.getString("nombre_material");
				String sNombreProveedorBD = resulSet.getString("nombre_proveedor");

				if (sNombreProductoBD != null && sNombreMaterialBD == null) {
					sPedidos += "  --ID:" + iIdBD + "  --Producto: " + sNombreProductoBD + "  --Cantidad: "
							+ iCantidadBD + "  --Tipo: " + sTipoBD + "  --Proveedor: " + sNombreProveedorBD + "\n";

				} else if (sNombreProductoBD == null && sNombreMaterialBD != null) {
					sPedidos += "  --ID:" + iIdBD + "  --Material: " + sNombreMaterialBD + "  --Cantidad: "
							+ iCantidadBD + "  --Tipo: " + sTipoBD + "  --Proveedor: " + sNombreProveedorBD + "\n";

				}
			}
			resulSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sPedidos;
	}

	public boolean remove(int iNumero) {
		boolean bDelete = false;

		String sql = "DELETE FROM LINEA_PEDIDO WHERE ID_LINEA_PEDIDO = " + iNumero + ";";

		if (ConexionDB.executeUpdate(sql) != 0) {
			bDelete = true;
		}

		return bDelete;
	}

	public boolean search(int iNumero) {
		boolean bSearch = false;

		String sql = "SELECT COUNT(*) FROM LINEA_PEDIDO WHERE ID_LINEA_PEDIDO = " + iNumero + ";";

		if (ConexionDB.executeCount(sql) != 0) {
			bSearch = true;
		}
		return bSearch;
	}

	public boolean searchPedidoProveedor(String sNombreProveedor) {
		boolean bSearch = false;

		String sql = "SELECT COUNT(*) FROM LINEA_PEDIDO WHERE NOMBRE_PROVEEDOR = '" + sNombreProveedor + "';";

		if (ConexionDB.executeCount(sql) != 0) {
			bSearch = true;
		}
		return bSearch;
	}

	public String mostrarPedidoProveedor(String sNombreProveedor) {

		String sPedidos = "No hay lineas de pedidos con ese proveedor";

		String sql2 = "SELECT COUNT(*) FROM LINEA_PEDIDO WHERE NOMBRE_PROVEEDOR = '" + sNombreProveedor + "';";

		if (ConexionDB.executeCount(sql2) != 0) {
			sPedidos = "  --Proveedor: " + sNombreProveedor + "\n---------------------------------------------------\n";

		}

		String sql = "SELECT * FROM LINEA_PEDIDO WHERE NOMBRE_PROVEEDOR = '" + sNombreProveedor + "';";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resulSet = statement.executeQuery(sql);
			while (resulSet.next()) {
				int iCantidadBD = resulSet.getInt("cantidad");
				String sTipoBD = resulSet.getString("sTipo");
				String sNombreProductoBD = resulSet.getString("nombre_producto");
				String sNombreMaterialBD = resulSet.getString("nombre_material");

				if (sNombreProductoBD != null && sNombreMaterialBD == null) {
					sPedidos += "  --Producto: " + sNombreProductoBD + "  --Cantidad: " + iCantidadBD + "  --Tipo: "
							+ sTipoBD + "\n";

				} else if (sNombreProductoBD == null && sNombreMaterialBD != null) {
					sPedidos += "  --Material: " + sNombreMaterialBD + "  --Cantidad: " + iCantidadBD + "  --Tipo: "
							+ sTipoBD + "\n";

				}
			}
			resulSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sPedidos;
	}

	public String mostrarPedidos() {
		String sPedidos = "No hay pedidos ";
		String sNombre = null;

		String sql2 = "SELECT COUNT(*) FROM LINEA_PEDIDO ;";

		if (ConexionDB.executeCount(sql2) != 0) {
			sPedidos = "";

		}

		String sql = "SELECT DISTINCT NOMBRE_PROVEEDOR FROM LINEA_PEDIDO ;";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resulSet = statement.executeQuery(sql);

			while (resulSet.next()) {

				String sNombreProveedorBD = resulSet.getString("nombre_proveedor");

				String sql3 = "SELECT * FROM LINEA_PEDIDO WHERE NOMBRE_PROVEEDOR = '" + sNombreProveedorBD + "';";

				try {
					Statement statement2 = ConexionDB.getConnection().createStatement();
					ResultSet resulSet2 = statement.executeQuery(sql3);

					while (resulSet2.next()) {

						if (sNombre != sNombreProveedorBD) {
							sNombre = sNombreProveedorBD;
							sPedidos += "\nProveedor: " + sNombre + "\n---------------------------------------------\n";
						}

						int iCantidadBD = resulSet2.getInt("cantidad");
						String sTipoBD = resulSet2.getString("sTipo");
						String sNombreProductoBD = resulSet2.getString("nombre_producto");
						String sNombreMaterialBD = resulSet2.getString("nombre_material");

						if (sNombreProductoBD != null && sNombreMaterialBD == null) {
							sPedidos += "  --Producto: " + sNombreProductoBD + "  --Cantidad: " + iCantidadBD
									+ "  --Tipo: " + sTipoBD + "\n";

						} else if (sNombreProductoBD == null && sNombreMaterialBD != null) {
							sPedidos += "  --Material: " + sNombreMaterialBD + "  --Cantidad: " + iCantidadBD
									+ "  --Tipo: " + sTipoBD + "\n";

						}
					}
					resulSet2.close();
					statement2.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			resulSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sPedidos;
	}
}
