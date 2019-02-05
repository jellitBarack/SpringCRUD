package jhonattan.testeFullStack.clientes.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import jhonattan.testeFullStack.clientes.model.Cliente;
import jhonattan.testeFullStack.clientes.model.Endereco;
import jhonattan.testeFullStack.clientes.model.Telefone;
import jhonattan.testeFullStack.clientes.model.TiposContato;
import jhonattan.testeFullStack.clientes.service.ClienteService;
import jhonattan.testeFullStack.clientes.service.TiposContatoService;

@Controller
public class ClienteController {
	static Logger log = Logger.getLogger(ClienteController.class.getName());
	@Autowired
	private ClienteService service;

	@Autowired
	private TiposContatoService serviceTipos;

	@GetMapping("/downloadcsv")
	public @ResponseBody String downloadcsv(HttpServletResponse response) {
		log.info("DOWNLOAD");
		List<Cliente> clientes = service.findAll();
		String file = "Nome_cli;Data_nasc;Cpf_cli;Mae_cli;Ddd_fone;Num_fone;Logradouro;Cep;Cidade;\n";
		for (Cliente cli : clientes) {
			file += cli.toCSV();
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"test.csv\"");
		return file;
	}

	@GetMapping("/")
	public ModelAndView findAll() {
		log.info("findAll");
		ModelAndView mv = new ModelAndView("cliente");
		mv.addObject("clientes", service.findAll());

		return mv;
	}

	@GetMapping("add")
	public ModelAndView add(Cliente cliente) {
		log.info("add");
		try {
			ModelAndView mv = new ModelAndView("clienteDetails");
			mv.addObject("cliente", cliente);

			List<TiposContato> tipos = this.serviceTipos.findAll();
			mv.addObject("tipos", tipos);

			return mv;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@GetMapping("edit/{id}")
	public ModelAndView edit(@PathVariable("id") Integer id) {
		log.info("edit "+id);
		Cliente cliente = service.findOne(id);
		return add(cliente);
	}

	@GetMapping("delete/{id}")
	public ModelAndView delete(@PathVariable("id") Integer id) {
		log.info("edit "+id);
		service.delete(id);
		return new ModelAndView("redirect:" + '/');
	}

	@PostMapping(value = "save", params = "addTelefone")
	public ModelAndView addPhone(@Valid Cliente cliente, BindingResult result) {
		log.info("add telefone");
		cliente.addTelefone();
		return add(cliente);
	}

	@PostMapping(value = "save", params = "delTelefone")
	public ModelAndView removePhone(@Valid Cliente cliente, BindingResult result, final HttpServletRequest req) {
		log.info("del telefone");
		final Integer paramId = new Integer(req.getParameter("delTelefone"));
		for (Iterator<Telefone> iter = cliente.getTelefones().listIterator(); iter.hasNext();) {
			Telefone telefone = iter.next();
			if (telefone.getCod_fone().equals(paramId)) {
				iter.remove();
				System.out.println("removed");
			}
		}
		return add(cliente);
	}

	@PostMapping(value = "save", params = "addEnd")
	public ModelAndView addEnd(@Valid Cliente cliente, BindingResult result) {
		log.info("add endereco");
		cliente.addEndereco();
		return add(cliente);
	}

	@PostMapping(value = "save", params = "delEnd")
	public ModelAndView removeEnd(@Valid Cliente cliente, BindingResult result, final HttpServletRequest req) {
		log.info("del endereco");
		final Integer paramId = new Integer(req.getParameter("delEnd"));
		
		for (Iterator<Endereco> iter = cliente.getEnderecos().listIterator(); iter.hasNext();) {
			Endereco endereco = iter.next();
			if (endereco.getCod_ende().equals(paramId)) {
				iter.remove();
				System.out.println("removed");
			}

		}
		return add(cliente);
	}

	@PostMapping(value = "save", params = "salvar")
	public ModelAndView save(@Valid @ModelAttribute Cliente cliente, BindingResult result,
			final HttpServletRequest req) {
		log.info("save");
		if (result.hasErrors()) {
			return add(cliente);
		}

		if (!this.isValid(cliente, result)) {
			return add(cliente);
		} else {
			System.out.println("validacao OK");
		}

		for (Telefone tel : cliente.getTelefones()) {
			System.out.println(tel.toString());
			if (tel.getCod_fone() < 0) {
				System.out.println(tel.toString());
				tel.setCod_fone(null);
			}
		}
		for (Endereco end : cliente.getEnderecos()) {
			if (end.getCod_ende() < 0) {
				end.setCod_ende(null);
			}
		}
		log.info("SALVAR CLIENTE");
		try {
			log.info("SALVAR CLIENTE - SUCCESS");
			service.save(cliente);
		} catch (Exception e) {
			log.error("SALVAR CLIENTE - ERROR");
			log.error(e.getMessage());
		}

		return new ModelAndView("redirect:" + '/');
	}

	public boolean isValid(Cliente cliente, BindingResult result) {
		log.info("is valid");
		boolean isValidTel = this.isValidTel(cliente, result);
		boolean isValidEnd = this.isValidEnd(cliente, result);
		System.out.println("isValidTel " + isValidTel);
		System.out.println("isValidEnd " + isValidEnd);
		if (isValidTel && isValidEnd) {
			log.info("is valid : true");
			return true;
		} else {
			log.info("is valid : false");
			return false;
		}
	}

	public boolean isValidTel(Cliente cliente, BindingResult result) {
		boolean isValid = true;
		int contTelRes = 0;
		List<Telefone> telefones = cliente.getTelefones();
		if (telefones == null) {
			result.addError(new ObjectError("Problemas nos Telefones", "O Usuario deve conter pelo menos 1 Telefone"));
			return false;
		}
		if (telefones.size() < 1) {
			result.addError(new ObjectError("Problemas nos telefones", "O Usuario deve conter pelo menos 1 Telefone"));
			isValid = false;
		}
		for (Telefone telefone : telefones) {
			if (telefone.getCod_tipo() == 1) {
				contTelRes++;
			}

		}
		if (contTelRes < 1) {
			result.addError(new ObjectError("Problemas nos telefones", "Obrigatório um telefone Residencial"));
			isValid = false;
		}
		return isValid;
	}

	public boolean isValidEnd(Cliente cliente, BindingResult result) {
		//todo : get id residencial from sql
		boolean isVaild = true;
		int contEndRes = 0;
		int contEndCom = 0;
		// 1 RESIDENCIAL
		List<Endereco> enderecos = cliente.getEnderecos();
		if (enderecos == null) {
			result.addError(new ObjectError("Problemas nos Enderecos", "O Cliente deve conter pelo menos 1 Endereço"));
			return false;
		}
		if (enderecos.size() < 1) {
			result.addError(new ObjectError("Problemas nos Enderecos", "O Cliente deve conter pelo menos 1 Endereço"));
			isVaild = false;
		}
		for (Endereco endereco : enderecos) {
			if (endereco.getCod_tipo() == 1) {
				contEndRes++;
			}
			if (endereco.getCod_tipo() == 2) {
				contEndCom++;
			}
		}
		System.out.println("contEndRes :" + contEndRes);
		System.out.println("contEndCom :" + contEndCom);
		if (contEndRes > 1) {
			result.addError(
					new ObjectError("Problemas nos Endereços", "O Cliente deve conter somente 1 Endereço Residencial"));
			isVaild = false;
		} else if (contEndRes < 1) {
			result.addError(new ObjectError("Problemas nos Endereços", "O Endereço Residencial é obrigatório"));
			isVaild = false;
		}
		if (contEndCom > 1) {
			result.addError(
					new ObjectError("Problemas nos Endereços", "O Cliente deve conter somente 1 endereço comercial"));
			isVaild = false;
		}

		return isVaild;
	}

}