package com.example.lab5_ultimate.db;

import com.example.lab5_ultimate.entity.IngredientEntity;
import com.example.lab5_ultimate.entity.IngredientEntity_;
import org.hibernate.JDBCException;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * IngredientDao is a DAO for "ingredients" table
 * that store data about additional free ingredients like syrups or cinnamon.
 */
public class IngredientsDao extends AbstractIngredientDao<IngredientEntity> {
    public IngredientsDao() {
        super();
    }

    @Override
    public void create(IngredientEntity ingredient) throws DaoException {
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
    public List<IngredientEntity> read() throws DaoException {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<IngredientEntity> cq = cb.createQuery(IngredientEntity.class);
        Root<IngredientEntity> ingrRoot = cq.from(IngredientEntity.class);
        cq.select(ingrRoot);
        EntityTransaction t = em.getTransaction();
        List<IngredientEntity> ingredientEntities;
        try {
            t.begin();
            TypedQuery<IngredientEntity> ingrsByName = em.createQuery(cq);
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
    public IngredientEntity readById(int id) throws DaoException {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<IngredientEntity> cq = cb.createQuery(IngredientEntity.class);
        Root<IngredientEntity> userRoot = cq.from(IngredientEntity.class);
        cq.where(cb.equal(userRoot.get(IngredientEntity_.id), id));
        IngredientEntity ingr;
        try {
            TypedQuery<IngredientEntity> usersQuery = em.createQuery(cq);
            ingr = usersQuery.getSingleResult();
        } catch (JDBCException e) {
            throw new DaoException(e.getMessage());
        } finally {
            em.close();
        }
        return ingr;
    }

    @Override
    public void update(IngredientEntity ingredient) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<IngredientEntity> cq = cb.createCriteriaUpdate(IngredientEntity.class);
        Root<IngredientEntity> root = cq.from(IngredientEntity.class);
        cq.set(root.get(IngredientEntity_.quantity), ingredient.getQuantity())
                .where(cb.equal(root.get(IngredientEntity_.name), ingredient.getName()));
        updateQuery(entityManager, transaction, entityManager.createQuery(cq));
    }

    @Override
    public void delete(IngredientEntity ingr) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<IngredientEntity> cq = cb.createCriteriaDelete(IngredientEntity.class);
        Root<IngredientEntity> root = cq.from(IngredientEntity.class);
        cq.where(cb.equal(root.get(IngredientEntity_.id), ingr.getId()));
        updateQuery(entityManager, transaction, entityManager.createQuery(cq));
    }

    @Override
    public void addQuantity(int ingrId, double quantity) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<IngredientEntity> cq = cb.createCriteriaUpdate(IngredientEntity.class);
        Root<IngredientEntity> root = cq.from(IngredientEntity.class);
        cq.set(
                        root.get(IngredientEntity_.quantity),
                        cb.sum(root.get(IngredientEntity_.quantity), quantity)
                )
                .where(cb.equal(root.get(IngredientEntity_.id), ingrId));
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
    }
}
