package controlador;

import modelo.usuario.Usuario;

public interface IUsuarioController {

	public boolean add(Usuario oUsuario);

	public void createTipoUsuario(String sTipo);

	public boolean searchTipoUsuario(String sTipo);

	public boolean remove(Usuario oUsuario);

	public boolean deleteUser(Usuario oUsuario);

	public boolean searchUser(Usuario oUsuario);

	public String mostrarUsuario(Usuario oUsuario);

	public String mostrarUsuarios();

	public Usuario determinarUsuarioPedido(Usuario oUsuario);

	public boolean login(String hash);
	
	
	

}
