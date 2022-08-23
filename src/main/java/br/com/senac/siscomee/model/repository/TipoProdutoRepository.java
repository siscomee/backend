package br.com.senac.siscomee.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senac.siscomee.model.entidades.TiposProdutos;


@Repository
public interface TipoProdutoRepository extends JpaRepository<TiposProdutos, Integer> {

        Page<TiposProdutos> findAll(Pageable pageable);
        
        List<TiposProdutos> findByDsTipoProduto(String dsTipoProduto);

       
        Page<TiposProdutos> findByInAtivo(short inAtivo, Pageable paging);

       
        Page<TiposProdutos> findByInAtivoAndDsTipoProdutoContaining(short inAtivo, String dsTipoProduto, Pageable paging);

        
        Page<TiposProdutos> findByDsTipoProdutoContaining(String dsTipoProduto, Pageable paging);


}
