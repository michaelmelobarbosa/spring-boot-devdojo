package academy.devdojo.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class AnimeGetResponse {
    private Long id;
    private String name;

}
