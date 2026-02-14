package direction.systems.direction.controller;

import direction.systems.direction.entities.Produto;
import direction.systems.direction.entities.Usuario;
import direction.systems.direction.services.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

  private final ProdutoService produtoService;

  public ProdutoController (ProdutoService produtoService){
    this.produtoService = produtoService;
  }

  @PostMapping
  public ResponseEntity<Produto> criar(@RequestBody Produto produto){
    Produto novoProduto = produtoService.criar(produto);
    return ResponseEntity.status(201).body(novoProduto);
  }

  @GetMapping
  public ResponseEntity<List<Produto>> listar(){
    List<Produto> produtos = produtoService.listar();
    return ResponseEntity.status(200).body(produtos);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Produto> editar(@PathVariable Long id, @RequestBody Produto produto){
    produto.setId(id);
    Produto produtoAtualizado = produtoService.editar(produto);
    return ResponseEntity.ok(produtoAtualizado);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> apagar (@PathVariable Long id){
     produtoService.apagar(id);
    return ResponseEntity.noContent().build();
  }

}
