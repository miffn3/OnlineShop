package net.thumbtack.onlineshop.repository.iface;

import net.thumbtack.onlineshop.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
