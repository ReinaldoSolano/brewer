package com.algaworks.brewer.storage.local;

import java.io.File;
import java.io.IOException;
import static java.nio.file.FileSystems.getDefault;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.brewer.storage.FotoStorage;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

public class FotoStorageLocal implements FotoStorage {

	private static final Logger logger = LoggerFactory.getLogger(FotoStorageLocal.class);
	private Path local;
	private Path localTemporario;

	public FotoStorageLocal() {
		this.local = getDefault().getPath(System.getenv("HOME"), ".brewerfotos");
		criarPastas();

	}

	@Override
	public String salvarTemporariamente(MultipartFile[] files) {
		String novoNome = null;
		if (files != null && files.length > 0) {

			MultipartFile arquivo = files[0];

			novoNome = renomearArquivo(arquivo.getOriginalFilename());

			try {
				arquivo.transferTo(new File(
						this.localTemporario.toAbsolutePath().toString() + getDefault().getSeparator() + novoNome));
			} catch (IOException e) {
				throw new RuntimeException("Erro salvando a foto na pasta temporária", e);
			}

		}
		return novoNome;
	}

	@Override
	public byte[] recuperarFotoTemporaria(String nome) {
		try {
			return Files.readAllBytes(this.localTemporario.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro lendo a foto temporária");
		}
	}

	@Override
	public void salvar(String foto) {
		try {
			Files.move(this.localTemporario.resolve(foto), this.local.resolve(foto));
		} catch (IOException e) {
			throw new RuntimeException("erro ao mover a foto para o diretório final", e);
		}
		try {
			Thumbnails.of(this.local.resolve(foto).toString()).size(40, 68).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			throw new RuntimeException("erro ao gerando o thumbnail", e);
		}
	}
	
	@Override
	public byte[] recuperar(String nome) {
		try {
			return Files.readAllBytes(this.local.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro lendo a foto");
		}
	}

	private void criarPastas() {
		try {
			Files.createDirectories(this.local);
			this.localTemporario = getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTemporario);
			if (logger.isDebugEnabled()) {
				logger.debug("Pastas criadas para salvar foto");
				logger.debug("Pasta default : " + this.local.toAbsolutePath());
				logger.debug("Pasta temporaria : " + this.localTemporario.toAbsolutePath());
			}
		} catch (IOException e) {
			throw new RuntimeException("Erro criando o diretório para salvar foto", e);
		}
	}

	private String renomearArquivo(String nomeOriginal) {
		String novoNome = UUID.randomUUID().toString().concat("_").concat(nomeOriginal);
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Nome Original : %s, novo nome : %s", nomeOriginal, novoNome));
		}
		return novoNome;
	}

	

}
