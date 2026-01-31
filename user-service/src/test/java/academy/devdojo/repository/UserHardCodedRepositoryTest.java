package academy.devdojo.repository;

import academy.devdojo.commons.UserUtils;
import academy.devdojo.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserHardCodedRepositoryTest {
    @InjectMocks
    private UserHardCodedRepository respository;
    @Mock
    private UserData userData;
    private List<User> usersList;
    @InjectMocks
    private UserUtils userUtils;

    @BeforeEach
    void init() {
        usersList = userUtils.newUserList();
    }

    @Test
    @DisplayName("findAll returns a list with all users")
    @Order(1)
    void findAll_ReturnsAllUsers_WhenSuccessful() {
        BDDMockito.when(userData.getUsers()).thenReturn(usersList);

        var users = respository.findAll();
        Assertions.assertThat(users).isNotNull().hasSameElementsAs(usersList);
    }

    @Test
    @DisplayName("findById returns an user with given id")
    @Order(2)
    void findById_ReturnsUserById_WhenSuccessful() {
        BDDMockito.when(userData.getUsers()).thenReturn(usersList);

        var expectedUser = usersList.getFirst();
        var users = respository.findById(expectedUser.getId());
        Assertions.assertThat(users).isPresent().contains(expectedUser);
    }

    @Test
    @DisplayName("findByName returns empty list when firstName is null")
    @Order(3)
    void findByFirstName_ReturnsEmptyList_WhenFirstNameIsNull() {
        BDDMockito.when(userData.getUsers()).thenReturn(usersList);

        var users = respository.findByFirstName(null);
        Assertions.assertThat(users).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("findByName returns list with found object when firstName exists")
    @Order(4)
    void findByFirstName_ReturnsFoundUserInList_WhenFirstNameIsFound() {
        BDDMockito.when(userData.getUsers()).thenReturn(usersList);

        var expectedUser = usersList.getFirst();
        var users = respository.findByFirstName(expectedUser.getFirstName());
        Assertions.assertThat(users).hasSize(1).contains(expectedUser);
    }

    @Test
    @DisplayName("save creates an user")
    @Order(5)
    void save_CreatesUser_WhenSuccessful() {
        BDDMockito.when(userData.getUsers()).thenReturn(usersList);

        var userToSave = userUtils.newUserToSave();
        var user = respository.save(userToSave);

        Assertions.assertThat(user).isEqualTo(userToSave).hasNoNullFieldsOrProperties();

        var userSavedOptional = respository.findById(userToSave.getId());

        Assertions.assertThat(userSavedOptional).isPresent().contains(userToSave);
    }

    @Test
    @DisplayName("delete removes an user")
    @Order(6)
    void delete_RemoveUser_WhenSuccessful() {
        BDDMockito.when(userData.getUsers()).thenReturn(usersList);

        var userToDelete = usersList.getFirst();
        respository.delete(userToDelete);

        var users = respository.findAll();

        Assertions.assertThat(users).isNotEmpty().doesNotContain(userToDelete);
    }

    @Test
    @DisplayName("update updates an user")
    @Order(7)
    void update_UpdatesUser_WhenSuccessful() {
        BDDMockito.when(userData.getUsers()).thenReturn(usersList);
        var userToUpdate = this.usersList.getFirst();
        userToUpdate.setFirstName("Ana");

        respository.update(userToUpdate);

        Assertions.assertThat(this.usersList).contains(userToUpdate);

        var userUpdatedOptional = respository.findById(userToUpdate.getId());

        Assertions.assertThat(userUpdatedOptional).isPresent();
        Assertions.assertThat(userUpdatedOptional.get().getFirstName()).isEqualTo(userToUpdate.getFirstName());
    }

}