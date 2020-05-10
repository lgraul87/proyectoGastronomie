package vista.pedido;

import java.util.Date;

import controlador.database.GeneralController;
import modelo.genero.Material;
import modelo.genero.Producto;
import modelo.instalacion.Instalacion;
import modelo.pago.MetodoPago;
import modelo.pago.Pago;
import modelo.pedido.LineaPedido;
import modelo.pedido.Pedido;
import modelo.proveedor.Proveedor;
import modelo.proveedor.TipoProveedor;
import modelo.usuario.TipoUsuario;
import modelo.usuario.Usuario;
import validaciones.L;

public class PedidoView {

	/****************************************************************************************************
	 * PARA CLIENTES
	 ****************************************************************************************************/
	public static void tomarNotaLocal(GeneralController controlGeneral) {
		String sResultado = "Error, pedido no realizado";

		byte bOptionPedir = 0;
		boolean errorControl;

		errorControl = true;

		do {
			try {
				bOptionPedir = (byte) L.valida("Elige opcion:" + "\nBebidas: (1)" + "\nTapas: (2)", 1, 2, 3);
				errorControl = false;
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (errorControl);

		if (bOptionPedir == 1) {

			byte bCantidad = 0;
			byte bOption = 0;
			String sPago = null;
			int iId = 1;

			System.out.println(controlGeneral.getProductoController().mostrarCartaBebida());

			String sBebida = L.leer("¡Que te pongo!\n--------------------------\nNombre: (Bebida)");

			errorControl = true;
			do {
				try {
					bCantidad = (byte) L.valida("Cantidad: ", 1, 100, 3);
					errorControl = false;
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			errorControl = true;

			do {
				try {
					bOption = (byte) L.valida("Elige metodo de pago: " + "\nEfectivo: (1)" + "\nTarjeta: (2)", 1, 2, 3);
					errorControl = false;
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			if (bOption == 1) {
				sPago = "efectivo";
			}
			if (bOption == 2) {
				sPago = "tarjeta";
			}
			MetodoPago oMetodoPago = new MetodoPago(sPago);

			Pago oPago = new Pago(iId, new Date(), oMetodoPago);

			String sNombreLocal = "Local1";

			TipoUsuario oTipoUsuario = new TipoUsuario("Cliente");
			Instalacion oInstalacion = new Instalacion(sNombreLocal, "Central", 1000);
			Usuario oUsuario = new Usuario("Cliente", "Cliente", "Cliente", oTipoUsuario);

			Producto oProducto = controlGeneral.getProductoController().obtenerProducto(sBebida);

			if (oProducto != null) {

				float fPrecioTotal = oProducto.getfPrecio() * bCantidad;

				if (controlGeneral.getProductoController().update(oProducto, bCantidad)) {

					TipoProveedor oTipoProveedor = new TipoProveedor("Gastronomie");
					Proveedor oProveedor = new Proveedor("Gastronomie", 654554321, "Gastronomie@gmail.com",
							oTipoProveedor);
					Pedido oPedido = new Pedido(iId, new Date(), oUsuario, oPago, oInstalacion);
					LineaPedido oLineaPedido = new LineaPedido(iId, oPedido, oProducto, bCantidad, "Venta", oProveedor);
					if (controlGeneral.getLineaPedidoController().add(oLineaPedido)) {
						sResultado = "Aniadido correctamente: " + fPrecioTotal + " euros por favor.";

						int iNumero = contarExistencias(oProducto, controlGeneral);
						if (iNumero < 10) {

							new TipoUsuario("Admin");

							new Usuario("Raul", "49789078F", "Lora", 873299056, "lg@gmail.com", oTipoUsuario);

							oPedido = new Pedido(iId, new Date(), oUsuario, oInstalacion);

							byte bPedir = 20;

							LineaPedido oLineaPedidoRecarga = new LineaPedido(iId, oPedido, oProducto, bPedir, "Compra",
									oProveedor);
							if (controlGeneral.getLineaPedidoController().add(oLineaPedidoRecarga)) {

								System.out.println("Recargando");
							}
						}

					}
				}

			}

		} else if (bOptionPedir == 2) {

			byte bCantidad = 0;
			byte bOption = 0;
			String sPago = null;
			int iId = 1;

			System.out.println(controlGeneral.getProductoController().mostrarCartaComida());

			String sTapas = L.leer("¡Que te pongo!\n--------------------------\nNombre: (Tapa)");

			errorControl = true;
			do {
				try {
					bCantidad = (byte) L.valida("Cantidad: ", 1, 100, 3);
					errorControl = false;
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			errorControl = true;

			do {
				try {
					bOption = (byte) L.valida("Elige metodo de pago: " + "\nEfectivo: (1)" + "\nTarjeta: (2)", 1, 2, 3);
					errorControl = false;
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			if (bOption == 1) {
				sPago = "efectivo";
			}
			if (bOption == 2) {
				sPago = "tarjeta";
			}
			MetodoPago oMetodoPago = new MetodoPago(sPago);

			Pago oPago = new Pago(iId, new Date(), oMetodoPago);

			String sNombreLocal = "Local1";

			TipoUsuario oTipoUsuario = new TipoUsuario("Cliente");
			Instalacion oInstalacion = new Instalacion(sNombreLocal, "Central", 1000);
			Usuario oUsuario = new Usuario("Cliente", "Cliente", "Cliente", oTipoUsuario);

			Producto oProducto = controlGeneral.getProductoController().obtenerProducto(sTapas);

			if (oProducto != null) {

				float fPrecioTotal = oProducto.getfPrecio() * bCantidad;

				if (controlGeneral.getProductoController().update(oProducto, bCantidad)) {

					TipoProveedor oTipoProveedor = new TipoProveedor("Gastronomie");
					Proveedor oProveedor = new Proveedor("Gastronomie", 654554321, "Gastronomie@gmail.com",
							oTipoProveedor);
					Pedido oPedido = new Pedido(iId, new Date(), oUsuario, oPago, oInstalacion);
					
					
				LineaPedido oLineaPedido = new LineaPedido(iId, oPedido, oProducto, bCantidad, "Venta", oProveedor);
					if (controlGeneral.getLineaPedidoController().add(oLineaPedido)) {
						sResultado = "Aniadido correctamente: " + fPrecioTotal + " euros por favor.";
						int iNumero = contarExistencias(oProducto, controlGeneral);
						if (iNumero < 10) {

							oTipoUsuario = new TipoUsuario("Admin");

							oUsuario = new Usuario("Raul", "49789078F", "Lora", 873299056, "lg@gmail.com", oTipoUsuario);

							oPedido = new Pedido(iId, new Date(), oUsuario, oInstalacion);

							byte bPedir = 20;

							LineaPedido oLineaPedidoRecarga = new LineaPedido(iId, oPedido, oProducto, bPedir, "Compra",
									oProveedor);
							if (controlGeneral.getLineaPedidoController().add(oLineaPedidoRecarga)) {

								System.out.println("Recargando");
							}
						}

					}
				}

			}
		}
		System.out.println(sResultado);
	}

	public static int contarExistencias(Producto oProducto, GeneralController controlGeneral) {
		int iExistencias = 0;
		iExistencias = controlGeneral.getProductoController().contarExistencias(oProducto, controlGeneral);

		return iExistencias;
	}

	public static void tomarNotaTakeAway(GeneralController controlGeneral) {
		String sResultado = "Error, pedido no realizado";

		byte bOptionPedir = 0;
		boolean errorControl;

		errorControl = true;

		do {
			try {
				bOptionPedir = (byte) L.valida("Elige opcion:" + "\nBebidas: (1)" + "\nTapas: (2)", 1, 2, 3);
				errorControl = false;
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (errorControl);

		if (bOptionPedir == 1) {

			byte bCantidad = 0;
			byte bOption = 0;
			String sPago = null;
			int iId = 1;

			System.out.println(controlGeneral.getProductoController().mostrarCartaBebida());

			String sBebida = L.leer("¡Que te pongo!\n--------------------------\nNombre: (Bebida)");

			errorControl = true;
			do {
				try {
					bCantidad = (byte) L.valida("Cantidad: ", 1, 100, 3);
					errorControl = false;
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			errorControl = true;

			do {
				try {
					bOption = (byte) L.valida("Elige metodo de pago: " + "\nEfectivo: (1)" + "\nTarjeta: (2)", 1, 2, 3);
					errorControl = false;
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			if (bOption == 1) {
				sPago = "efectivo";
			}
			if (bOption == 2) {
				sPago = "tarjeta";
			}
			MetodoPago oMetodoPago = new MetodoPago(sPago);

			Pago oPago = new Pago(iId, new Date(), oMetodoPago);

			String sNombreLocal = "Local1";

			TipoUsuario oTipoUsuario = new TipoUsuario("Cliente");
			Instalacion oInstalacion = new Instalacion(sNombreLocal, "Central", 1000);
			Usuario oUsuario = new Usuario("Cliente", "Cliente", "Cliente", oTipoUsuario);

			Producto oProducto = controlGeneral.getProductoController().obtenerProducto(sBebida);

			if (oProducto != null) {

				float fPrecioTotal = oProducto.getfPrecio() * bCantidad;

				if (controlGeneral.getProductoController().update(oProducto, bCantidad)) {

					TipoProveedor oTipoProveedor = new TipoProveedor("Gastronomie");
					Proveedor oProveedor = new Proveedor("Gastronomie", 654554321, "Gastronomie@gmail.com",
							oTipoProveedor);
					Pedido oPedido = new Pedido(iId, new Date(), oUsuario, oPago, oInstalacion);
					LineaPedido oLineaPedido = new LineaPedido(iId, oPedido, oProducto, bCantidad, "Venta", oProveedor);
					if (controlGeneral.getLineaPedidoController().add(oLineaPedido)) {
						sResultado = "Aniadido correctamente: " + fPrecioTotal + " euros por favor.";
					}
				}

			}

		} else if (bOptionPedir == 2) {

			byte bCantidad = 0;
			byte bOption = 0;
			String sPago = null;
			int iId = 1;

			System.out.println(controlGeneral.getProductoController().mostrarCartaComida());

			String sTapas = L.leer("¡Que te pongo!\n--------------------------\nNombre: (Tapa)");

			errorControl = true;
			do {
				try {
					bCantidad = (byte) L.valida("Cantidad: ", 1, 100, 3);
					errorControl = false;
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			errorControl = true;

			do {
				try {
					bOption = (byte) L.valida("Elige metodo de pago: " + "\nEfectivo: (1)" + "\nTarjeta: (2)", 1, 2, 3);
					errorControl = false;
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			if (bOption == 1) {
				sPago = "efectivo";
			}
			if (bOption == 2) {
				sPago = "tarjeta";
			}
			MetodoPago oMetodoPago = new MetodoPago(sPago);

			Pago oPago = new Pago(iId, new Date(), oMetodoPago);

			String sNombreLocal = "Local1";

			TipoUsuario oTipoUsuario = new TipoUsuario("Cliente");
			Instalacion oInstalacion = new Instalacion(sNombreLocal, "Central", 1000);
			Usuario oUsuario = new Usuario("Cliente", "Cliente", "Cliente", oTipoUsuario);

			Producto oProducto = controlGeneral.getProductoController().obtenerProducto(sTapas);

			if (oProducto != null) {

				float fPrecioTotal = oProducto.getfPrecio() * bCantidad;

				if (controlGeneral.getProductoController().update(oProducto, bCantidad)) {

					TipoProveedor oTipoProveedor = new TipoProveedor("Gastronomie");
					Proveedor oProveedor = new Proveedor("Gastronomie", 654554321, "Gastronomie@gmail.com",
							oTipoProveedor);
					Pedido oPedido = new Pedido(iId, new Date(), oUsuario, oPago, oInstalacion);
					LineaPedido oLineaPedido = new LineaPedido(iId, oPedido, oProducto, bCantidad, "Venta", oProveedor);
					if (controlGeneral.getLineaPedidoController().add(oLineaPedido)) {
						sResultado = "Aniadido correctamente: " + fPrecioTotal + " euros por favor.";
					}
				}

			}

		}
		System.out.println(sResultado);
	}

	/****************************************************************************************************
	 * ADMIN
	 ****************************************************************************************************/
	public static void operacionesPedido(GeneralController controlGeneral) {
		byte bOption;
		do {
			bOption = (byte) L.valida(""
					//
					+ "*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *"
					//
					+ "\n	ADMINISTRACION"
					//
					+ "\n*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *"
					//
					+ "\n"
					//
					+ "\n  --Aniadir Pedido (Compras): 		(1)"
					//
					+ "\n  --Borrar Pedido (Compras):     	(2)"
					//
					+ "\n  --Buscar Pedido (Compras): 		(3)"
					//
					+ "\n  --Mostrar Pedido (Compras): 		(4)"
					//
					+ "\n  --Salir: 				(5)", 1, 5, 3);
			//

			if (bOption == 1) {
				aniadirPedido(controlGeneral);
			} else if (bOption == 2) {
				borrarPedido(controlGeneral);
			} else if (bOption == 3) {
				buscarPedido(controlGeneral);
			} else if (bOption == 4) {
				mostrarPedido(controlGeneral);
			}
		} while (bOption != 5);
	}

	/***********************************************************************************************
	 * ANIADIR LINEA DE PEDIDO
	 ***********************************************************************************************/
	public static void aniadirPedido(GeneralController controlGeneral) {

		String sResultado = "No se pudo aniadir.";
		byte bOptionPago = 0;
		int iId = 1;
		String sDni;
		String sPago = null;
		byte bOption = 0;
		byte bOptionGenero = 0;
		byte bCantidad = 0;
		Producto oProducto;
		float fPrecio = 0;
		Proveedor oProveedor = null;
		sDni = L.leer("Dni: (Usuario)");

		Usuario oUsuario = determinarUsuarioPedido(sDni, controlGeneral);

		if (oUsuario != null) {

			boolean errorControl = true;
			do {
				try {
					bOptionGenero = (byte) L.valida("Es un: " + "\nProducto: (1)" + "\nMaterial: (2)", 1, 2, 3);
					errorControl = false;
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			// ###### PRODUCTO ################################################
			if (bOptionGenero == 1) {

				String sNombreProducto = L.leer("Nombre: (Producto)");

				if (determinarProducto(sNombreProducto, controlGeneral)) {

					oProveedor = obtenerProveedorProducto(sNombreProducto, controlGeneral);

					errorControl = true;
					do {
						try {
							fPrecio = (float) L.valida("Precio: (Producto)", 0.1F, 3000F, 2);
							errorControl = false;
						} catch (NumberFormatException e) {
							System.out.println(e.getMessage());
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					} while (errorControl);

					errorControl = true;
					do {
						try {
							bCantidad = (byte) L.valida("Cantidad (Unidades)", 1, 100, 3);
							errorControl = false;
						} catch (NumberFormatException e) {
							System.out.println(e.getMessage());
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					} while (errorControl);

					oProducto = new Producto(sNombreProducto, fPrecio, (short) bCantidad, "Derivados");

					errorControl = true;
					do {
						try {
							bOptionPago = (byte) L.valida("¿Pago incluido?: " + "\nSi: (1)" + "\nNo: (2)", 1, 2, 3);
							errorControl = false;
						} catch (NumberFormatException e) {
							System.out.println(e.getMessage());
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					} while (errorControl);

					if (bOptionPago == 1) {

						errorControl = true;
						do {
							try {
								bOption = (byte) L.valida(
										"Elige metodo de pago: " + "\nEfectivo: (1)" + "\nTarjeta: (2)", 1, 2, 3);
								errorControl = false;
							} catch (NumberFormatException e) {
								System.out.println(e.getMessage());
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						} while (errorControl);

						if (bOption == 1) {
							sPago = "efectivo";
						}
						if (bOption == 2) {
							sPago = "tarjeta";
						}
						MetodoPago oMetodoPago = new MetodoPago(sPago);

						Pago oPago = new Pago(iId, new Date(), oMetodoPago);

						String sNombreLocal = L.leer("Nombre: (Instalacion o Local)");

						Instalacion oInstalacion = determinarInstalacion(sNombreLocal, controlGeneral);

						if (oInstalacion != null) {

							Pedido oPedido = new Pedido(iId, new Date(), oUsuario, oPago, oInstalacion);

							LineaPedido oLineaPedido = new LineaPedido(iId, oPedido, oProducto, bCantidad, "Compra",
									oProveedor);

							if (controlGeneral.getLineaPedidoController().add(oLineaPedido)) {
								sResultado = "Aniadido correctamente";
							}
						}

					} else if (bOptionPago == 2) {

						String sNombreLocal = L.leer("Nombre: (Instalacion o Local)");

						Instalacion oInstalacion = determinarInstalacion(sNombreLocal, controlGeneral);

						if (oInstalacion != null) {
							Pedido oPedido = new Pedido(iId, new Date(), oUsuario, oInstalacion);

							LineaPedido oLineaPedido = new LineaPedido(iId, oPedido, oProducto, bCantidad, "Compra",
									oProveedor);

							if (controlGeneral.getLineaPedidoController().addSinPago(oLineaPedido)) {
								sResultado = "Aniadido correctamente";
							}
						}

					}

				}

				// ###### MATERIAL ################################################

			} else if (bOptionGenero == 2) {

				String sNombreMaterial = L.leer("Nombre: (Material)");

				if (determinarMaterial(sNombreMaterial, controlGeneral)) {

					oProveedor = obtenerProveedorMaterial(sNombreMaterial, controlGeneral);

					errorControl = true;
					do {
						try {
							fPrecio = (float) L.valida("Precio: (Material)", 0.1F, 3000F, 2);
							errorControl = false;
						} catch (NumberFormatException e) {
							System.out.println(e.getMessage());
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					} while (errorControl);

					errorControl = true;
					do {
						try {
							bCantidad = (byte) L.valida("Cantidad (Unidades)", 1, 100, 3);
							errorControl = false;
						} catch (NumberFormatException e) {
							System.out.println(e.getMessage());
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					} while (errorControl);

					Material oMaterial = null;
					oMaterial = new Material(sNombreMaterial, fPrecio, (short) bCantidad, "Derivados");

					errorControl = true;
					do {
						try {
							bOptionPago = (byte) L.valida("¿Pago incluido?: " + "\nSi: (1)" + "\nNo: (2)", 1, 2, 3);
							errorControl = false;
						} catch (NumberFormatException e) {
							System.out.println(e.getMessage());
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					} while (errorControl);

					if (bOptionPago == 1) {

						errorControl = true;
						do {
							try {
								bOption = (byte) L.valida(
										"Elige metodo de pago: " + "\nEfectivo: (1)" + "\nTarjeta: (2)", 1, 2, 3);
								errorControl = false;
							} catch (NumberFormatException e) {
								System.out.println(e.getMessage());
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						} while (errorControl);

						if (bOption == 1) {
							sPago = "efectivo";
						}
						if (bOption == 2) {
							sPago = "tarjeta";
						}
						MetodoPago oMetodoPago = new MetodoPago(sPago);

						Pago oPago = new Pago(iId, new Date(), oMetodoPago);

						String sNombreLocal = L.leer("Nombre: (Instalacion o Local)");

						Instalacion oInstalacion = determinarInstalacion(sNombreLocal, controlGeneral);

						if (oInstalacion != null) {

							Pedido oPedido = new Pedido(iId, new Date(), oUsuario, oPago, oInstalacion);

							LineaPedido oLineaPedido = new LineaPedido(iId, oPedido, oMaterial, bCantidad, "Compra",
									oProveedor);

							if (controlGeneral.getLineaPedidoController().add(oLineaPedido)) {
								sResultado = "Aniadido correctamente";
							}
						}

					} else if (bOptionPago == 2) {

						String sNombreLocal = L.leer("Nombre: (Instalacion o Local)");

						Instalacion oInstalacion = determinarInstalacion(sNombreLocal, controlGeneral);

						if (oInstalacion != null) {
							Pedido oPedido = new Pedido(iId, new Date(), oUsuario, oInstalacion);

							LineaPedido oLineaPedido = new LineaPedido(iId, oPedido, oMaterial, bCantidad, "Compra",
									oProveedor);

							if (controlGeneral.getLineaPedidoController().addSinPago(oLineaPedido)) {
								sResultado = "Aniadido correctamente";
							}
						}

					}

				}

			}

			// --------------------------------------------------------------

		}
		System.out.println(sResultado);
	}

	/***********************************************************************************************
	 * BORRAR LINEA DE PEDIDO
	 ***********************************************************************************************/

	public static void borrarPedido(GeneralController controlGeneral) {

		int iNumero = 0;

		String sResultado = "No se pudo borrar la linea de pedido";

		String sAccion = controlGeneral.getLineaPedidoController().mostrarPedidosPorId();
		System.out.println(sAccion);
		if (!sAccion.equals("No hay lineas de pedidos")) {

			boolean errorControl = true;
			do {
				try {
					iNumero = (int) L.valida("Dime id: (Linea de Pedido)", 1, 100000, 1);
					errorControl = false;
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			if (controlGeneral.getLineaPedidoController().remove(iNumero)) {
				sResultado = "Linea de Pedido borrado";
			}

			System.out.println(sResultado);

		}
	}

	/***********************************************************************************************
	 * BUSCAR LINEA DE PEDIDO
	 ***********************************************************************************************/
	public static void buscarPedido(GeneralController controlGeneral) {
		String sResultado = "Linea de Pedido no registrado";

		String sAccion = controlGeneral.getLineaPedidoController().mostrarPedidosPorId();
		System.out.println(sAccion);
		if (!sAccion.equals("No hay lineas de pedidos")) {

			int iNumero = 0;
			boolean errorControl = true;
			do {
				try {
					iNumero = (int) L.valida("Dime id: (Pedido)", 1, 100000, 1);
					errorControl = false;
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			if (controlGeneral.getLineaPedidoController().search(iNumero)) {
				sResultado = "Linea registrada";
			}
			System.out.println(sResultado);
		} else {
			System.out.println(sAccion);
		}
	}

	/***********************************************************************************************
	 * MOSTRAR PEDIDO
	 ***********************************************************************************************/
	public static void mostrarPedido(GeneralController controlGeneral) {

		String sResultado = null;
		String sAccion = controlGeneral.getLineaPedidoController().mostrarPedidosPorId();
		if (!sAccion.equals("No hay lineas de pedidos")) {

			byte bOption = 0;

			boolean errorControl = true;

			do {
				try {
					bOption = (byte) L.valida(""
							//
							+ "El pedido de un proveedor: 		(1)"
							//
							+ "\nTodos los pedidos: 			(2)", 1, 2, 3);

					errorControl = false;

				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			if (bOption == 1) {
				String sNombreProveedor = null;

				sNombreProveedor = L.leer("Nombre: (Proveedor)");

				if (controlGeneral.getLineaPedidoController().searchPedidoProveedor(sNombreProveedor)) {
					sResultado = controlGeneral.getLineaPedidoController().mostrarPedidoProveedor(sNombreProveedor);

				} else {
					sResultado = "Pedido no encontrado";
				}

			} else if (bOption == 2) {
				sResultado = controlGeneral.getLineaPedidoController().mostrarPedidos();
			}
			System.out.println(sResultado);
		} else {
			System.out.println(sAccion);
		}
	}

	/*******************************************************************************************************************
	 * OBTENER USUARIO A PARTIR DE UN DNI
	 *******************************************************************************************************************/
	public static Usuario determinarUsuarioPedido(String sDni, GeneralController controlGeneral) {

		Usuario oUsuario = controlGeneral.getUsuarioController().determinarUsuarioPedido(sDni);

		return oUsuario;
	}

	/*******************************************************************************************************************
	 * DETERMINAR MATERIAL
	 *******************************************************************************************************************/
	public static boolean determinarMaterial(String sNombreMaterial, GeneralController controlGeneral) {
		boolean bEncontrado = false;

		if (controlGeneral.getMaterialProveedorController().determinarMaterial(sNombreMaterial)) {
			bEncontrado = true;
		}

		return bEncontrado;
	}

	/*******************************************************************************************************************
	 * OBTENER PROVEEDOR A PARTIR DE UN MATERIAL
	 *******************************************************************************************************************/
	public static Proveedor obtenerProveedorMaterial(String sNombreMaterial, GeneralController controlGeneral) {
		Proveedor oProveedor = null;

		oProveedor = controlGeneral.getMaterialProveedorController().obtenerProveedorMaterial(sNombreMaterial);
		return oProveedor;
	}

	/*******************************************************************************************************************
	 * DETERMINAR PRODUCTO
	 *******************************************************************************************************************/
	public static boolean determinarProducto(String sNombreProducto, GeneralController controlGeneral) {

		boolean bEncontrado = false;

		if (controlGeneral.getProductoProveedorController().determinarProducto(sNombreProducto)) {
			bEncontrado = true;
		}

		return bEncontrado;
	}

	/*******************************************************************************************************************
	 * OBTENER PROVEEDOR A PARTIR DE UN PRODUCTO
	 *******************************************************************************************************************/
	public static Proveedor obtenerProveedorProducto(String sNombreProducto, GeneralController controlGeneral) {
		Proveedor oProveedor = null;

		oProveedor = controlGeneral.getProductoProveedorController().obtenerProveedorProducto(sNombreProducto);
		return oProveedor;
	}

	/*******************************************************************************************************************
	 * DETERMINAR INSTALACION
	 *******************************************************************************************************************/
	public static Instalacion determinarInstalacion(String sNombreLocal, GeneralController controlGeneral) {
		Instalacion oInstalacion = null;

		oInstalacion = controlGeneral.getInstalacionController().determinarInstalacion(sNombreLocal);
		return oInstalacion;
	}

}
