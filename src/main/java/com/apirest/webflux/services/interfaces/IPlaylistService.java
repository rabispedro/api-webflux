package com.apirest.webflux.services.interfaces;

import com.apirest.webflux.documents.Playlist;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPlaylistService {
	Flux<Playlist> findAll();
	Mono<Playlist> findById(String id);
	Mono<Playlist> save(Playlist playlist);
}

