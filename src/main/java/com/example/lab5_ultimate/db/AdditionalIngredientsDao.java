package com.example.lab5_ultimate.db;

import com.example.lab5_ultimate.entity.AddIngredientEntity;
import com.example.lab5_ultimate.entity.AddIngredientEntity_;
import org.hibernate.JDBCException;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import java.util.List;

/**
 * AdditionalIngredientDao is a DAO for "add_ingredients" table
 * that store data about additional free ingredients like syrups or cinnamon.
 */
public class AdditionalIngredientsDao extends AbstractIngredientDao<AddIngredientEntity> {
    public AdditionalIngredientsDao() {
        super();
    }

    @Override
    public void create(AddIngredientEntity ingredient) throws DaoException {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.persist(ingredient);
        } catch (JDBCException e) {
            throw new DaoException(e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<AddIngredientEntity> read() throws DaoException {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<AddIngredientEntity> cq = cb.createQuery(AddIngredientEntity.class);
        Root<AddIngredientEntity> ingrRoot = cq.from(AddIngredientEntity.class);
        cq.select(ingrRoot);
        EntityTransaction t = em.getTransaction();
        List<AddIngredientEntity> ingredientEntities;
        try {
            t.begin();
            TypedQuery<AddIngredientEntity> ingrsByName = em.createQuery(cq);
            ingredientEntities = ingrsByName.getResultList();
            t.commit();
        } catch (JDBCException e) {
            throw new DaoException(e.getMessage());
        } finally {
            if (t.isActive()) {
                t.rollback();
            }
            em.close();
        }
        return ingredientEntities;
    }

    @Override
    public AddIngredientEntity readById(int id) throws DaoException {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<AddIngredientEntity> cq = cb.createQuery(AddIngredientEntity.class);
        Root<AddIngredientEntity> userRoot = cq.from(AddIngredientEntity.class);
        cq.where(cb.equal(userRoot.get(AddIngredientEntity_.id), id));
        AddIngredientEntity ingr;
        try {
            TypedQuery<AddIngredientEntity> usersQuery = em.createQuery(cq);
            ingr = usersQuery.getSingleResult();
        } catch (JDBCException e) {
            throw new DaoException(e.getMessage());
        } finally {
            em.close();
        }
        return ingr;
    }

    @Override
    public void update(AddIngredientEntity ingredient) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<AddIngredientEntity> cq = cb.createCriteriaUpdate(AddIngredientEntity.class);
        Root<AddIngredientEntity> root = cq.from(AddIngredientEntity.class);
        cq.set(root.get(AddIngredientEntity_.quantity), ingredient.getQuantity())
                .where(cb.equal(root.get(AddIngredientEntity_.name), ingredient.getName()));
        updateQuery(entityManager, transaction, entityManager.createQuery(cq));
    }

    @Override
    public void delete(AddIngredientEntity ingr) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<AddIngredientEntity> cq = cb.createCriteriaDelete(AddIngredientEntity.class);
        Root<AddIngredientEntity> root = cq.from(AddIngredientEntity.class);
        cq.where(cb.equal(root.get(AddIngredientEntity_.id), ingr.getId()));
        updateQuery(entityManager, transaction, entityManager.createQuery(cq));
    }

    @Override
    public void addQuantity(int ingrId, double quantity) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<AddIngredientEntity> cq = cb.createCriteriaUpdate(AddIngredientEntity.class);
        Root<AddIngredientEntity> root = cq.from(AddIngredientEntity.class);
        cq.set(
                        root.get(AddIngredientEntity_.quantity),
                        cb.sum(root.get(AddIngredientEntity_.quantity), quantity)
                )
                .where(cb.equal(root.get(AddIngredientEntity_.id), ingrId));
        updateQuery(entityManager, transaction, entityManager.createQuery(cq));
    }

    @Override
    public void buy(int ingrId, double quantity) throws DaoException {
        addQuantity(ingrId, -quantity);
    }

    private void updateQuery(EntityManager entityManager, EntityTransaction transaction, Query query) throws DaoException {
        try {
            transaction.begin();
            query.executeUpdate();
            transaction.commit();
        } catch (JDBCException e) {
            throw new DaoException(e.getMessage());
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
        }
    }}
