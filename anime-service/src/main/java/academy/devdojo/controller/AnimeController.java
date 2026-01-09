package academy.devdojo.controller;

import academy.devdojo.domain.Anime;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/animes")
public class AnimeController {


    @GetMapping("list")
    public List<Anime> listAll(){
        return Anime.listAllAnimes();
    }

    @GetMapping("filter")
    public List<Anime> filterName(@RequestParam String name){

        return Anime.listAllAnimes()
                .stream()
                .filter(anime -> anime.getName().equalsIgnoreCase(name))
                .toList();
    }

    @GetMapping("{id}")
    public List<Anime> filterid(@PathVariable Long id){

        return Anime.listAllAnimes()
                .stream()
                .filter(anime -> anime.getId().equals(id))
                .toList();
    }
}
