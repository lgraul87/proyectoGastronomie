package controlador;

public class PedidoController {

	public final int MIN_ID = 0;
	private int autoId;

	public PedidoController() {
		setAutoId(MIN_ID);
	}

	public int getAutoId() {
		return autoId;
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
}
