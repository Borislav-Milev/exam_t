package app.exam_t.repositories;

import app.exam_t.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByUsernameAndPassword(String username, String email);

    UserEntity findByUsername(String username);
}
