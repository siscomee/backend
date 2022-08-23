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

/**
 *
 * @author rita.pavao
 */
@Entity
@Table(name = "usuarios",  schema = "")
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nm_usuario")
    private String nmUsuario;
    @Basic(optional = false)
    @Column(name = "in_ativo")
    private boolean inAtivo;
    @Basic(optional = false)
    @Column(name = "ds_email")
    private String dsEmail;
    @Basic(optional = false)
    @Column(name = "ds_login")
    private String dsLogin;
    @Column(name = "ds_senha")
    private String dsSenha;
    @Basic(optional = false)
    @Column(name = "dt_ult_atualiza")
    private Date dtUltAtualiza;
    @Basic(optional = false)
    @Column(name = "usuario_id_atualiza")
    private int usuarioIdAtualiza;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioId")
    private Collection<PerfisUsuarios> perfisUsuariosCollection;

    public Usuarios() {
    }

    public Usuarios(Integer id) {
        this.id = id;
    }

    public Usuarios(Integer id, String nmUsuario, boolean inAtivo, String dsEmail, String dsLogin, Date dtUltAtualiza, int usuarioIdAtualiza) {
        this.id = id;
        this.nmUsuario = nmUsuario;
        this.inAtivo = inAtivo;
        this.dsEmail = dsEmail;
        this.dsLogin = dsLogin;
        this.dtUltAtualiza = dtUltAtualiza;
        this.usuarioIdAtualiza = usuarioIdAtualiza;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public boolean getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(boolean inAtivo) {
        this.inAtivo = inAtivo;
    }

    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    public String getDsLogin() {
        return dsLogin;
    }

    public void setDsLogin(String dsLogin) {
        this.dsLogin = dsLogin;
    }

    public String getDsSenha() {
        return dsSenha;
    }

    public void setDsSenha(String dsSenha) {
        this.dsSenha = dsSenha;
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
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.montreal.siscomee.model.entidades.Usuarios[ id=" + id + " ]";
    }
    
}
