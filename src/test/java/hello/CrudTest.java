package hello;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import jhonattan.testeFullStack.clientes.controller.ClienteController;
import jhonattan.testeFullStack.clientes.model.Cliente;
import jhonattan.testeFullStack.clientes.service.ClienteService;

public class CrudTest extends ApplicationTest {

	private MockMvc mockMvc;

	@Autowired
	private ClienteController clienteController;
	
	@Autowired
	private ClienteService clienteService;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
	}

	@Test
	public void testGetAll() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testCreate() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/save")
				.param("salvar", "salvar")
				.param("cod_cli", "100")
				.param("nome_cli", "TTTTTTTTTTTTTTTTTTTTTT")
				.param("data_nasc", "01-01-01")
				.param("telefones[0].cod_tipo", "1")
				.param("enderecos[0].cod_tipo", "1")
				.param("telefones[0].cod_fone", "-1")
				.param("enderecos[0].cod_ende", "-1")
				).andExpect(MockMvcResultMatchers.redirectedUrl("/"));
	}
	
	@Test
	public void tesSelectUpdate() throws Exception {
		Cliente clienteTeste = clienteService.findTeste();
		this.mockMvc.perform(MockMvcRequestBuilders.post("/save")
				.param("salvar", "salvar")
				.param("cod_cli", ""+clienteTeste.getCod_cli())
				.param("nome_cli", "TTTTTTTTTTTTTTTTTTTTTT")
				.param("data_nasc", "01-01-01")
				.param("telefones[0].cod_tipo", "1")
				.param("enderecos[0].cod_tipo", "1")
				.param("telefones[0].cod_fone", "-1")
				.param("enderecos[0].cod_ende", "-1")
				).andExpect(MockMvcResultMatchers.redirectedUrl("/"));
	}
	
	@Test
	public void tesRemove() throws Exception {
		Cliente clienteTeste = clienteService.findTeste();
		this.mockMvc.perform(MockMvcRequestBuilders.get("/delete/"+clienteTeste.getCod_cli())).andExpect(MockMvcResultMatchers.redirectedUrl("/"));
	}
	
	
	
	
}