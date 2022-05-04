package com.example.lab5_ultimate.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.TypeDef;

import java.util.Objects;
import java.util.StringJoiner;

@NamedQueries({
        @NamedQuery(name = "allUsers", query = "SELECT u FROM UserEntity u"),
        @NamedQuery(name = "userByName", query = "SELECT u FROM UserEntity u WHERE u.name=?1"),
        @NamedQuery(name = "updateUser", query = "UPDATE UserEntity u SET u.account=?1 WHERE u.name=?2"),
        @NamedQuery(name = "spendMoney", query = "UPDATE UserEntity u SET u.account=u.account-?1 WHERE u.id=?2")
})
@Entity
@Table(name = "users", schema = "public", catalog = "coffee")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "account")
    private double account;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "role")
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + Double.hashCode(account);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("account=" + account)
                .toString();
    }
}
