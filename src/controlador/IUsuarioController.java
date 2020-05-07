package controlador;

import modelo.usuario.Usuario;

public interface IUsuarioController {

	public boolean add(Usuario oUsuario);

	public void createTipoUsuario(String sTipo);

	public boolean searchTipoUsuario(String sTipo);

	public boolean remove(String sDni);

	public boolean deleteUser(String sDni);

	public boolean searchUser(String sDni);

	public String mostrarUsuario(String sDni);

	public String mostrarUsuarios();

	public Usuario determinarUsuarioPedido(String sDni);

}
