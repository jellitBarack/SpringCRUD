package jhonattan.testeFullStack.clientes.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipos_contato")
public class TiposContato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer cod_tipo;
	String desc_tipo;
	
	@Column(insertable = false)
	Date data_cad;

	public Integer getCod_tipo() {
		return cod_tipo;
	}

	public void setCod_tipo(Integer cod_tipo) {
		this.cod_tipo = cod_tipo;
	}

	public String getDesc_tipo() {
		return desc_tipo;
	}

	public void setDesc_tipo(String desc_tipo) {
		this.desc_tipo = desc_tipo;
	}

	public Date getData_cad() {
		return data_cad;
	}

	public void setData_cad(Date data_cad) {
		this.data_cad = data_cad;
	}
	
		
}
