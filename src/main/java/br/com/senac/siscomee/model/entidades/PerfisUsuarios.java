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
 * @author tiago.nogueira
 */
@Entity
@Table(name = "perfis_usuarios",  schema = "")
@NamedQueries({
    @NamedQuery(name = "PerfisUsuarios.findAll", query = "SELECT p FROM PerfisUsuarios p")})
public class PerfisUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "dt_ult_atualiza")
    private Date dtUltAtualiza;
    @Basic(optional = false)
    @Column(name = "usuario_id_atualiza")
    private int usuarioIdAtualiza;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuarios usuarioId;
    @JoinColumn(name = "perfil_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Perfis perfilId;

    public PerfisUsuarios() {
    }

    public PerfisUsuarios(Integer id) {
        this.id = id;
    }

    public PerfisUsuarios(Integer id, Date dtUltAtualiza, int usuarioIdAtualiza) {
        this.id = id;
        this.dtUltAtualiza = dtUltAtualiza;
        this.usuarioIdAtualiza = usuarioIdAtualiza;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDtUltAtualiza() {
        return dtUltAtualiza;
    }

    public void setDtUltAtualiza(Date dtUltAtualiza) {
        this.dtUltAtualiza = dtUltAtualiza;
    }

    public int getUsuarioIdAtualiza() {
        return usuarioIdAtualiza;
    }

    public void setUsuarioIdAtualiza(int usuarioIdAtualiza) {
        this.usuarioIdAtualiza = usuarioIdAtualiza;
    }

    public Usuarios getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuarios usuarioId) {
        this.usuarioId = usuarioId;
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
        if (!(object instanceof PerfisUsuarios)) {
            return false;
        }
        PerfisUsuarios other = (PerfisUsuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.montreal.siscomee.model.PerfisUsuarios[ id=" + id + " ]";
    }
    
}
