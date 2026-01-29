package academy.devdojo.commons;

import academy.devdojo.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserUtils {

    public List<User> newUserList() {

        var joao = User.builder().id(1L).firstName("João").lastName("Silva").email("joao@gmail.com").build();
        var ana = User.builder().id(2L).firstName("Ana").lastName("Alves").email("ana@gmail.com").build();
        var carlos = User.builder().id(3L).firstName("Carlos").lastName("Antonio").email("carlos@gmail.com").build();

        return new ArrayList<>(List.of(joao, ana, carlos));
    }

    public User newUserToSave() {
        return User.builder().id(99L).firstName("Bruno").lastName("Barbosa").email("bruno@gmail.com").build();
    }
}
