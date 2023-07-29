package com.apirest.webflux.controllers;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.webflux.documents.Playlist;
import com.apirest.webflux.services.interfaces.IPlaylistService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/playlists")
public class PaylistController {
	@Autowired
	private final IPlaylistService playlistService;

	@GetMapping(path = "")
	public Flux<Playlist> getAll() {
		return this.playlistService.findAll();
	}

	@GetMapping(path = "{id}")
	public Mono<Playlist> getById(@PathVariable String id) {
		return this.playlistService.findById(id);
	}

	@PostMapping(path = "")
	public Mono<Playlist> create(@RequestBody Playlist playlist) {
		return this.playlistService.save(playlist);
	}

	@GetMapping(path = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, Playlist>> getAllByEvents() {
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(5));
		Flux<Playlist> events = this.playlistService.findAll();

		System.out.print("Event disparado");
		return Flux.zip(interval, events);
	}
}
