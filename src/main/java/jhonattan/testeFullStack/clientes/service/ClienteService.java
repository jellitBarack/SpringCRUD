package jhonattan.testeFullStack.clientes.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import jhonattan.testeFullStack.clientes.model.Cliente;
import jhonattan.testeFullStack.clientes.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	public Cliente findOne(Integer id) {
		return repository.findById(id).get();
	}
	
	public Cliente save(Cliente usuario) {
		return repository.saveAndFlush(usuario);
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	public Cliente findTeste() {
		Cliente cliente = new Cliente();
		cliente.setNome_cli("TTTTTTTTTTTTTTTTTTTTTT");
		Example<Cliente> example = Example.of(cliente);
		Cliente rescliente =  repository.findOne(example).get();
		System.out.println("id = "+rescliente.getCod_cli());
		System.out.println("date = "+rescliente.getData_nasc());
		return rescliente;
	}
}