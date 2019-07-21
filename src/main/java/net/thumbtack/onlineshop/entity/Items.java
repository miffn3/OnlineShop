package net.thumbtack.onlineshop.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
public class Items {

    @Id
    @Column(name = "item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="cart_id", nullable=false)
    private Cart cart;

    @OneToMany
    @JoinColumn(name = "product_id")
    private Set<Product> products;

    private Long count;
}
