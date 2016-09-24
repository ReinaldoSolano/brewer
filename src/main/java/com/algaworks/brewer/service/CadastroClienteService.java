package com.algaworks.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.brewer.model.Cliente;
import com.algaworks.brewer.repository.ClientesRepository;
import com.algaworks.brewer.service.exception.CpfOuCnpjJaCadastradoException;

@Service
public class CadastroClienteService {

	@Autowired
	private ClientesRepository clientesRepository;

	@Transactional
	public void salvar(Cliente cliente) {
		
		Optional<Cliente> cpfOuCnpjExistente = clientesRepository.findByCpfOuCnpj(cliente.getCpfOuCnpjSemFormatacao());
		
		if(cpfOuCnpjExistente.isPresent()){
			throw new CpfOuCnpjJaCadastradoException("CPF/ CNPJ já cadastrado");
		}
		
		clientesRepository.save(cliente);

	}

}
