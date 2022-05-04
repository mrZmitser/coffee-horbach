package com.example.lab5_ultimate.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(IngredientEntity.class)
public abstract class IngredientEntity_ {

	public static volatile SingularAttribute<IngredientEntity, Double> quantity;
	public static volatile SingularAttribute<IngredientEntity, String> name;
	public static volatile SingularAttribute<IngredientEntity, Integer> id;

}

