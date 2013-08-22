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

  public void clear()
  {
    entityManager.clear();
  }

  public <T> T create(final T entity)
  {
    Objects.requireNonNull(entity);

    entityManager.persist(entity);
    return entity;
  }

  protected <T> CriteriaQuery<T> createCriteriaQuery(
      final Class<T> entityBeanType, final CriteriaBuilder builder)
  {
    Objects.requireNonNull(builder);

    return builder.createQuery(entityBeanType);
  }

  protected <T> TypedQuery<T> createQuery(final CriteriaQuery<T> criteriaQuery)
  {
    Objects.requireNonNull(criteriaQuery);

    return entityManager.createQuery(criteriaQuery);
  }

  protected Query createQuery(final String qlString)
  {
    Objects.requireNonNull(qlString);

    return entityManager.createQuery(qlString);
  }

  public <T> T delete(final Class<T> entityBeanType, final long id)
  {
    return delete(findById(entityBeanType, id));
  }

  public <T> T delete(final T entity)
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

  public <E> E find(final Class<E> clazz, final long id)
  {
    return entityManager.find(clazz, id);
  }

  public <T> List<T> findAll(final Class<T> entityBeanType)
  {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<T> query = builder.createQuery(entityBeanType);

    return entityManager.createQuery(query.select(query.from(entityBeanType)))
        .getResultList();
  }

  public <T> T findById(final Class<T> entityBeanType, final Long id)
  {
    Objects.requireNonNull(id);

    return entityManager.find(entityBeanType, id);
  }

  public void flush()
  {
    entityManager.flush();
  }

  public <T> T refresh(final T entity)
  {
    Objects.requireNonNull(entity);

    entityManager.refresh(entity);
    return entity;
  }

  public <T> T update(final T entity)
  {
    Objects.requireNonNull(entity);

    return entityManager.merge(entity);
  }
}
