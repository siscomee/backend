package br.com.senac.siscomee.model.specifications;

import java.util.Date;

public interface ProdutosRet {

	Integer getId();	
	String  getNmProduto();
	Float   getVlProduto();
	String  getTpMedida();
	Integer getQtdProduto();
	Integer getTipoProdutoId();
	String  getDsTipoProduto();
	Boolean getInAtivo();
	Integer getUsuarioIdAtualiza();
	Date    getDtUltAtualiza();
}

