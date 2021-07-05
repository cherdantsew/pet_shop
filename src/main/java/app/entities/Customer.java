package app.entities;

import lombok.Data;

@Data
public class Customer {

    private int id;
    private String login;
    private String password;
    private String name;
    private int age;

    public Customer(String login, String password, String name, int age) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    public Customer(int id, String login, String password, String name, int age) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.age = age;
    }

}
