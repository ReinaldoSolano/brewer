package com.algaworks.brewer.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.service.CadastroUsuarioService;

@RequestMapping("/usuarios")
@Controller
public class UsuariosController {

	private static final Logger logger = LoggerFactory.getLogger(UsuariosController.class);

	@Autowired
	CadastroUsuarioService cadastroUsuarioService;

	@RequestMapping("/novo")
	public ModelAndView novo(Usuario usario) {
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Usuario usuario, BindingResult result, Model model,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(usuario);
		}

		cadastroUsuarioService.salvar(usuario);
		logger.info("Usuário salvo com sucesso!");
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");

		return new ModelAndView("redirect:/usuarios/novo");
	}

}
