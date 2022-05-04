package com.example.lab5_ultimate.entity;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;

@NamedQueries({
        @NamedQuery(name="allDrinks", query = "SELECT d FROM DrinkEntity d"),
        @NamedQuery(name="updateDrink", query = "UPDATE DrinkEntity d SET d.cost=?1 WHERE d.name=?2"),
//        @NamedQuery(name="drinkIngrs",query="SELECT new IngredientEntity(i.id, i.name, r.quantity) FROM IngredientEntity i JOIN ReceiptEntity r " +
//                "on i.id = r.ingredientId WHERE r.drinkId=?1"),
        @NamedQuery(name="drinkIdByName", query = "SELECT d.id FROM DrinkEntity d WHERE d.name=?1")
})
@Entity
@Table(name = "drinks", schema = "public", catalog = "coffee")
public class DrinkEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "cost")
    private double cost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DrinkEntity that = (DrinkEntity) o;

        if (id != that.id) return false;
        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + Double.hashCode(cost);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DrinkEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("cost=" + cost)
                .toString();
    }
}
