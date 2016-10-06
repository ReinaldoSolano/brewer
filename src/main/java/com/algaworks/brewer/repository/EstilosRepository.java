package com.algaworks.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.brewer.model.Estilo;
import com.algaworks.brewer.repository.helper.estilo.EstiloRepositoryQueries;

public interface EstilosRepository extends JpaRepository<Estilo, Long>, EstiloRepositoryQueries {

	Optional<Estilo> findByNomeIgnoreCase(String nome);

}
