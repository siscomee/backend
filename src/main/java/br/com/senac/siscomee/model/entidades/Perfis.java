package br.com.senac.siscomee.model.entidades;

import java.io.Serializable;
import java.util.Collection;
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

/**
 *
 * @author rita.pavao
 */
@Entity
@Table(name = "perfis",  schema = "")
@NamedQueries({
    @NamedQuery(name = "Perfis.findAll", query = "SELECT p FROM Perfis p")})
public class Perfis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nm_perfil")
    private String nmPerfil;
    @Basic(optional = false)
    @Column(name = "in_ativo")
    private boolean inAtivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perfilId")
    private Collection<PerfisFuncionalidades> perfisFuncionalidadesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perfilId")
    private Collection<PerfisUsuarios> perfisUsuariosCollection;

    public Perfis() {
    }

    public Perfis(Integer id) {
        this.id = id;
    }

    public Perfis(Integer id, String nmPerfil, boolean inAtivo) {
        this.id = id;
        this.nmPerfil = nmPerfil;
        this.inAtivo = inAtivo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNmPerfil() {
        return nmPerfil;
    }

    public void setNmPerfil(String nmPerfil) {
        this.nmPerfil = nmPerfil;
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

    public Collection<PerfisUsuarios> getPerfisUsuariosCollection() {
        return perfisUsuariosCollection;
    }

    public void setPerfisUsuariosCollection(Collection<PerfisUsuarios> perfisUsuariosCollection) {
        this.perfisUsuariosCollection = perfisUsuariosCollection;
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
        if (!(object instanceof Perfis)) {
            return false;
        }
        Perfis other = (Perfis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " br.com.montreal.siscomee.model.entidades.Perfis[ id=" + id + " ]";
    }
    
}
