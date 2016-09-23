package com.algaworks.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.CervejasRepository;
import com.algaworks.brewer.service.event.cerveja.CervejaSalvaEvent;

@Service
public class CadastroCervejaService {

	@Autowired
	private CervejasRepository cervejasRepository;

	@Autowired
	ApplicationEventPublisher publisher;

	@Transactional
	public void salvar(Cerveja cerveja) {
		cervejasRepository.save(cerveja);

		publisher.publishEvent(new CervejaSalvaEvent(cerveja));
	}

}
