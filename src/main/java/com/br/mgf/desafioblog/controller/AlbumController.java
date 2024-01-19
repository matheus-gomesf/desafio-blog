package com.br.mgf.desafioblog.controller;

import com.br.mgf.desafioblog.dto.AlbumDto;
import com.br.mgf.desafioblog.security.IAuthenticationFacade;
import com.br.mgf.desafioblog.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/albums")
public class AlbumController {

	private final AlbumService albumService;
	private final IAuthenticationFacade iAuthenticationFacade;

	@PostMapping
	public ResponseEntity<AlbumDto> createAlbum(@RequestParam("files") List<MultipartFile> files) {
		AlbumDto album = albumService.createAlbum(files, iAuthenticationFacade.getAuthentication());
		return ResponseEntity.status(HttpStatus.CREATED).body(album);
	}

	@GetMapping
	public ResponseEntity<Page<AlbumDto>> listAlbum(@PageableDefault Pageable pageable) {
		return ResponseEntity.ok(albumService.listAlbum(pageable));
	}

	@DeleteMapping("/{id}")
	public void deleteAlbum(@PathVariable  UUID id) {
		albumService.deleteAlbum(id, iAuthenticationFacade.getAuthentication());
	}
}
