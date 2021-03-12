package spring.mx.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.mx.entity.Usuario;

@Repository
public interface IUsuarioModel extends JpaRepository<Usuario, Long>{
	public Usuario findByUsername(String username);
}
