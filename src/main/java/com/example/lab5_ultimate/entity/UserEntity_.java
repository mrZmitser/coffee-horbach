package com.example.lab5_ultimate.entity;

import javax.annotation.processing.Generated;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserEntity.class)
public abstract class UserEntity_ {

    public static volatile SingularAttribute<UserEntity, String> name;
    public static volatile SingularAttribute<UserEntity, String> password;
    public static volatile SingularAttribute<UserEntity, Integer> id;
    public static volatile SingularAttribute<UserEntity, Double> account;

}

