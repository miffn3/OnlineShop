package net.thumbtack.onlineshop.entity;

import java.util.List;

public class Administrator extends User {

    private String position;

    public Administrator() {
    }

    public Administrator(int id, String firstName, String lastName, String patronymic, String login, String password,  String role, String position) {
        super(id, firstName, lastName, patronymic, login, password, role);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", patronymic='" + getPatronymic() + '\'' +
                ", login='" + getLogin() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", position='" + getPosition() + '\'' +
                '}';
    }

}
