package com.acme.javaee.dao;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@Stateless
public class DAO
{
  private static final Logger LOG = Logger.getLogger(DAO.class.getName());

  @PersistenceContext
  private EntityManager       entityManager;

  public final void clear()
  {
    entityManager.clear();
  }

  public final <T> T create(final T entity)
  {
    Objects.requireNonNull(entity);

    entityManager.persist(entity);
    return entity;
  }

  protected final <T> CriteriaQuery<T> createCriteriaQuery(
      final Class<T> entityBeanType, final CriteriaBuilder builder)
  {
    Objects.requireNonNull(builder);

    return builder.createQuery(entityBeanType);
  }

  protected final <T> TypedQuery<T> createQuery(
      final CriteriaQuery<T> criteriaQuery)
  {
    Objects.requireNonNull(criteriaQuery);

    return entityManager.createQuery(criteriaQuery);
  }

  protected final Query createQuery(final String qlString)
  {
    Objects.requireNonNull(qlString);

    return entityManager.createQuery(qlString);
  }

  public final <T> T delete(final Class<T> entityBeanType, final long id)
  {
    return delete(findById(entityBeanType, id));
  }

  public final <T> T delete(final T entity)
  {
    Objects.requireNonNull(entity);

    entityManager.remove(update(entity));
    return entity;
  }

  @PostConstruct
  public void doPostConstruct()
  {
    LOG.info("DAO: " + entityManager);
  }

  public final <E> E find(final Class<E> clazz, final long id)
  {
    return entityManager.find(clazz, id);
  }

  public final <T> List<T> findAll(final Class<T> entityBeanType)
  {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<T> query = builder.createQuery(entityBeanType);

    return entityManager.createQuery(query.select(query.from(entityBeanType)))
        .getResultList();
  }

  public final <T> T findById(final Class<T> entityBeanType, final Long id)
  {
    Objects.requireNonNull(id);

    return entityManager.find(entityBeanType, id);
  }

  public final void flush()
  {
    entityManager.flush();
  }

  public final <T> T refresh(final T entity)
  {
    Objects.requireNonNull(entity);

    entityManager.refresh(entity);
    return entity;
  }

  public final <T> T update(final T entity)
  {
    Objects.requireNonNull(entity);

    return entityManager.merge(entity);
  }
}
