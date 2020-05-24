package com.edwin.works.api.storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.edwin.works.api.model.Relatorio;
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
			lerLancamentosCSV(arquivoPath);
		}catch(IOException ex) {
			throw new NegocioException("Problema ao salvar arquivo no diretório");
		}
		
		
	}
	
	public void recebeArquivoCSV(MultipartFile file) {
		validaArquivo(file);
		savarCSV(file);		
	}
		
	private void validaArquivo(MultipartFile file) {
		if(file == null || file.isEmpty()) {
			throw new NegocioException("Arquivo inválido");
		}
	}
	
	private List<Relatorio> lerLancamentosCSV(Path filePath) {
		List<Relatorio> relatorios = new ArrayList<Relatorio>();
		//TODO: Localizar arquivo para leitura
		try(BufferedReader br = Files.newBufferedReader(filePath,
                StandardCharsets.UTF_8)){
			
		}catch(IOException ioe) {
			 ioe.printStackTrace();
		}
		return relatorios;
	}
	
	private Relatorio montaRelatorio(String [] campos) {
		
		
		return null;
	}

}
