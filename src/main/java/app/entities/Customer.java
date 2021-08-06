package app.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Customer {
    private int id;
    private String login;
    private String password;
    private String name;
    private int age;
    private String isBlocked;
    private String type;
}
