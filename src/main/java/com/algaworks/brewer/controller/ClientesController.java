package com.algaworks.brewer.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.model.Cliente;
import com.algaworks.brewer.model.domain.TipoPessoa;
import com.algaworks.brewer.repository.EstadosRepository;
import com.algaworks.brewer.service.CadastroClienteService;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

	private static final Logger logger = LoggerFactory.getLogger(ClientesController.class);

	@Autowired
	private EstadosRepository estadosRepository;

	//@Autowired
	//private CadastroClienteService cadastroClienteService;

	@RequestMapping("/novo")
	public ModelAndView novo(Cliente cliente) {
		ModelAndView mv = new ModelAndView("cliente/CadastroCliente");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("estados", estadosRepository.findAll());
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cliente);
		}
		//cadastroClienteService.salvar(cliente);
		//logger.info("cliente salvo com sucesso");
		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");

		return new ModelAndView("redirect:/clientes/novo");
	}

}
