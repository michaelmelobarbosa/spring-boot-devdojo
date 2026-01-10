package academy.devdojo.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor

public class Anime {
    private Long id;
    private String name;
    private static List<Anime> animes = new ArrayList<>();

    static {
        Anime a1 = new Anime(1l, "Naruto");
        Anime a2 = new Anime(2l, "Bleach");
        Anime a3 = new Anime(3l, "One Piece");
        Anime a4 = new Anime(4l, "Jujutsu Kaisen");
        animes.addAll(List.of(a1, a2, a3, a4));
    }


    public static List<Anime> listAllAnimes() {
        return animes;
    }
}
