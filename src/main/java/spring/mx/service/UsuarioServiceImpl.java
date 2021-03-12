package spring.mx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import spring.mx.entity.Usuario;
import spring.mx.model.IUsuarioModel;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder; 
	
	@Autowired
	private IUsuarioModel usuarioModel;
	
	@Override
	public Usuario findByUsername(String username) {
		return usuarioModel.findByUsername(username);
	}

	@Override
	public Usuario registrar(Usuario usuario) {
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return usuarioModel.save(usuario);
	}

}
