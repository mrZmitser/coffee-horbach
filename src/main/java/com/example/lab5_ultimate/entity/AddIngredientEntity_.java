package com.example.lab5_ultimate.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AddIngredientEntity.class)
public abstract class AddIngredientEntity_ {

	public static volatile SingularAttribute<AddIngredientEntity, Double> quantity;
	public static volatile SingularAttribute<AddIngredientEntity, String> name;
	public static volatile SingularAttribute<AddIngredientEntity, Integer> id;

}

