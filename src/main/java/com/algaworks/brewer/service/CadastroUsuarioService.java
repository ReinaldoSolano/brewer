package com.algaworks.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.repository.UsuariosRepository;
import com.algaworks.brewer.service.exception.EmailJaCadastradoException;
import com.algaworks.brewer.service.exception.SenhaObrigatoriaNovoUsuarioException;

@Service
public class CadastroUsuarioService {
	@Autowired
	UsuariosRepository usuarioRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Transactional
	public void salvar(Usuario usuario) {

		Optional<Usuario> emailExistente = usuarioRepository.findByEmail(usuario.getEmail());

		if (emailExistente.isPresent()) {
			throw new EmailJaCadastradoException("E-mail já cadastrado");
		}

		if (usuario.isNovo() && StringUtils.isEmpty((usuario.getSenha()))) {
			throw new SenhaObrigatoriaNovoUsuarioException("Senha é obrigatória para um novo usuário");
		}
		if (usuario.isNovo()) {
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			usuario.setConfirmacaoSenha(usuario.getSenha());
		}
		usuarioRepository.save(usuario);
	}
}
