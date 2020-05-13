package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import controlador.database.ConexionDB;
import modelo.pedido.LineaPedido;
import modelo.proveedor.Proveedor;

public class LineaPedidoController implements ILineaPedidoController {

	@Override
	public boolean add(LineaPedido oLineaPedido) {

		boolean dAdd = false;
		boolean bAddPago = false;
		boolean bAddPedido = false;
		String sNombreProducto = null;
		String sNombreMaterial = null;
		String sTipoPago = null;
		String sDni = null;
		int idPago = 1;
		java.sql.Date sqlDate = null;
		java.sql.Date sqlDate2 = null;

		String sNombreInstalacion = null;

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

		// #### PARA PAGO #######

		Date utilDate = oLineaPedido.getoPedido().getdFecha();
		Date utilDate2 = utilDate;
		sqlDate2 = convert(utilDate2);
		sqlDate = convert(utilDate);

		sTipoPago = oLineaPedido.getoPedido().getoPago().getoMetodoPago().getsNombrePago();

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
		sNombreInstalacion = oLineaPedido.getoPedido().getoInstalacion().getsNombreInstalacion();

		sDni = oLineaPedido.getoPedido().getoUsuario().getsDni();

		String sql5 = "INSERT INTO PEDIDO VALUES (" + iIdPedido + ",'" + sqlDate + "','" + sDni + "'," + idPago + ",'"
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

			if (ConexionDB.executeUpdate(sql3) != 0 && bAddPago && bAddPedido) {
				dAdd = true;

			}
		}

		return dAdd;
	}

	@Override
	public java.sql.Date convert(java.util.Date uDate) {

		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}

	@Override
	public boolean addSinPago(LineaPedido oLineaPedido) {
		boolean dAdd = false;
		String sNombreProducto = null;
		String sNombreMaterial = null;

		// #### PARA LINEA PEDIDO #######
		int iId = 0;
		int iCantidad = (int) oLineaPedido.getbCantidad();
		String sTipo = oLineaPedido.getsTipo();

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

			String sql3 = "INSERT INTO LINEA_PEDIDO VALUES (" + iId + "," + iCantidad + ",'" + sTipo + "',null,'"
					+ sNombreProducto + "',null,'" + sNombreProveedor + "');";

			if (ConexionDB.executeUpdate(sql3) != 0) {
				dAdd = true;

			}

		} else if (sNombreProducto == null && sNombreMaterial != null) {

			String sql3 = "INSERT INTO LINEA_PEDIDO VALUES (" + iId + "," + iCantidad + ",'" + sTipo + "',null,null,'"
					+ sNombreMaterial + "','" + sNombreProveedor + "');";

			if (ConexionDB.executeUpdate(sql3) != 0) {
				dAdd = true;

			}
		}

		return dAdd;
	}

	@Override
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

	@Override
	public boolean remove(int iNumero) {
		boolean bDelete = false;

		String sql = "DELETE FROM LINEA_PEDIDO WHERE ID_LINEA_PEDIDO = " + iNumero + ";";

		if (ConexionDB.executeUpdate(sql) != 0) {
			bDelete = true;
		}

		return bDelete;
	}

	@Override
	public boolean search(int iNumero) {
		boolean bSearch = false;

		String sql = "SELECT COUNT(*) FROM LINEA_PEDIDO WHERE ID_LINEA_PEDIDO = " + iNumero + ";";

		if (ConexionDB.executeCount(sql) != 0) {
			bSearch = true;
		}
		return bSearch;
	}

	@Override
	public boolean searchPedidoProveedor(Proveedor oProveedor) {
		boolean bSearch = false;

		String sNombreProveedor = oProveedor.getsNombreProveedor();
		String sql = "SELECT COUNT(*) FROM LINEA_PEDIDO WHERE NOMBRE_PROVEEDOR = '" + sNombreProveedor + "';";

		if (ConexionDB.executeCount(sql) != 0) {
			bSearch = true;
		}
		return bSearch;
	}

	@Override
	public String mostrarPedidoProveedor(Proveedor oProveedor) {

		String sPedidos = "No hay lineas de pedidos con ese proveedor";

		String sNombreProveedor = oProveedor.getsNombreProveedor();

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

	@Override
	public String mostrarPedidos() {
		String sPedidos = "No hay pedidos";
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

	@Override
	public int autoId() {
		int id = 1;

		String sql = "SELECT * FROM LINEA_PEDIDO";

		try {
			Statement statement = ConexionDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				int idBD = resultSet.getInt("id_pedido");

				if (idBD > id) {
					id = idBD;
				}
			}

			id = id + 1;
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

}
