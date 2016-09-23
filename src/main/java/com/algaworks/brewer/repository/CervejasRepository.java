package com.algaworks.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.helper.cerveja.CervejasRepositoryQueries;

public interface CervejasRepository extends JpaRepository<Cerveja, Long>, CervejasRepositoryQueries {

}
