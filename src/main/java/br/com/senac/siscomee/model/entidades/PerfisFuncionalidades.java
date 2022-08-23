package br.com.senac.siscomee.model.entidades;

import java.io.Serializable;
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
@Table(name = "perfis_funcionalidades",  schema = "")
@NamedQueries({
    @NamedQuery(name = "PerfisFuncionalidades.findAll", query = "SELECT p FROM PerfisFuncionalidades p")})
public class PerfisFuncionalidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "funcionalidade_id", referencedColumnName = "id")
    @ManyToOne
    private Funcionalidades funcionalidadeId;
    @JoinColumn(name = "perfil_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Perfis perfilId;

    public PerfisFuncionalidades() {
    }

    public PerfisFuncionalidades(Integer id) {
        this.id = id;
    }

    public PerfisFuncionalidades(Integer id, short tpAcesso) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Funcionalidades getFuncionalidadeId() {
        return funcionalidadeId;
    }

    public void setFuncionalidadeId(Funcionalidades funcionalidadeId) {
        this.funcionalidadeId = funcionalidadeId;
    }

    public Perfis getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Perfis perfilId) {
        this.perfilId = perfilId;
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
        if (!(object instanceof PerfisFuncionalidades)) {
            return false;
        }
        PerfisFuncionalidades other = (PerfisFuncionalidades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.montreal.siscomee.model.PerfisFuncionalidades[ id=" + id + " ]";
    }
    
}
