package jhonattan.testeFullStack.clientes.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="telefones_clientes")
public class Telefone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer cod_fone;
	
	Integer cod_tipo;
	
	Integer ddd_fone;
	
	Integer num_fone;
		 
	@Column(insertable = false)
	Date data_cad;

	
	public Integer getCod_tipo() {
		return cod_tipo;
	}

	public void setCod_tipo(Integer cod_tipo) {
		this.cod_tipo = cod_tipo;
	}

	public Integer getCod_fone() {
		return cod_fone;
	}

	public void setCod_fone(Integer cod_fone) {
		this.cod_fone = cod_fone;
	}

	public Integer getDdd_fone() {
		return ddd_fone;
	}

	public void setDdd_fone(Integer ddd_fone) {
		this.ddd_fone = ddd_fone;
	}

	public Integer getNum_fone() {
		return num_fone;
	}

	public void setNum_fone(Integer num_fone) {
		this.num_fone = num_fone;
	}

	public Date getData_cad() {
		return data_cad;
	}

	public void setData_cad(Date data_cad) {
		this.data_cad = data_cad;
	}

	@Override
	public String toString() {
		return "Telefone [cod_fone=" + cod_fone + ", cod_tipo=" + cod_tipo + ", ddd_fone=" + ddd_fone + ", num_fone="
				+ num_fone + ", data_cad=" + data_cad + "]";
	}
	
		
	
}
