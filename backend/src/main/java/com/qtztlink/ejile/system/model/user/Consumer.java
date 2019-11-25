package com.qtztlink.ejile.system.model.user;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Consumer implements User, Serializable {
    private static final long serialVersionUID = 3502095783428429745L;

    public interface UserView { }

    public interface AdminView extends UserView { }

    @JsonView(UserView.class)
    private Integer id;

    @JsonView(UserView.class)
    private String username;

    @JsonView(AdminView.class)
    private String password;

    @JsonView(UserView.class)
    private String telephone;

    @JsonView(UserView.class)
    private String email;

    @JsonView(UserView.class)
    private String sex;

    @JsonView(UserView.class)
    private Integer age;

    @JsonView(UserView.class)
    private String address;

    @JsonView(UserView.class)
    private Double balance;
}
