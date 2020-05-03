package modelo.usuario;

public interface IUsuario {

	public String getsNombre();

	public boolean setsNombre(String sNombre);

	public String getsDni();

	public String getsApellidos();

	public boolean setsApellidos(String sApellidos);

	public int getiTelefono();

	public boolean setiTelefono(int iTelefono);

	public String getsCorreoElectronico();

	public boolean setsCorreoElectronico(String sCorreoElectronico);

	public TipoUsuario getoTipoUsuario();

	public boolean setoTipoUsuario(TipoUsuario oTipoUsuario);

	public boolean checkUsuario();

	public int hashCode();

	public boolean equals(Object obj);

	public String toString();

}
