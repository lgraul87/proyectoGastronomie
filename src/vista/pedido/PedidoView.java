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

			Producto oProducto = new Producto(sBebida);

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
				sPago = "Efectivo";
			}
			if (bOption == 2) {
				sPago = "Tarjeta";
			}
			MetodoPago oMetodoPago = new MetodoPago(sPago);

			Pago oPago = new Pago(iId, new Date(), oMetodoPago);

			String sNombreLocal = "Local1";

			TipoUsuario oTipoUsuario = new TipoUsuario("Cliente");
			Instalacion oInstalacion = new Instalacion(sNombreLocal, "Central", 1000);

			Usuario oUsuario = new Usuario("Cliente", "123456789", "Cliente", "Sin", oTipoUsuario);

			oProducto = controlGeneral.getProductoController().obtenerProducto(oProducto);

			if (oProducto != null) {

				float fPrecioTotal = oProducto.getfPrecio() * bCantidad;

				if (controlGeneral.getProductoController().update(oProducto, bCantidad)) {

					TipoProveedor oTipoProveedor = new TipoProveedor("Gastronomie");
					Proveedor oProveedor = new Proveedor("Gastronomie", "654554321", "Gastronomie@gmail.com",
							"Comercio 73", oTipoProveedor);
					Pedido oPedido = new Pedido(controlGeneral.getLineaPedidoController().autoId(), new Date(),
							oUsuario, oPago, oInstalacion);

					LineaPedido oLineaPedido = new LineaPedido(iId, oPedido, oProducto, bCantidad, "Venta", oProveedor);
					if (controlGeneral.getLineaPedidoController().add(oLineaPedido)) {
						sResultado = "Aniadido correctamente: " + fPrecioTotal + " euros por favor.";

						int iNumero = contarExistencias(oProducto, controlGeneral);
						if (iNumero < 10) {

							oTipoUsuario = new TipoUsuario("Admin");

							oUsuario = new Usuario("Raul", "49789078F", "Lora", "873299056", "lg@gmail.com", "Raul123",
									oTipoUsuario);

							oPedido = new Pedido(iId, new Date(), oUsuario, oInstalacion);

							byte bPedir = 20;

							LineaPedido oLineaPedidoRecarga = new LineaPedido(iId, oPedido, oProducto, bPedir, "Compra",
									oProveedor);
							if (controlGeneral.getLineaPedidoController().addSinPago(oLineaPedidoRecarga)) {

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

			Producto oProducto = new Producto(sTapas);
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

			Usuario oUsuario = new Usuario("Cliente", "123456789", "Cliente", "Sin", oTipoUsuario);

			oProducto = controlGeneral.getProductoController().obtenerProducto(oProducto);

			if (oProducto != null) {

				float fPrecioTotal = oProducto.getfPrecio() * bCantidad;

				if (controlGeneral.getProductoController().update(oProducto, bCantidad)) {

					TipoProveedor oTipoProveedor = new TipoProveedor("Gastronomie");
					Proveedor oProveedor = new Proveedor("Gastronomie", "654554321", "Gastronomie@gmail.com",
							oTipoProveedor);
					Pedido oPedido = new Pedido(iId, new Date(), oUsuario, oPago, oInstalacion);

					LineaPedido oLineaPedido = new LineaPedido(iId, oPedido, oProducto, bCantidad, "Venta", oProveedor);
					if (controlGeneral.getLineaPedidoController().add(oLineaPedido)) {
						sResultado = "Aniadido correctamente: " + fPrecioTotal + " euros por favor.";
						int iNumero = contarExistencias(oProducto, controlGeneral);
						if (iNumero < 10) {

							oTipoUsuario = new TipoUsuario("Admin");

							oUsuario = new Usuario("Raul", "49789078F", "Lora", "873299056", "lg@gmail.com", "Raul123",
									oTipoUsuario);

							oPedido = new Pedido(iId, new Date(), oUsuario, oInstalacion);

							byte bPedir = 20;

							LineaPedido oLineaPedidoRecarga = new LineaPedido(iId, oPedido, oProducto, bPedir, "Compra",
									oProveedor);
							if (controlGeneral.getLineaPedidoController().addSinPago(oLineaPedidoRecarga)) {

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

			Producto oProducto = new Producto(sBebida);

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
				sPago = "Efectivo";
			}
			if (bOption == 2) {
				sPago = "Tarjeta";
			}
			MetodoPago oMetodoPago = new MetodoPago(sPago);

			Pago oPago = new Pago(iId, new Date(), oMetodoPago);

			String sNombreLocal = "Local1";

			TipoUsuario oTipoUsuario = new TipoUsuario("Cliente");
			Instalacion oInstalacion = new Instalacion(sNombreLocal, "Central", 1000);

			Usuario oUsuario = new Usuario("Cliente", "123456789", "Cliente", "Sin", oTipoUsuario);

			oProducto = controlGeneral.getProductoController().obtenerProducto(oProducto);

			if (oProducto != null) {

				float fPrecioTotal = oProducto.getfPrecio() * bCantidad;

				if (controlGeneral.getProductoController().update(oProducto, bCantidad)) {

					TipoProveedor oTipoProveedor = new TipoProveedor("Gastronomie");
					Proveedor oProveedor = new Proveedor("Gastronomie", "654554321", "Gastronomie@gmail.com",
							"Comercio 73", oTipoProveedor);
					Pedido oPedido = new Pedido(controlGeneral.getLineaPedidoController().autoId(), new Date(),
							oUsuario, oPago, oInstalacion);

					LineaPedido oLineaPedido = new LineaPedido(iId, oPedido, oProducto, bCantidad, "Venta", oProveedor);
					if (controlGeneral.getLineaPedidoController().add(oLineaPedido)) {
						sResultado = "Aniadido correctamente: " + fPrecioTotal + " euros por favor.";

						int iNumero = contarExistencias(oProducto, controlGeneral);
						if (iNumero < 10) {

							oTipoUsuario = new TipoUsuario("Admin");

							oUsuario = new Usuario("Raul", "49789078F", "Lora", "873299056", "lg@gmail.com", "Raul123",
									oTipoUsuario);

							oPedido = new Pedido(iId, new Date(), oUsuario, oInstalacion);

							byte bPedir = 20;

							LineaPedido oLineaPedidoRecarga = new LineaPedido(iId, oPedido, oProducto, bPedir, "Compra",
									oProveedor);
							if (controlGeneral.getLineaPedidoController().addSinPago(oLineaPedidoRecarga)) {

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

			Producto oProducto = new Producto(sTapas);
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

			Usuario oUsuario = new Usuario("Cliente", "123456789", "Cliente", "Sin", oTipoUsuario);

			oProducto = controlGeneral.getProductoController().obtenerProducto(oProducto);

			if (oProducto != null) {

				float fPrecioTotal = oProducto.getfPrecio() * bCantidad;

				if (controlGeneral.getProductoController().update(oProducto, bCantidad)) {

					TipoProveedor oTipoProveedor = new TipoProveedor("Gastronomie");
					Proveedor oProveedor = new Proveedor("Gastronomie", "654554321", "Gastronomie@gmail.com",
							oTipoProveedor);
					Pedido oPedido = new Pedido(iId, new Date(), oUsuario, oPago, oInstalacion);

					LineaPedido oLineaPedido = new LineaPedido(iId, oPedido, oProducto, bCantidad, "Venta", oProveedor);
					if (controlGeneral.getLineaPedidoController().add(oLineaPedido)) {
						sResultado = "Aniadido correctamente: " + fPrecioTotal + " euros por favor.";
						int iNumero = contarExistencias(oProducto, controlGeneral);
						if (iNumero < 10) {

							oTipoUsuario = new TipoUsuario("Admin");

							oUsuario = new Usuario("Raul", "49789078F", "Lora", "873299056", "lg@gmail.com", "Raul123",
									oTipoUsuario);

							oPedido = new Pedido(iId, new Date(), oUsuario, oInstalacion);

							byte bPedir = 20;

							LineaPedido oLineaPedidoRecarga = new LineaPedido(iId, oPedido, oProducto, bPedir, "Compra",
									oProveedor);
							if (controlGeneral.getLineaPedidoController().addSinPago(oLineaPedidoRecarga)) {

								System.out.println("Recargando");
							}
						}

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
		byte bOption = 0;
		boolean errorControl = true;
		do {
			try {
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

				errorControl = false;

			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

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
		} while (errorControl);
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

		Usuario oUsuario = new Usuario(sDni);

		oUsuario = determinarUsuarioPedido(oUsuario, controlGeneral);

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

						Instalacion oInstalacion = new Instalacion(sNombreLocal);

						oInstalacion = determinarInstalacion(oInstalacion, controlGeneral);

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

						Instalacion oInstalacion = new Instalacion(sNombreLocal);

						oInstalacion = determinarInstalacion(oInstalacion, controlGeneral);

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

				Material oMaterial = new Material(sNombreMaterial);

				if (determinarMaterial(oMaterial, controlGeneral)) {

					oProveedor = obtenerProveedorMaterial(oMaterial, controlGeneral);

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

						Instalacion oInstalacion = new Instalacion(sNombreLocal);

						oInstalacion = determinarInstalacion(oInstalacion, controlGeneral);

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

						Instalacion oInstalacion = new Instalacion(sNombreLocal);

						oInstalacion = determinarInstalacion(oInstalacion, controlGeneral);

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

		String sResultado = "No hay pedidos";

		if (!controlGeneral.getLineaPedidoController().mostrarPedidos().equals("No hay pedidos")) {

			sResultado = "No se pudo borrar la linea de pedido";

			System.out.println(controlGeneral.getLineaPedidoController().mostrarPedidosPorId());

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
		}
		System.out.println(sResultado);

	}

	/***********************************************************************************************
	 * BUSCAR LINEA DE PEDIDO
	 ***********************************************************************************************/
	public static void buscarPedido(GeneralController controlGeneral) {

		String sResultado = "No hay pedidos";

		if (!controlGeneral.getLineaPedidoController().mostrarPedidos().equals("No hay pedidos")) {

			sResultado = "Linea de Pedido no registrado";

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

		}

		System.out.println(sResultado);
	}

	/***********************************************************************************************
	 * MOSTRAR PEDIDO
	 ***********************************************************************************************/
	public static void mostrarPedido(GeneralController controlGeneral) {

		String sResultado = "No hay pedidos";

		if (!controlGeneral.getLineaPedidoController().mostrarPedidos().equals("No hay pedidos")) {

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

				Proveedor oProveedor = new Proveedor(sNombreProveedor);

				if (controlGeneral.getLineaPedidoController().searchPedidoProveedor(oProveedor)) {
					sResultado = controlGeneral.getLineaPedidoController().mostrarPedidoProveedor(oProveedor);

				} else {
					sResultado = "No hay lineas de pedidos con ese proveedor";
				}

			} else if (bOption == 2) {
				sResultado = controlGeneral.getLineaPedidoController().mostrarPedidos();
			}

		}

		System.out.println(sResultado);
	}

	/*******************************************************************************************************************
	 * OBTENER USUARIO A PARTIR DE UN DNI
	 *******************************************************************************************************************/
	public static Usuario determinarUsuarioPedido(Usuario oUsuario, GeneralController controlGeneral) {

		oUsuario = controlGeneral.getUsuarioController().determinarUsuarioPedido(oUsuario);

		return oUsuario;
	}

	/*******************************************************************************************************************
	 * DETERMINAR MATERIAL
	 *******************************************************************************************************************/
	public static boolean determinarMaterial(Material oMaterial, GeneralController controlGeneral) {
		boolean bEncontrado = false;

		if (controlGeneral.getMaterialProveedorController().determinarMaterial(oMaterial)) {
			bEncontrado = true;
		}

		return bEncontrado;
	}

	/*******************************************************************************************************************
	 * OBTENER PROVEEDOR A PARTIR DE UN MATERIAL
	 *******************************************************************************************************************/
	public static Proveedor obtenerProveedorMaterial(Material oMaterial, GeneralController controlGeneral) {
		Proveedor oProveedor = null;

		oProveedor = controlGeneral.getMaterialProveedorController().obtenerProveedorMaterial(oMaterial);
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
	public static Instalacion determinarInstalacion(Instalacion oInstalacion, GeneralController controlGeneral) {

		oInstalacion = controlGeneral.getInstalacionController().determinarInstalacion(oInstalacion);
		return oInstalacion;
	}

}
