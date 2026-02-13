package direction.systems.direction.services;

import direction.systems.direction.entities.Usuario;
import direction.systems.direction.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {


  private UsuarioRepository usuarioRepository;

  public UsuarioService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }



  public Usuario criar(Usuario usuario){
    return usuarioRepository.save(usuario);
  }


}
