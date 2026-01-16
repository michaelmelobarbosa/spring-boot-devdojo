package academy.devdojo.controller;

import academy.devdojo.domain.Anime;
import academy.devdojo.mapper.AnimeMapper;
import academy.devdojo.request.AnimePostRequest;
import academy.devdojo.request.AnimePutRequest;
import academy.devdojo.response.AnimeGetResponse;
import academy.devdojo.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("v1/animes")
@Slf4j
@RequiredArgsConstructor
public class AnimeController {
    private final AnimeMapper mapper;
    private final AnimeService service;

    @GetMapping()
    public ResponseEntity<List<AnimeGetResponse>> listAll(@RequestParam(required = false) String name) {
        log.debug("Request received to list all animes: '{}'", name);

        var animes = service.findAll(name);
        var animeGetResponses = mapper.toAnimeGetResponseList(animes);

        return ResponseEntity.ok(animeGetResponses);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> findById(@PathVariable Long id) {
        log.debug("Request to find any anime by id: '{}'", id);

        var anime = service.findByIdOrThrowNotFound(id);
        var AnimeGetResponse = mapper.toAnimeGetResponse(anime);

        return ResponseEntity.ok(AnimeGetResponse);
    }

    @PostMapping
    public ResponseEntity<AnimeGetResponse> save(@RequestBody AnimePostRequest animePostRequest) {
        log.debug("Request to save Anime: '{}'", animePostRequest);
        var anime = mapper.toAnime(animePostRequest);
        var animeSaved = service.save(anime);
        var animeGetResponse = mapper.toAnimeGetResponse(animeSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(animeGetResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deteleById(@PathVariable Long id) {
        log.debug("Request to delete Anime by id: '{}'", id);

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<Void> update(@RequestBody AnimePutRequest animePutRequest) {
        log.debug("Request to update Anime: '{}'", animePutRequest);

        Anime animeToUpdate = mapper.toAnime(animePutRequest);

        service.update(animeToUpdate);
        return ResponseEntity.noContent().build();
    }
}
