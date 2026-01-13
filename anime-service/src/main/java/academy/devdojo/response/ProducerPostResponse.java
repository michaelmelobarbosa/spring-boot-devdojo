package academy.devdojo.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@ToString
public class ProducerPostResponse {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
}
