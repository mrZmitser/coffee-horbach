package com.example.lab5_ultimate.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ReceiptEntity.class)
public abstract class ReceiptEntity_ {

    public static volatile SingularAttribute<ReceiptEntity, Double> quantity;
    public static volatile SingularAttribute<ReceiptEntity, Integer> id;
    public static volatile SingularAttribute<ReceiptEntity, IngredientEntity> ingredientsByIngredientId;
    public static volatile SingularAttribute<ReceiptEntity, DrinkEntity> drinksByDrinkId;

}

