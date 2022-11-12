package com.adi.corkproject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "groups")
public class UserGroup {
    public enum GROUP_TYPE{
        STUDENT, TEACHER
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private GROUP_TYPE groupType;

    @OneToMany(mappedBy = "userGroup")
    @JsonManagedReference
    private List<User> user = new ArrayList<>();

    public UserGroup(GROUP_TYPE groupType, List<User> user) {
        this.groupType = groupType;
        this.user = user;
    }

    public UserGroup(GROUP_TYPE groupType) {
        this.groupType = groupType;
    }

    public UserGroup() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGroup userGroup = (UserGroup) o;
        return id.equals(userGroup.id) && groupType == userGroup.groupType && user.equals(userGroup.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupType, user);
    }

    @Override
    public String toString() {
        return "GroupType: " + groupType;
    }
}
