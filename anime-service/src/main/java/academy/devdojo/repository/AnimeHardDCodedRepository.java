package academy.devdojo.repository;

import academy.devdojo.domain.Anime;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AnimeHardDCodedRepository {

    private static final List<Anime> ANIMES = new ArrayList<>();

    static {
        Anime naruto = Anime.builder().id(1L).name("Naruto").createdAt(LocalDateTime.now()).build();
        Anime bleach = Anime.builder().id(2L).name("Bleach").createdAt(LocalDateTime.now()).build();
        Anime onePiece = Anime.builder().id(3L).name("One Piece").createdAt(LocalDateTime.now()).build();
        Anime jujutsuKaisen = Anime.builder().id(4L).name("Jujutsu Kaisen").createdAt(LocalDateTime.now()).build();
        ANIMES.addAll(List.of(naruto, bleach, onePiece, jujutsuKaisen));
    }


    public List<Anime> findAll() {
        return ANIMES;
    }

    public Optional<Anime> findById(Long id) {
        return ANIMES.stream().filter(anime -> anime.getId().equals(id)).findFirst();
    }

    public List<Anime> findByName(String name) {
        return ANIMES.stream().filter(prod -> prod.getName().equalsIgnoreCase(name)).toList();
    }

    public Anime save(Anime anime) {
        ANIMES.add(anime);
        return anime;
    }

    public void delete(Anime anime) {
        ANIMES.remove(anime);
    }

    public void update(Anime anime) {
        delete(anime);
        save(anime);
    }
}
