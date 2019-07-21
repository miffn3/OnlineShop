package net.thumbtack.onlineshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy="cart")
    private Set<Items> items;

}
