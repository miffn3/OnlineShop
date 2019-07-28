package net.thumbtack.onlineshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "parent_name")
    private String parentName;

}
