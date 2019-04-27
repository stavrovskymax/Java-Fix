package ru.ivmiit.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivmiit.service.models.Token;

import java.util.Optional;

public interface TokensRepository extends JpaRepository<Token, Long> {
    Optional<Token> findOneByValue(String value);;
}
