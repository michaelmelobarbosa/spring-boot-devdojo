package academy.devdojo.repository;

import academy.devdojo.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserData {
    private final List<User> users = new ArrayList<>();

    {
        var michael = User.builder().id(1L).firstName("Michael").lastName("Melo").email("michael@gmail.com").build();
        var evna = User.builder().id(2L).firstName("Evna").lastName("Neres").email("evna@gmail.com").build();
        var bentinho = User.builder().id(3L).firstName("Bentinho").lastName("Doidinho").email("bentinho@gmail.com").build();
        users.addAll(List.of(michael, evna, bentinho));
    }

    public List<User> getUsers() {
        return users;
    }
}
