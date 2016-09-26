package com.algaworks.brewer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.controller.page.PageWrapper;
import com.algaworks.brewer.model.Cliente;
import com.algaworks.brewer.model.domain.TipoPessoa;
import com.algaworks.brewer.repository.ClientesRepository;
import com.algaworks.brewer.repository.EstadosRepository;
import com.algaworks.brewer.repository.filter.ClienteFilter;
import com.algaworks.brewer.service.CadastroClienteService;
import com.algaworks.brewer.service.exception.CpfOuCnpjJaCadastradoException;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

	private static final Logger logger = LoggerFactory.getLogger(ClientesController.class);

	@Autowired
	private EstadosRepository estadosRepository;

	@Autowired
	private ClientesRepository clientesRepository;

	@Autowired
	private CadastroClienteService cadastroClienteService;

	@RequestMapping("/novo")
	public ModelAndView novo(Cliente cliente) {
		ModelAndView mv = new ModelAndView("cliente/CadastroCliente");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("estados", estadosRepository.findAll());
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cliente);
		}
		try {
			cadastroClienteService.salvar(cliente);
		} catch (CpfOuCnpjJaCadastradoException e) {
			result.rejectValue("cpfOuCnpj", e.getMessage(), e.getMessage());
			return novo(cliente);
		}

		logger.info("cliente salvo com sucesso");
		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");

		return new ModelAndView("redirect:/clientes/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(ClienteFilter clienteFilter, BindingResult result,
			@PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {

		ModelAndView mv = new ModelAndView("cliente/PesquisaClientes");
		PageWrapper<Cliente> paginaWrapper = new PageWrapper<>(clientesRepository.filtrar(clienteFilter, pageable),httpServletRequest);
		mv.addObject("pagina", paginaWrapper);

		return mv;

	}

}
