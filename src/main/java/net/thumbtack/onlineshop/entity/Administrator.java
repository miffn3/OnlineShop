package net.thumbtack.onlineshop.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("ADMIN")
public class Administrator extends User {

    private String position;
}
