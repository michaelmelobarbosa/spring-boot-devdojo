package academy.devdojo.anime;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AnimePostRequest {
    @NotBlank(message = "the field 'name' is required")
    private String name;
}
