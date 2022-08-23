package br.com.senac.siscomee.model.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author rita.pavao
 */
@Entity
@Table(name = "menus",  schema = "")
@NamedQueries({
    @NamedQuery(name = "Menus.findAll", query = "SELECT m FROM Menus m")})
public class Menus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nm_menu")
    private String nmMenu;
    @Basic(optional = false)
    @Column(name = "nu_ordem")
    private int nuOrdem;
    @Basic(optional = false)
    @Column(name = "in_ativo")
    private boolean inAtivo;
    @JoinColumn(name = "funcionalidade_id", referencedColumnName = "id")
    @ManyToOne
    private Funcionalidades funcionalidadeId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "menus1")
    private Menus menus;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Menus menus1;

    public Menus() {
    }

    public Menus(Integer id) {
        this.id = id;
    }

    public Menus(Integer id, String nmMenu, int nuOrdem, boolean inAtivo ) {
        this.id = id;
        this.nmMenu = nmMenu;
        this.nuOrdem = nuOrdem;
        this.inAtivo = inAtivo;
    }

    public String getNmMenu() {
        return nmMenu;
    }

    public void setNmMenu(String nmMenu) {
        this.nmMenu = nmMenu;
    }
    
    public int getNuOrdem() {
        return nuOrdem;
    }

    public void setNuOrdem(int nuOrdem) {
        this.nuOrdem = nuOrdem;
    }

    public boolean getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(boolean inAtivo) {
        this.inAtivo = inAtivo;
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

    public Menus getMenus() {
        return menus;
    }

    public void setMenus(Menus menus) {
        this.menus = menus;
    }

    public Menus getMenus1() {
        return menus1;
    }

    public void setMenus1(Menus menus1) {
        this.menus1 = menus1;
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
        if (!(object instanceof Menus)) {
            return false;
        }
        Menus other = (Menus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.montreal.siscomee.model.entidades.Menus[ id=" + id + " ]";
    }
    
}
