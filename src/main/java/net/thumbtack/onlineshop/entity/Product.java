package net.thumbtack.onlineshop.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

    private String name;

    private Long price;

    private Long count;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "product_category",
    joinColumns = {@JoinColumn(name = "product_id")},
    inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> categories;
}
