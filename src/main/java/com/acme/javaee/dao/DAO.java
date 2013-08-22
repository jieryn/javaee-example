package com.acme.javaee.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.acme.javaee.domain.Model;

@Stateless
public class DAO<T extends Model>
{
  private final Class<T> entityBeanType;

  @PersistenceContext
  private EntityManager  entityManager;

  @SuppressWarnings("unchecked")
  protected DAO()
  {
    super();

    entityBeanType = (Class<T>) ((ParameterizedType) getClass()
        .getGenericSuperclass()).getActualTypeArguments()[0];
  }

  public void clear()
  {
    entityManager.clear();
  }

  public T create(final T entity)
  {
    Objects.requireNonNull(entity);

    entityManager.persist(entity);
    return entity;
  }

  protected CriteriaQuery<T> createCriteriaQuery(final CriteriaBuilder builder)
  {
    Objects.requireNonNull(builder);

    return builder.createQuery(entityBeanType);
  }

  protected TypedQuery<T> createQuery(final CriteriaQuery<T> criteriaQuery)
  {
    Objects.requireNonNull(criteriaQuery);

    return entityManager.createQuery(criteriaQuery);
  }

  protected Query createQuery(final String qlString)
  {
    Objects.requireNonNull(qlString);

    return entityManager.createQuery(qlString);
  }

  public T delete(final Long id)
  {
    return delete(findById(id));
  }

  public T delete(final T entity)
  {
    Objects.requireNonNull(entity);

    entityManager.remove(update(entity));
    return entity;
  }

  public <E> E find(final Class<E> clazz, final Long id)
  {
    return entityManager.find(clazz, id);
  }

  public List<T> findAll()
  {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<T> query = builder.createQuery(entityBeanType);

    return entityManager.createQuery(query.select(query.from(entityBeanType)))
        .getResultList();
  }

  public T findById(final Long id)
  {
    Objects.requireNonNull(id);

    return entityManager.find(entityBeanType, id);
  }

  public void flush()
  {
    entityManager.flush();
  }

  public T refresh(final T entity)
  {
    Objects.requireNonNull(entity);

    entityManager.refresh(entity);
    return entity;
  }

  public T update(final T entity)
  {
    Objects.requireNonNull(entity);

    return entityManager.merge(entity);
  }
}
