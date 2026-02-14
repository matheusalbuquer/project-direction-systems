package direction.systems.direction.services;

import direction.systems.direction.entities.Usuario;
import direction.systems.direction.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

  private final UsuarioRepository usuarioRepository;

  private final PasswordEncoder passwordEncoder;


  private final JwtService jwtService;

  //Contrutor para injetar as dependencias
  public UsuarioService(UsuarioRepository usuarioRepository,
                        PasswordEncoder passwordEncoder,
                        JwtService jwtService) {
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }


  public Usuario criar(Usuario usuario){
    usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
    return usuarioRepository.save(usuario);
  }

  public List<Usuario> listar (){
    return usuarioRepository.findAll();
  }

  public Usuario buscarPorId (Long id){
    return usuarioRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Usuário não encntrado!"));
  }

  public void apagar(Long id){
    if(!usuarioRepository.existsById(id)){
      throw new RuntimeException("Usuario não encontrado");
    }
      usuarioRepository.deleteById(id);
  }

  public String login(String email, String senha){

    Usuario usuario = usuarioRepository.findByEmail(email)
      .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


    if(passwordEncoder.matches(senha, usuario.getSenha())){
      return jwtService.gerarToken(usuario.getEmail());
    }

    throw new RuntimeException("Senha inválida");
    
  }



}
