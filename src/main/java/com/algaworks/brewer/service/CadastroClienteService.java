package com.algaworks.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.brewer.model.Cliente;
import com.algaworks.brewer.repository.ClientesRepository;

public class CadastroClienteService {
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	@Transactional
	public void salvar(Cliente cliente) {
		clientesRepository.save(cliente);

	}

}
