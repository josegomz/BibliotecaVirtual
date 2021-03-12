package spring.mx.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

import spring.mx.entity.Usuario;
import spring.mx.model.IUsuarioModel;

@Service
public class UserDetailServiceImpl  implements UserDetailsService{

	@Autowired
	private IUsuarioModel usuarioModel;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioModel.findByUsername(username);
		UserBuilder builder = null;
		if(usuario != null) {
			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(usuario.getPassword());
			builder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
			
		}else {
			throw new UsernameNotFoundException("Usuario no encontrado..");
		}
		return builder.build();
	}

}
