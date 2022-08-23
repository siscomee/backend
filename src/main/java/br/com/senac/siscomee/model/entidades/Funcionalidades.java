package br.com.senac.siscomee.model.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author rita.pavao
 */
@Entity
@Table(name = "funcionalidades",  schema = "")
@NamedQueries({
    @NamedQuery(name = "Funcionalidades.findAll", query = "SELECT f FROM Funcionalidades f")})
public class Funcionalidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nm_funcionalidade")
    private String nmFuncionalidade;
    @Basic(optional = false)
    @Column(name = "in_menu")
    private boolean inMenu;
    @Column(name = "tx_url")
    private String txUrl;
    @Basic(optional = false)
    @Column(name = "in_ativo")
    private boolean inAtivo;
    @OneToMany(mappedBy = "funcionalidadeId")
    private Collection<PerfisFuncionalidades> perfisFuncionalidadesCollection;
    @OneToMany(mappedBy = "funcionalidadeId")
    private Collection<Menus> menusCollection;
    
    public Funcionalidades() {
    }

    public Funcionalidades(Integer id) {
        this.id = id;
    }

    public Funcionalidades(Integer id, String nmFuncionalidade, boolean inMenu, boolean inAtivo) {
        this.id = id;
        this.nmFuncionalidade = nmFuncionalidade;
        this.inMenu = inMenu;
        this.inAtivo = inAtivo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNmFuncionalidade() {
        return nmFuncionalidade;
    }

    public void setNmFuncionalidade(String nmFuncionalidade) {
        this.nmFuncionalidade = nmFuncionalidade;
    }

    public boolean getInMenu() {
        return inMenu;
    }

    public void setInMenu(boolean inMenu) {
        this.inMenu = inMenu;
    }

    public String getTxUrl() {
        return txUrl;
    }

    public void setTxUrl(String txUrl) {
        this.txUrl = txUrl;
    }

    public boolean getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(boolean inAtivo) {
        this.inAtivo = inAtivo;
    }

    public Collection<PerfisFuncionalidades> getPerfisFuncionalidadesCollection() {
        return perfisFuncionalidadesCollection;
    }

    public void setPerfisFuncionalidadesCollection(Collection<PerfisFuncionalidades> perfisFuncionalidadesCollection) {
        this.perfisFuncionalidadesCollection = perfisFuncionalidadesCollection;
    }

    public Collection<Menus> getMenusCollection() {
        return menusCollection;
    }

    public void setMenusCollection(Collection<Menus> menusCollection) {
        this.menusCollection = menusCollection;
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
        if (!(object instanceof Funcionalidades)) {
            return false;
        }
        Funcionalidades other = (Funcionalidades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " br.com.montreal.siscomee.model.entidades.Funcionalidades[ id=" + id + " ]";
    }
    
}
