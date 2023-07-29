package com.apirest.webflux.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import com.apirest.webflux.handlers.PlaylistHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
// @Configuration
public class PlaylistRouter {
	@Bean
	public RouterFunction<ServerResponse> route(PlaylistHandler handler) {
		return RouterFunctions
			.route(GET("api/v1/playlists").and(accept(MediaType.APPLICATION_JSON)), handler::getAll)
			.andRoute(GET("api/v1/playlists/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::getById)
			.andRoute(POST("api/v1/playlists").and(accept(MediaType.APPLICATION_JSON)), handler::create);
	}
}
