package net.thumbtack.onlineshop.entity;

import java.util.List;

public class Client extends User {

    private String email;
    private String address;
    private String phone;
    private Integer deposit;

    public Client() {
    }

    public Client(Integer id, String firstName, String lastName, String patronymic, String login, String password, String role, String email, String address, String phone, Integer deposit) {
        super(id, firstName, lastName, patronymic, login, password, role);
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.deposit = deposit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", patronymic='" + getPatronymic() + '\'' +
                ", login='" + getLogin() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", deposit='" + getDeposit() + '\'' +
                '}';
    }
}
