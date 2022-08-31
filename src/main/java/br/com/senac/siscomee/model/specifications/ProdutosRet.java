package br.com.senac.siscomee.model.specifications;

import java.util.Date;

public interface ProdutosRet {

	Integer getId();	
	String  getNmProduto();
	float   getVlProduto();
	String  getTpMedida();
	Integer getQtdProduto();
	String  getInAtivo();
	Integer getUsuarioIdAtualiza();
	Date    getDtUltAtualiza();
	Integer getTipoProdutoId();
	String  getDsTipoProduto();
	
}

