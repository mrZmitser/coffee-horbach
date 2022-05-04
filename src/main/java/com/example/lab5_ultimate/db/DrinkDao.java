package com.example.lab5_ultimate.db;

import com.example.lab5_ultimate.entity.*;
import org.hibernate.JDBCException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import java.util.List;
import java.util.stream.Collectors;

public class DrinkDao extends CrudDao<DrinkEntity> {
    public DrinkDao() {
        super();
    }

    private int idByName(String name) throws DaoException {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DrinkEntity> cq = cb.createQuery(DrinkEntity.class);
        Root<DrinkEntity> root = cq.from(DrinkEntity.class);
        cq.where(cb.equal(root.get(DrinkEntity_.name), name));
        DrinkEntity drink;
        try {
            TypedQuery<DrinkEntity> ingrsQuery = em.createQuery(cq);
            drink = ingrsQuery.getSingleResult();
        } catch (JDBCException e) {
            throw new DaoException(e.getMessage());
        } finally {
            em.close();
        }
        return drink.getId();
    }

    @Override
    public void create(DrinkEntity drink) throws DaoException {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            em.persist(drink);
        } catch (JDBCException e) {
            throw new DaoException(e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<DrinkEntity> read() throws DaoException {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DrinkEntity> cq = cb.createQuery(DrinkEntity.class);
        Root<DrinkEntity> root = cq.from(DrinkEntity.class);
        cq.select(root);
        EntityTransaction t = em.getTransaction();
        List<DrinkEntity> addDrinksEntities;
        try {
            t.begin();
            TypedQuery<DrinkEntity> drinksByName = em.createQuery(cq);
            addDrinksEntities = drinksByName.getResultList();
            t.commit();
        } catch (JDBCException e) {
            throw new DaoException(e.getMessage());
        } finally {
            if (t.isActive()) {
                t.rollback();
            }
            em.close();
        }
        return addDrinksEntities;
    }

    public List<IngredientEntity> getIngredients(String name) throws DaoException {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ReceiptEntity> cq = cb.createQuery(ReceiptEntity.class);
        Root<ReceiptEntity> root = cq.from(ReceiptEntity.class);
        Join<ReceiptEntity, DrinkEntity> receiptDrinkJoin = root.join(ReceiptEntity_.drinksByDrinkId);
        cq.select(root).where(cb.equal(receiptDrinkJoin.get(DrinkEntity_.name), name));
        EntityTransaction t = em.getTransaction();
        List<IngredientEntity> ingrs;
        try {
            t.begin();
            TypedQuery<ReceiptEntity> ingrsQuery = em.createQuery(cq);
            ingrs = ingrsQuery.getResultList().stream().map(receiptEntity -> {
                        IngredientEntity ingr = receiptEntity.getIngredientsByIngredientId();
                        return new IngredientEntity(ingr.getId(), ingr.getName(), receiptEntity.getQuantity());
                    }
            ).collect(Collectors.toList());
            t.commit();
        } catch (JDBCException e) {
            throw new DaoException(e.getMessage());
        } finally {
            if (t.isActive()) {
                t.rollback();
            }
            em.close();
        }
        return ingrs;
    }

    @Override
    public DrinkEntity readById(int id) throws DaoException {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DrinkEntity> cq = cb.createQuery(DrinkEntity.class);
        Root<DrinkEntity> userRoot = cq.from(DrinkEntity.class);
        cq.where(cb.equal(userRoot.get(DrinkEntity_.id), id));
        return readDrinkQuery(em, cq);
    }

    private DrinkEntity readDrinkQuery(EntityManager em, CriteriaQuery<DrinkEntity> cq) throws DaoException {
        DrinkEntity drink;
        try {
            TypedQuery<DrinkEntity> query = em.createQuery(cq);
            drink = query.getSingleResult();
        } catch (JDBCException e) {
            throw new DaoException(e.getMessage());
        } finally {
            em.close();
        }
        return drink;
    }

    @Override
    public void update(DrinkEntity drink) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<DrinkEntity> cq = cb.createCriteriaUpdate(DrinkEntity.class);
        Root<DrinkEntity> root = cq.from(DrinkEntity.class);
        cq.set(root.get(DrinkEntity_.cost), drink.getCost())
                .where(cb.equal(root.get(DrinkEntity_.name), drink.getName()));
        updateQuery(entityManager, transaction, entityManager.createQuery(cq));
    }

    @Override
    public void delete(DrinkEntity drink) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<DrinkEntity> cq = cb.createCriteriaDelete(DrinkEntity.class);
        Root<DrinkEntity> root = cq.from(DrinkEntity.class);
        cq.where(cb.equal(root.get(DrinkEntity_.id), drink.getId()));

        EntityTransaction transaction = entityManager.getTransaction();
        updateQuery(entityManager, transaction, entityManager.createQuery(cq));
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

    public <T> void buyDrink(DrinkEntity drink, UserEntity user, AbstractIngredientDao<T> ingredientsDao, UserDao userDao) throws DaoException {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            List<IngredientEntity> ingrs = getIngredients(drink.getName());
            for (IngredientEntity ingr : ingrs) {
                ingredientsDao.buy(ingr.getId(), ingr.getQuantity());
            }
            userDao.spendMoney(user.getId(), drink.getCost());
            t.commit();
        } catch (JDBCException e) {
            throw new DaoException(e.getMessage());
        } finally {
            if (t.isActive()) {
                t.rollback();
            }
            em.close();
        }
    }
}
