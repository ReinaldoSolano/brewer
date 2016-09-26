package com.algaworks.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.brewer.model.Cidade;
import com.algaworks.brewer.repository.CidadesRepository;
import com.algaworks.brewer.service.exception.CidadeExistenteException;

@Service
public class CadastroCidadeService {
	@Autowired
	CidadesRepository cidadesRepository;

	@Transactional
	public void salvar(Cidade cidade) {

		Optional<Cidade> cidadeExistente = cidadesRepository.findByNomeAndEstado(cidade.getNome(), cidade.getEstado());
		if (cidadeExistente.isPresent()) {
			throw new CidadeExistenteException("Cidade já cadastrada");
		}
		cidadesRepository.save(cidade);

	}

}
