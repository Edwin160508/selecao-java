package com.edwin.works.api.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.edwin.works.domain.exception.NegocioException;

@Component
public class CSV {
	
	@Value("${contato.disco.raiz}")
	private String raiz;
	
	@Value("${contato.disco.diretorio-csv}")
	private String diretorio;
	
	public void savarCSV(MultipartFile file) {
		this.salvar(this.diretorio, file);
	}
	
	private void salvar(String diretorio, MultipartFile file) {
		Path diretorioPath = Paths.get(this.raiz, diretorio);
		Path arquivoPath = diretorioPath.resolve(file.getOriginalFilename());
		
		try {
			Files.createDirectories(diretorioPath);
			file.transferTo(arquivoPath.toFile());
		}catch(IOException ex) {
			throw new NegocioException("Problema ao salvar arquivo no diretório");
		}
		
		
	}

}
