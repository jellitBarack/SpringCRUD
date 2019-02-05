package jhonattan.testeFullStack.clientes.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer cod_cli;

	@Size(max = 80, message = "nome do cliente, max 80 caracteres")
	String nome_cli;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Data é uma informação obrigatória.")
	Date data_nasc;

	@Size(max = 100, message = "cidade de nascimento, max 100 caracteres")
	String cidade_nasc;

	@Size(max = 80, message = "nome do pai, max 80 caracteres")
	String pai_cli;

	@Size(max = 80, message = "nome da mãe, max 80 caracteres")
	String mae_cli;

	@Size(max = 14, message = "CPF, max 14 caracteres")
	String cpf_cli;

	@Size(max = 20, message = "RG, max 20 caracteres")
	String rg_cli;

	@Column(insertable=false)
	Date data_cad;
	

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="cod_cli")
	@Fetch(value = FetchMode.SUBSELECT)
	List<Telefone> telefones = new ArrayList<Telefone>();
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="cod_cli")
	@Fetch(value = FetchMode.SUBSELECT)
	List<Endereco> enderecos = new ArrayList<Endereco>();
	
	public Integer getCod_cli() {
		return cod_cli;
	}

	public void setCod_cli(Integer cod_cli) {
		this.cod_cli = cod_cli;
	}

	public String getNome_cli() {
		return nome_cli;
	}

	public void setNome_cli(String nome_cli) {
		this.nome_cli = nome_cli;
	}

	public Date getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(Date data_nasc) {
		this.data_nasc = data_nasc;
	}

	public String getCidade_nasc() {
		return cidade_nasc;
	}

	public void setCidade_nasc(String cidade_nasc) {
		this.cidade_nasc = cidade_nasc;
	}

	public String getPai_cli() {
		return pai_cli;
	}

	public void setPai_cli(String pai_cli) {
		this.pai_cli = pai_cli;
	}

	public String getMae_cli() {
		return mae_cli;
	}

	public void setMae_cli(String mae_cli) {
		this.mae_cli = mae_cli;
	}

	public String getCpf_cli() {
		return cpf_cli;
	}

	public void setCpf_cli(String cpf_cli) {
		this.cpf_cli = cpf_cli;
	}

	public String getRg_cli() {
		return rg_cli;
	}

	public void setRg_cli(String rg_cli) {
		this.rg_cli = rg_cli;
	}

	public Date getData_cad() {
		return data_cad;
	}

	public void setData_cad(Date data_cad) {
		this.data_cad = data_cad;
	}


	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public void addTelefone() {
		Telefone telefone= new Telefone();
		Long num_long = new Date().getTime() * -1 / 2;
		Integer num_int = num_long.intValue();
		telefone.setCod_fone(num_int);
		telefone.setCod_tipo(3);
		this.getTelefones().add(telefone);
	}


	public void addEndereco() {	
		Endereco endereco= new Endereco();
		Long num_long = new Date().getTime() * -1 / 2;
		Integer num_int = num_long.intValue();
		endereco.setCod_ende(num_int);
		System.out.println("new end id"+num_int);
		endereco.setCod_tipo(3);
		this.getEnderecos().add(endereco);
	}
	
	public String toCSV() {
		//todo get id do tipo from SQL
		Telefone telefone = new Telefone();
		Endereco endereco = new Endereco();
		for(Telefone tel : this.getTelefones()) {
			if(tel.getCod_tipo()==1) {
				telefone = tel;
			}
		}
		for(Endereco end : this.getEnderecos()) {
			if(end.getCod_tipo()==1) {
				endereco = end;
			}
		}
		return ""+this.getNome_cli()+";"+this.getData_nasc()+";"+this.getCpf_cli()+";"+this.getMae_cli()+";"+telefone.getDdd_fone()+";"+telefone.getNum_fone()+";"+endereco.getLogradouro()+";"+endereco.getCep()+";"+endereco.getCidade()+";\n";
	}
	
	
	
	
}
