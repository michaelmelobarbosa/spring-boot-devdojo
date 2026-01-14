package academy.devdojo.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder

public class Producer {
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private static List<Producer> producers = new ArrayList<>();

    static {
        Producer p1 = Producer.builder().id(1L).name("Mappa").createdAt(LocalDateTime.now()).build();
        Producer p2 = Producer.builder().id(2L).name("Kyoto Animation").createdAt(LocalDateTime.now()).build();
        Producer p3 = Producer.builder().id(3L).name("Mad House").createdAt(LocalDateTime.now()).build();

        producers.addAll(List.of(p1, p2, p3));
    }


    public static List<Producer> listAllProducers() {
        return producers;
    }
}
