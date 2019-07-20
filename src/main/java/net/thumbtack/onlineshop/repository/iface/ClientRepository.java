package net.thumbtack.onlineshop.repository.iface;

import net.thumbtack.onlineshop.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
}
