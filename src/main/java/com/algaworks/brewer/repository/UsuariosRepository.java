package com.algaworks.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.repository.helper.usuario.UsuariosRepositoryQueries;

public interface UsuariosRepository extends JpaRepository<Usuario, Long>, UsuariosRepositoryQueries{

	Optional<Usuario> findByEmail(String email);

}
