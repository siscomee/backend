package br.com.senac.siscomee.model.dto;

import java.io.Serializable;
import java.util.Date;

public class ProdutosDTO implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer id;
    private String nmProduto;
	private float vlProduto;
	private String tpMedida;
	private Integer qtdProduto;
	private short inAtivo;
	private Integer tipoProdutoId;
	private String dsTipoProduto;
	private int usuarioIdAtualiza;
	private Date dtUltAtualiza;
	
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
	public Integer getTipoProdutoId() {
		return tipoProdutoId;
	}
	public void setTipoProdutoId(Integer tipoProdutoId) {
		this.tipoProdutoId = tipoProdutoId;
	}
	
	public String getDsTipoProduto() {
		return dsTipoProduto;
	}
	public void setDsTipoProduto(String dsTipoProduto) {
		this.dsTipoProduto = dsTipoProduto;
	}
	public void setVlProduto(float vlProduto) {
		this.vlProduto = vlProduto;
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
	 
}
