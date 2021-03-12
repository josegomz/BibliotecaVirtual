package spring.mx.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import spring.mx.entity.Usuario;
import spring.mx.service.IUsuarioService;

@Controller
public class LoginController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/auth/login")
	public String login(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	@GetMapping("/auth/registro")
	public String registroForm(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "registro";
	}
	
	@PostMapping("/auth/registro")
	public String registro(@Valid @ModelAttribute Usuario usuario,
			@RequestParam(name="fotoPerfil",required = false) MultipartFile foto, 
			BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "redirect:/auth/registro";
		}else {
			if(!foto.isEmpty()) {
				Path DirectorioImagenes = Paths.get("src","main","resources","static","img","usuario");
				String RutaIMG = DirectorioImagenes.toFile().getAbsolutePath();
				//guardar imagen
				try {
					byte[] byteIMG = foto.getBytes();
					
					String RutaAbsoluta = Paths.get(RutaIMG+"/"+usuario.getUsername()+".jpg").toString();
					File dir = new File(RutaIMG);
					if(!dir.exists()) {
						System.out.println("Archivo creado");
						dir.mkdirs();					
					}else {
						System.out.println("Archivo existe");
					}
					System.out.println(RutaAbsoluta);
					//guardar el documento
					BufferedOutputStream  stream = new BufferedOutputStream(new FileOutputStream(RutaAbsoluta));
					stream.write(byteIMG);
					stream.close();
					usuario.setFoto_perfil(foto.getOriginalFilename());
				}catch(Exception e) {
					e.getMessage();
				}
			}
			model.addAttribute("usuario", usuarioService.registrar(usuario));
		}
		return "redirect:/auth/login";
	}

}
