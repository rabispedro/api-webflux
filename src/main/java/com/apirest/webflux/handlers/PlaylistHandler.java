package com.apirest.webflux.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;


import com.apirest.webflux.documents.Playlist;
import com.apirest.webflux.services.interfaces.IPlaylistService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
// @Component
public class PlaylistHandler {
	@Autowired
	private final IPlaylistService playlistService;
	
	public Mono<ServerResponse> getAll(ServerRequest request) {
		return ServerResponse.ok()
			.contentType(MediaType.APPLICATION_JSON)
			.body(this.playlistService.findAll(), Playlist.class);
	}

	public Mono<ServerResponse> getById(ServerRequest request) {
		final String id = request.pathVariable("id");

		return ServerResponse
			.ok()
			.contentType(MediaType.APPLICATION_JSON)
			.body(this.playlistService.findById(id), Playlist.class);
	}

	public Mono<ServerResponse> create(ServerRequest request) {
		final Mono<Playlist> playlist = request.bodyToMono(Playlist.class);

		return ServerResponse
			.ok()
			.contentType(MediaType.APPLICATION_JSON)
			.body(fromPublisher(playlist.flatMap(this.playlistService::save), Playlist.class));
	}
}
