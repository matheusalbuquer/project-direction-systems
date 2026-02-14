package direction.systems.direction.repositories;

import direction.systems.direction.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProdutoRepository extends JpaRepository<Produto, Long> {
  List<Produto> findByUsuarioEmail(String email);

}
