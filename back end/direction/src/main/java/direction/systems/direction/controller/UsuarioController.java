package direction.systems.direction.controller;

import direction.systems.direction.dtos.LoginRequest;
import direction.systems.direction.dtos.LoginResponse;
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


  @PostMapping("/cadastro")
  public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario){
    Usuario novoUsuario = usuarioService.criar(usuario);
    return ResponseEntity.status(201).body(novoUsuario);
  }

  @GetMapping
  public ResponseEntity<List<Usuario>> listar (){
    return ResponseEntity.ok(usuarioService.listar());
  }


  @GetMapping("/{id}")
  public ResponseEntity<Usuario> buscar(@PathVariable Long id){
    return ResponseEntity.ok(usuarioService.buscarPorId(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> apagar (@PathVariable Long id){
    usuarioService.apagar(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
    String token = usuarioService.login(request.email(), request.senha());
    return ResponseEntity.ok(new LoginResponse(token));
  }

  @GetMapping("/nome")
  public ResponseEntity<Usuario> nomeUsuarioLogado (){
    return ResponseEntity.ok(usuarioService.nomeUsuarioLogado());
  }

}
