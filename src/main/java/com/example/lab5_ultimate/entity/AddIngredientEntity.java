package com.example.lab5_ultimate.entity;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;

@NamedQueries({
        @NamedQuery(name="allAddIngrs", query = "SELECT i FROM AddIngredientEntity i"),
        @NamedQuery(name="updateAddIngrQuantity", query = "UPDATE AddIngredientEntity i SET i.quantity=?1 WHERE i.name=?2"),
        @NamedQuery(name = "addAddIngrQuantity", query = "UPDATE AddIngredientEntity i SET i.quantity=i.quantity+?1 WHERE i.id=?2"),
        @NamedQuery(name = "subtractAddIngrQuantity", query = "UPDATE AddIngredientEntity i SET i.quantity=i.quantity-?1 WHERE i.id=?2")
})
@Entity
@Table(name = "add_ingredients", schema = "public", catalog = "coffee")
public class AddIngredientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "quantity")
    private double quantity;

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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddIngredientEntity that = (AddIngredientEntity) o;

        if (id != that.id) return false;
        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + Double.hashCode(quantity);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AddIngredientEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("quantity=" + quantity)
                .toString();
    }
}
