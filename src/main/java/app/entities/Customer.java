package app.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Customer {

    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_CUSTOMER = "CUSTOMER";
    public static final String TYPE_BLOCKED = "Y";
    public static final String TYPE_UNBLOCKED = "N";

    private int id;
    private String login;
    private String password;
    private String name;
    private int age;
    private String isBlocked;
    private String type;
}
