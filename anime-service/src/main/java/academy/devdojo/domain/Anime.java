package academy.devdojo.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder

public class Anime {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private static List<Anime> animes = new ArrayList<>();

    static {
        Anime naruto = Anime.builder().id(1L).name("Naruto").createdAt(LocalDateTime.now()).build();
        Anime bleach = Anime.builder().id(2L).name("Bleach").createdAt(LocalDateTime.now()).build();
        Anime onePiece = Anime.builder().id(3L).name("One Piece").createdAt(LocalDateTime.now()).build();
        Anime jujutsuKaisen = Anime.builder().id(4L).name("Jujutsu Kaisen").createdAt(LocalDateTime.now()).build();
        animes.addAll(List.of(naruto, bleach, onePiece, jujutsuKaisen));
    }


    public static List<Anime> listAllAnimes() {
        return animes;
    }
}
