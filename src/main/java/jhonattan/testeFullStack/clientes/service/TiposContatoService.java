package jhonattan.testeFullStack.clientes.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jhonattan.testeFullStack.clientes.model.TiposContato;
import jhonattan.testeFullStack.clientes.repository.TiposContatoRepository;

@Service
public class TiposContatoService {
	
	@Autowired
	private TiposContatoRepository repository;
	
	public List<TiposContato> findAll() {
		return repository.findAll();
	}
	

}