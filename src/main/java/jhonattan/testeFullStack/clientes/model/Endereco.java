package jhonattan.testeFullStack.clientes.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="enderecos_clientes")
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer cod_ende;
	
	Integer cod_tipo;
         
	@Size(max = 80, message = "Logradouro, max 80 caracteres")
    String logradouro;
    
	@Size(max = 80, message = "Bairro, max 80 caracteres")
    String bairro;
	
	@Size(max = 80, message = "Complemento, max 80 caracteres")
	String complemento;
    
	@Size(max = 80, message = "Complemento, max 80 caracteres")
	String cidade;
    
	@Size(max = 8, message = "CEP, max 8 caracteres")
	String cep;

	@Column(insertable = false)
	Date data_cad;

	public Integer getCod_ende() {
		return cod_ende;
	}

	public void setCod_ende(Integer cod_ende) {
		this.cod_ende = cod_ende;
	}

	public Integer getCod_tipo() {
		return cod_tipo;
	}

	public void setCod_tipo(Integer cod_tipo) {
		this.cod_tipo = cod_tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Date getData_cad() {
		return data_cad;
	}

	public void setData_cad(Date data_cad) {
		this.data_cad = data_cad;
	}
	
	
}
