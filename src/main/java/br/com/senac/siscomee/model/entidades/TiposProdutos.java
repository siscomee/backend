package br.com.senac.siscomee.model.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 *
 * @author rita.pavao
 */
@Entity
@Table(name = "tipos_produtos",  schema = "")
@NamedQueries({
    @NamedQuery(name = "TiposProdutos.findAll", query = "SELECT tp FROM TiposProdutos tp")})
public class TiposProdutos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ds_tipo_produto")
    private String dsTipoProduto;
    @Basic(optional = false)
    @Column(name = "in_ativo")
    private short inAtivo;
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoProdutoId")
    private Collection<Produtos> ProdutosCollection;
    @Basic(optional = false)
    @Column(name = "usuario_id_atualiza")
    private int usuarioIdAtualiza;
    @Basic(optional = false)
    @Column(name = "dt_ult_atualiza")
    private Date dtUltAtualiza;
    
    public Collection<Produtos> getProdutosCollection() {
		return ProdutosCollection;
	}

	public void setProdutosCollection(Collection<Produtos> produtosCollection) {
		ProdutosCollection = produtosCollection;
	}

    public TiposProdutos() {
    }

    public TiposProdutos(Integer id) {
        this.id = id;
    }

    public TiposProdutos(Integer id, String dsTipoProduto, short inAtivo, int usuarioIdAtualiza, Date dtUltAtualiza) {
        this.id = id;
        this.dsTipoProduto = dsTipoProduto;
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

    public String getDsTipoProduto() {
        return dsTipoProduto;
    }

    public void setDsTipoProduto(String dsTipoProduto) {
        this.dsTipoProduto = dsTipoProduto;
    }

    public short getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(short inAtivo) {
        this.inAtivo = inAtivo;  
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
        if (!(object instanceof TiposProdutos)) {
            return false;
        }
        TiposProdutos other = (TiposProdutos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.montreal.siscomee.model.entidades.TiposProdutos[ id=" + id + " ]";
    }
    
}
