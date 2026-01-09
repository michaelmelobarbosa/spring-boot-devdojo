package academy.devdojo.domain;

import lombok.*;

import java.util.List;

@Setter
@Getter
@EqualsAndHashCode
@ToString

public class Anime {
    private Long id;
    private String name;
    private static List<Anime> list = List.of(
            new Anime(1l, "Naruto"),
            new Anime(2l, "Bleach"),
            new Anime(3l, "One Piece"),
            new Anime(4l, "Jujutsu Kaisen"));

    public Anime(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<Anime> listAllAnimes() {
        return list;
    }
}
