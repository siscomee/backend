package br.com.senac.siscomee.model.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 *
 * @author rita.pavao
 */
@Entity
@Table(name = "produtos",  schema = "")
@NamedQueries({
    @NamedQuery(name = "Produtos.findAll", query = "SELECT p FROM Produtos p")})
public class Produtos implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nm_produto")
    private String nmProduto;
    @Basic(optional = false)
    @Column(name = "vl_produto")
    private float vlProduto;
    @Basic(optional = false)
    @Column(name = "tp_medida")
    private String tpMedida;
    @Basic(optional = false)
    @Column(name = "qtd_produto")
    private Integer qtdProduto;
    @Basic(optional = false)
    @Column(name = "in_ativo")
    private short inAtivo;
   // @JsonManagedReference
    @JoinColumn(name = "tipo_produto_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TiposProdutos tipoProdutoId;
    @Basic(optional = false)
    @Column(name = "usuario_id_atualiza")
    private int usuarioIdAtualiza;
    @Basic(optional = false)
    @Column(name = "dt_ult_atualiza")
    private Date dtUltAtualiza;
  
    public Produtos() {
    }

    public Produtos(Integer id) {
        this.id = id;
    }

    public Produtos(Integer id, String nmProduto, float vlProduto, String tpMedida, Integer qtdProduto, short inAtivo, int usuarioIdAtualiza, Date dtUltAtualiza) {
        this.id = id;
        this.nmProduto = nmProduto;
        this.vlProduto = vlProduto;
        this.tpMedida = tpMedida;
        this.qtdProduto = qtdProduto;
        this.inAtivo = inAtivo;
        this.usuarioIdAtualiza = usuarioIdAtualiza;
        this.dtUltAtualiza = dtUltAtualiza;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNmProduto() {
		return nmProduto;
	}

	public void setNmProduto(String nmProduto) {
		this.nmProduto = nmProduto;
	}

	public Float getVlProduto() {
		return vlProduto;
	}

	public void setVlProduto(Float vlProduto) {
		this.vlProduto = vlProduto;
	}

	public String getTpMedida() {
		return tpMedida;
	}

	public void setTpMedida(String tpMedida) {
		this.tpMedida = tpMedida;
	}

	public Integer getQtdProduto() {
		return qtdProduto;
	}

	public void setQtdProduto(Integer qtdProduto) {
		this.qtdProduto = qtdProduto;
	}

	public short getInAtivo() {
		return inAtivo;
	}

	public void setInAtivo(short inAtivo) {
		this.inAtivo = inAtivo;
	}

	public TiposProdutos getTipoProdutoId() {
		return tipoProdutoId;
	}

	public void setTipoProdutoId(TiposProdutos tipoProdutoId) {
		this.tipoProdutoId = tipoProdutoId;
	}

	public int getUsuarioIdAtualiza() {
		return usuarioIdAtualiza;
	}

	public void setUsuarioIdAtualiza(int usuarioIdAtualiza) {
		this.usuarioIdAtualiza = usuarioIdAtualiza;
	}

	public Date getDtUltAtualiza() {
		return dtUltAtualiza;
	}

	public void setDtUltAtualiza(Date dtUltAtualiza) {
		this.dtUltAtualiza = dtUltAtualiza;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produtos)) {
            return false;
        }
        Produtos other = (Produtos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.montreal.siscoint.model.entidades.Produtos[ id=" + id + " ]";
    }
    
}
