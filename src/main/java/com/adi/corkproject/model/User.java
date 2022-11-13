package com.adi.corkproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    private static int userCount;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String email;
    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name="group_id")
    @JsonBackReference
    private UserGroup userGroup;

    public User(String email, String username, String password, UserGroup userGroup) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.userGroup = userGroup;
    }

    public User(Long id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    @PostConstruct
    private void increaseUserCount(){
        userCount++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && email.equals(user.email) && username.equals(user.username) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password);
    }

    @Override
    public String toString() {
        String userGroup = this.getUserGroup().getGroupType() == UserGroup.GROUP_TYPE.STUDENT ? "STUDENT" : "TEACHER";
        return "User(" + userCount +") email: " + email +", username: " + username + ", group: " + userGroup;
    }
}

