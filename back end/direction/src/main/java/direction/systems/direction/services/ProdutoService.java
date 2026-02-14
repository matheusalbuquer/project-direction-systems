package direction.systems.direction.services;

import direction.systems.direction.entities.Produto;
import direction.systems.direction.entities.Usuario;
import direction.systems.direction.repositories.ProdutoRepository;
import direction.systems.direction.repositories.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

  private final UsuarioRepository usuarioRepository;

  private final ProdutoRepository produtoRepository;

  public ProdutoService (ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository){
    this.produtoRepository = produtoRepository;
    this.usuarioRepository = usuarioRepository;
  }

  //Funcionalidade para validar se o usuario ta autenticado
  public Authentication autenticar (){
    return SecurityContextHolder
      .getContext()
      .getAuthentication();
  }//

  public Produto criar (Produto produto){

    Authentication authentication = autenticar();

    if(authentication == null || !authentication.isAuthenticated()){
      throw new IllegalStateException("Usuario não encontradado");
    }

    String login = authentication.getName();

    Usuario usuario = usuarioRepository
      .findByEmail(login)
      .orElseThrow(() -> new IllegalStateException("Usuario nao encontrado"));

    produto.setUsuario(usuario);

    return produtoRepository.save(produto);

  }


  public List<Produto> listar (){
    Authentication authentication = autenticar();

    if(authentication == null || !authentication.isAuthenticated()){
      throw new IllegalStateException("Usuario não encontrado!");
    }
    String login = authentication.getName();


   return produtoRepository.findByUsuarioEmail(login);
  }

  public Produto editar(Produto produto){
    Authentication authentication = autenticar();

    if(authentication == null || !authentication.isAuthenticated()){
      throw new IllegalStateException("Usuario não encontrado");
    }

    String login = authentication.getName();

    Produto produtoExistente = produtoRepository.findById(produto.getId())
      .orElseThrow(() -> new IllegalStateException("Id do produto não encontrado!"));

    produtoExistente.setNome(produto.getNome());
    produtoExistente.setPreco(produto.getPreco());
    produtoExistente.setQuantidade(produto.getQuantidade());
    produtoExistente.setCategoria(produto.getCategoria());

    return produtoRepository.save(produtoExistente);
  }

  public void apagar(Long id){

    /*Verificação se o usuario esta logando*/
    Authentication authentication = autenticar();

    if(authentication == null || !authentication.isAuthenticated()){
      throw new IllegalStateException("Usuario não encontrado!");
    }

    String login = authentication.getName();

    Produto produto = produtoRepository.findById(id)
    .orElseThrow(() -> new IllegalStateException("Id do usuario não encontrado!"));

    if(!produto.getUsuario().getEmail().equals(login)){
      throw new IllegalStateException("Produto não encontrado");
    }

    produtoRepository.delete(produto);
  }


}
