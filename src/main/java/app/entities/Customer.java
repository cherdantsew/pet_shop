package app.entities;

import java.util.Objects;

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

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}
