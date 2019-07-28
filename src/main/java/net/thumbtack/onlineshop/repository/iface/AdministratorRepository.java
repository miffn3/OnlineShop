package net.thumbtack.onlineshop.repository.iface;

import net.thumbtack.onlineshop.entity.Administrator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministratorRepository extends CrudRepository<Administrator, Long> {
    Optional<Administrator> findByLogin(String login);
}
