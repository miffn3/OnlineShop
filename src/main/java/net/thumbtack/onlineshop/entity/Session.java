package net.thumbtack.onlineshop.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Session {

    @Id
    private String cookie;

    @Column(name = "user_Id")
    private Long userId;
}
