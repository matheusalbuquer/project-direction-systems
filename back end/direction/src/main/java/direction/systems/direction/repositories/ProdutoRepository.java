package direction.systems.direction.repositories;

import direction.systems.direction.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
