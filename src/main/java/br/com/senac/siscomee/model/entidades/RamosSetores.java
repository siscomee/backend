package br.com.senac.siscomee.model.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

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
@Table(name = "ramos_setores",  schema = "")
@NamedQueries({
    @NamedQuery(name = "RamosSetores.findAll", query = "SELECT r FROM RamosSetores r")})
public class RamosSetores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nm_ramo_setor")
    private String nmRamoSetor;
    @Basic(optional = false)
    @Column(name = "in_ativo")
    private short inAtivo;
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ramoSetorId")
    private Collection<Fornecedores> fornecedoresCollection;
    @Basic(optional = false)
    @Column(name = "usuario_id_atualiza")
    private int usuarioIdAtualiza;
    @Basic(optional = false)
    @Column(name = "dt_ult_atualiza")
    private Date dtUltAtualiza;
  

    public Collection<Fornecedores> getFornecedoresCollection() {
		return fornecedoresCollection;
	}

	public void setFornecedoresCollection(Collection<Fornecedores> fornecedoresCollection) {
		this.fornecedoresCollection = fornecedoresCollection;
	}

	public RamosSetores() {
    }

    public RamosSetores(Integer id) {
        this.id = id;
    }

    public RamosSetores(Integer id, String nmRamoSetor, short inAtivo, int usuarioIdAtualiza, Date dtUltAtualiza) {
        this.id = id;
        this.nmRamoSetor = nmRamoSetor;
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

	public String getNmRamoSetor() {
		return nmRamoSetor;
	}

	public void setNmRamoSetor(String nmRamoSetor) {
		this.nmRamoSetor = nmRamoSetor;
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
		return Objects.hash(dtUltAtualiza, id, inAtivo, nmRamoSetor, usuarioIdAtualiza);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RamosSetores other = (RamosSetores) obj;
		return Objects.equals(dtUltAtualiza, other.dtUltAtualiza) && Objects.equals(id, other.id)
				&& inAtivo == other.inAtivo && Objects.equals(nmRamoSetor, other.nmRamoSetor)
				&& usuarioIdAtualiza == other.usuarioIdAtualiza;
	}

	@Override
	public String toString() {
		return "RamoSetor [id=" + id + ", nmRamoSetor=" + nmRamoSetor + ", inAtivo=" + inAtivo + ", usuarioIdAtualiza="
				+ usuarioIdAtualiza + ", dtUltAtualiza=" + dtUltAtualiza + ", getId()=" + getId()
				+ ", getNmRamoSetor()=" + getNmRamoSetor() + ", getInAtivo()=" + getInAtivo()
				+ ", getUsuarioIdAtualiza()=" + getUsuarioIdAtualiza() + ", getDtUltAtualiza()=" + getDtUltAtualiza()
				+ ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString()
				+ "]";
	}

	
}
