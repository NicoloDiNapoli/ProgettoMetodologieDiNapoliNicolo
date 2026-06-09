package it.unicam.cs.mpgc.rpg125668.persistence.store;

import it.unicam.cs.mpgc.rpg125668.persistence.HibernateConnection;
import jakarta.persistence.EntityManager;

import java.util.List;

/**
 * Base class for all the stores
 * it implements base methods for all entities
 * @param <T> type of the entity
 */
public abstract class BaseStore<T> {
    private final Class<T> entityClass;
    protected EntityManager entityManager;

    protected BaseStore(Class<T> entityClass) {
        if (entityClass == null) throw new IllegalArgumentException("Entity class cannot be null");
        this.entityClass = entityClass;
        this.entityManager = HibernateConnection.getEntityManager();
    }

    public void save(T entity){
        if(entity == null) throw new IllegalArgumentException("Entity cannot be null in save method");
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            entityManager.clear();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public void delete(T entity){
        if(entity == null) throw new IllegalArgumentException("Entity cannot be null in delete method");
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public void update(T entity){
        if(entity == null) throw new IllegalArgumentException("Entity cannot be null in update method");
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public T findById(Long id){
        if(id < 0) throw new IllegalArgumentException("Id cannot be negative");
        return entityManager.find(entityClass, id);
    }

    public List<T> findAll(){
        return entityManager.createQuery("FROM " + this.entityClass.getSimpleName(), entityClass).getResultList();
    }


}
