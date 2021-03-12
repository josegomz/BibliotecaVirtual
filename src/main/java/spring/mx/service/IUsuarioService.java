package spring.mx.service;

import spring.mx.entity.Usuario;

public interface IUsuarioService {
	public Usuario findByUsername(String username);
	public Usuario registrar(Usuario usuario);
}
