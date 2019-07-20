package net.thumbtack.onlineshop.repository.iface;

import net.thumbtack.onlineshop.entity.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {
}

