package net.thumbtack.onlineshop.repository.iface;

import net.thumbtack.onlineshop.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
}
