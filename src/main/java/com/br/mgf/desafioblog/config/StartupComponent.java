package com.br.mgf.desafioblog.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Slf4j
public class StartupComponent implements CommandLineRunner {
	private final StorageProperties storageProps;

	public StartupComponent(StorageProperties storageProps) {
		this.storageProps = storageProps;
	}

	@Override
	public void run(String... args) {
		Path folderPath = Paths.get(storageProps.getPath(), storageProps.getFolder());

		if (Files.exists(folderPath)) {
			log.info("Folder already exists: " + folderPath);
			return;
		}

		try {
			Files.createDirectory(folderPath);
			log.info("Folder created: " + folderPath);
		} catch (IOException e) {
			log.error("Error creating folder: " + e.getMessage());
		}
	}
}