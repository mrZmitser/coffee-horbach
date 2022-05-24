package com.example.lab5_ultimate.db;

import com.example.lab5_ultimate.entity.UserEntity;
import com.example.lab5_ultimate.entity.UserEntity_;
import org.hibernate.JDBCException;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

public class UserDao extends CrudDao<UserEntity> {
    public UserDao() {
        super();
    }

    @Override
    public void create(UserEntity user) throws DaoException {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(user);
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

    @Override
    public List<UserEntity> read() throws DaoException {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> userRoot = cq.from(UserEntity.class);
        cq.select(userRoot);
        EntityTransaction t = em.getTransaction();
        List<UserEntity> userEntities;
        try {
            t.begin();
            TypedQuery<UserEntity> usersQuery = em.createQuery(cq);
            userEntities = usersQuery.getResultList();
            t.commit();
        } catch (JDBCException e) {
            throw new DaoException(e.getMessage());
        } finally {
            if (t.isActive()) {
                t.rollback();
            }
            em.close();
        }
        return userEntities;
    }

    @Override
    public UserEntity readById(int id) throws DaoException {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> userRoot = cq.from(UserEntity.class);
        cq.where(cb.equal(userRoot.get(UserEntity_.id), id));
        return readUserQuery(em, cq);
    }

    public UserEntity readByName(String name) throws DaoException {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> userRoot = cq.from(UserEntity.class);
        cq.where(cb.equal(userRoot.get(UserEntity_.name), name));
        return readUserQuery(em, cq);
    }

    private UserEntity readUserQuery(EntityManager em, CriteriaQuery<UserEntity> cq) throws DaoException {
        UserEntity user;
        try {
            TypedQuery<UserEntity> usersQuery = em.createQuery(cq);
            user = usersQuery.getSingleResult();
        } catch (JDBCException e) {
            throw new DaoException(e.getMessage());
        } finally {
            em.close();
        }
        return user;
    }

    @Override
    public void update(UserEntity user) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<UserEntity> cq = cb.createCriteriaUpdate(UserEntity.class);
        Root<UserEntity> root = cq.from(UserEntity.class);
        cq.set(root.get(UserEntity_.account), user.getAccount())
                .where(cb.equal(root.get(UserEntity_.name), user.getName()));
        updateQuery(entityManager, transaction, entityManager.createQuery(cq));
    }

    @Override
    public void delete(UserEntity user) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<UserEntity> cq = cb.createCriteriaDelete(UserEntity.class);
        Root<UserEntity> root = cq.from(UserEntity.class);
        cq.where(cb.equal(root.get(UserEntity_.id), user.getId()));

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

    public void spendMoney(int id, double cost) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<UserEntity> cq = cb.createCriteriaUpdate(UserEntity.class);
        Root<UserEntity> root = cq.from(UserEntity.class);
        cq.set(
                        root.get(UserEntity_.account),
                        cb.sum(root.get(UserEntity_.account), -cost)
                )
                .where(cb.equal(root.get(UserEntity_.id), id));
        updateQuery(entityManager, transaction, entityManager.createQuery(cq));
    }
}
