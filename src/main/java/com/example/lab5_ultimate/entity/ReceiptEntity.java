package com.example.lab5_ultimate.entity;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "receipts", schema = "public", catalog = "coffee")
public class ReceiptEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
//    @Basic
//    @Column(name = "drink_id")
//    private int drinkId;
//    @Basic
//    @Column(name = "ingredient_id")
//    private int ingredientId;
    @Basic
    @Column(name = "quantity")
    private double quantity;
    @ManyToOne
    @JoinColumn(name = "drink_id", referencedColumnName = "id")
    private DrinkEntity drinksByDrinkId;
    @ManyToOne
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    private IngredientEntity ingredientsByIngredientId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
//
//    public Integer getDrinkId() {
//        return drinkId;
//    }
//
//    public void setDrinkId(Integer drinkId) {
//        this.drinkId = drinkId;
//    }
//
//    public Integer getIngredientId() {
//        return ingredientId;
//    }
//
//    public void setIngredientId(Integer ingredientId) {
//        this.ingredientId = ingredientId;
//    }

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

        ReceiptEntity that = (ReceiptEntity) o;

        if (id != that.id) return false;
        if (!drinksByDrinkId.equals(that.drinksByDrinkId)) return false;
        if (!ingredientsByIngredientId.equals(that.ingredientsByIngredientId)) return false;
        if (quantity != that.quantity) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + drinksByDrinkId.hashCode();
        result = 31 * result + ingredientsByIngredientId.hashCode();
        result = 31 * result + Double.hashCode(quantity);
        return result;
    }

    public DrinkEntity getDrinksByDrinkId() {
        return drinksByDrinkId;
    }

    public void setDrinksByDrinkId(DrinkEntity drinksByDrinkId) {
        this.drinksByDrinkId = drinksByDrinkId;
    }

    public IngredientEntity getIngredientsByIngredientId() {
        return ingredientsByIngredientId;
    }

    public void setIngredientsByIngredientId(IngredientEntity ingredientsByIngredientId) {
        this.ingredientsByIngredientId = ingredientsByIngredientId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ReceiptEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("drinkId=" + drinksByDrinkId.toString())
                .add("ingredientId=" + ingredientsByIngredientId.toString())
                .add("quantity=" + quantity)
                .toString();
    }
}
