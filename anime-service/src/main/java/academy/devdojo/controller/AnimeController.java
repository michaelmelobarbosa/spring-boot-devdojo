package academy.devdojo.controller;

import academy.devdojo.domain.Anime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@RestController
@RequestMapping("v1/animes")
@Slf4j
public class AnimeController {


    @GetMapping()
    public List<Anime> listAll(@RequestParam(required = false) String name){
        List<Anime> animes = Anime.listAllAnimes();
        if(name == null) return animes;

        return Anime.listAllAnimes()
                .stream()
                .filter(anime -> anime.getName().equalsIgnoreCase(name))
                .toList();
    }

    @GetMapping("{id}")
    public Anime findById(@PathVariable Long id){

        return Anime.listAllAnimes()
                .stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst().orElse(null);
    }

    @PostMapping
    public Anime save(@RequestBody Anime anime){
        anime.setId(ThreadLocalRandom.current().nextLong(100_000));
        Anime.listAllAnimes().add(anime);
        return anime;
    }
}
