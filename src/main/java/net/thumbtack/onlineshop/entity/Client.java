package net.thumbtack.onlineshop.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue(value = "CLIENT")
public class Client extends User {

    private String email;

    private String address;

    private String phone;

    private Long deposit;

    @OneToMany
    @JoinColumn(name = "user_id")
    List<BasketItem> products;

}
