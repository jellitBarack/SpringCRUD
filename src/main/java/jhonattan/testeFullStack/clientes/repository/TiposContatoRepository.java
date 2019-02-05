package jhonattan.testeFullStack.clientes.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jhonattan.testeFullStack.clientes.model.TiposContato;

@Repository
public interface TiposContatoRepository extends JpaRepository<TiposContato, Integer> { }