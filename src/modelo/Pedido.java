package modelo;

import java.util.Date;

public class Pedido implements LimitsDB {
	private int iIdPedido; // PK
	private Date dFecha; // NN
	private Usuario oUsuario; // FK
	private Pago oPago; // FK
	private Instalacion oInstalacion; // FK
}
