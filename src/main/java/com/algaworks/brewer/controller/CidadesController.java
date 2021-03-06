package com.algaworks.brewer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.controller.page.PageWrapper;
import com.algaworks.brewer.model.Cidade;
import com.algaworks.brewer.repository.CidadesRepository;
import com.algaworks.brewer.repository.EstadosRepository;
import com.algaworks.brewer.repository.filter.CidadeFilter;
import com.algaworks.brewer.service.CadastroCidadeService;
import com.algaworks.brewer.service.exception.CidadeExistenteException;

@Controller
@RequestMapping("/cidades")
public class CidadesController {

	@Autowired
	private CidadesRepository cidadesRepository;

	@Autowired
	private EstadosRepository estadosRepository;

	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	@GetMapping("/nova")
	public ModelAndView novo(Cidade cidade) {
		ModelAndView mv = new ModelAndView("cidade/CadastroCidade");
		mv.addObject("estados", estadosRepository.findAll());
		return mv;
	}

	@PostMapping("/nova")
	// @CacheEvict(value = "cidades", key = "#cidade.estado.codigo", condition =
	// "cidade.temEstado()")
	public ModelAndView nova(@Valid Cidade cidade, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(cidade);
		}

		try {
			cadastroCidadeService.salvar(cidade);
		} catch (CidadeExistenteException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(cidade);
		}
		attributes.addFlashAttribute("mensagem", "Cidade salva com sucesso!");
		return new ModelAndView("redirect:/cidades/nova");
	}

	// @Cacheable(value = "cidades", key = "#codigoEstado")
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> pesquisarPorCodigoEstado(
			@RequestParam(name = "estado", defaultValue = "-1") Long codigoEstado) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		return cidadesRepository.findByEstadoCodigo(codigoEstado);
	}

	@GetMapping
	public ModelAndView pesquisar(CidadeFilter cidadeFilter, BindingResult result,
			@PageableDefault(size = 8) Pageable pageable, HttpServletRequest httpServletRequest) {

		ModelAndView mv = new ModelAndView("cidade/PesquisaCidades");
		mv.addObject("estados", estadosRepository.findAll());

		PageWrapper<Cidade> paginaWrapper = new PageWrapper<>(cidadesRepository.filtrar(cidadeFilter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);

		return mv;

	}

}
