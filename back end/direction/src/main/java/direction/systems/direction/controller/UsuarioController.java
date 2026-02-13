package direction.systems.direction.controller;

import direction.systems.direction.entities.Usuario;
import direction.systems.direction.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {


  private UsuarioService usuarioService;


  public UsuarioController(UsuarioService service) {
    this.usuarioService = service;
  }


  @PostMapping
  public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario){
    Usuario novoUsuario = usuarioService.criar(usuario);
    return ResponseEntity.status(201).body(novoUsuario);
  }

  @GetMapping
  public ResponseEntity<List<Usuario>> listar (){
    return ResponseEntity.ok(usuarioService.listar());
  }


}
