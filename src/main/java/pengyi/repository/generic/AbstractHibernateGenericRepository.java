package pengyi.repository.generic;

import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;


/**
 * Author: pengyi
 * Date: 15-12-30
 * Time: 下午3:43
 */
@Repository
public abstract class AbstractHibernateGenericRepository<T, ID extends Serializable> implements IHibernateGenericRepository<T, ID> {

//    private static final String DELETE_FLAG_PROPERTY = "deleted";

    private Class<T> persistentClass;

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public AbstractHibernateGenericRepository() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    @Override
    public void clear() {
        getSession().clear();
    }

    @Override
    public void evict(Object obj) {
        getSession().evict(obj);
    }

    @Override
    public void refresh(Object obj) {
        getSession().refresh(obj);
    }

    @Override
    public void addLock() {
        getSession().createCriteria(getPersistentClass()).setLockMode(LockMode.PESSIMISTIC_WRITE);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T loadById(ID id) {
        return (T) getSession().load(getPersistentClass(), id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getById(ID id) {
        return (T) getSession().get(getPersistentClass(), id);
    }

//    @Override
//    @SuppressWarnings("unchecked")
//    public List<T> findByExample(T exampleInstance, String... excludeProperty) {
//        Criteria criteria = getSession().createCriteria(getPersistentClass());
//        Example example = Example.create(exampleInstance);
//        for (String exclude : excludeProperty) {
//            example.excludeProperty(exclude);
//        }
//        criteria.add(example);
//        return criteria.list();
//    }
//
//
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public T findByProperties(Map<String, Object> propertyMap) {
//        Criteria criteria = getSession().createCriteria(getPersistentClass());
//        for (Map.Entry<String, Object> entry : propertyMap.entrySet()) {
//            criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
//        }
//
//        Object result = criteria.uniqueResult();
//        return null == result ? null : (T)result;
//    }
//
//    @Override
//    public List<T> findAllByProperties(Map<String, Object> propertyMap) {
//        Criteria criteria = getSession().createCriteria(getPersistentClass());
//        for (Map.Entry<String, Object> entry : propertyMap.entrySet()) {
//            criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
//        }
//        return criteria.list();
//    }

    @Override
    public void flush() {
        getSession().flush();
    }

    @Override
    public void save(T entity) {
        getSession().save(entity);
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Pagination<T> pagination(int page, int pageSize, List<Criterion> criteriaList, List<Order> orderList) {
        return pagination(page, pageSize, criteriaList, orderList, null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Pagination<T> pagination(int page, int pageSize, List<Criterion> criterionList, List<Order> orderList,
                                    Map<String, FetchMode> fetchModeMap) {
        return pagination(page, pageSize, criterionList, null, orderList, fetchModeMap, null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Pagination<T> pagination(int page, int pageSize, List<Criterion> criterionList, Map<String, String> aliasMap,
                                    List<Order> orderList, Map<String, FetchMode> fetchModeMap, ProjectionList projectionList) {

        Criteria criteriaCount = getSession().createCriteria(getPersistentClass()).
                setProjection(Projections.rowCount());

        Criteria criteriaSearch = getSession().createCriteria(getPersistentClass());

        if (null != aliasMap) {
            for (Map.Entry<String, String> entry : aliasMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                criteriaCount.createAlias(key, value);
                criteriaSearch.createAlias(key, value);
            }
        }

        if (null != criterionList) {
            for (Criterion criterion : criterionList) {
                criteriaCount.add(criterion);
                criteriaSearch.add(criterion);
            }
        }

        if (null != projectionList) {
            criteriaCount.setProjection(projectionList);

            criteriaSearch.setProjection(projectionList);
        }

        if (null != orderList) {
            for (Order order : orderList) {
                criteriaSearch.addOrder(order);
            }
        }

        if (null != fetchModeMap) {
            for (Map.Entry<String, FetchMode> entry : fetchModeMap.entrySet()) {
                String key = entry.getKey();
                FetchMode value = entry.getValue();
                criteriaCount.setFetchMode(key, value);
                criteriaSearch.setFetchMode(key, value);
            }
        }

        int count = ((Long) criteriaCount.uniqueResult()).intValue();

        int firstResult = (page - 1) * pageSize;

        criteriaSearch.setFirstResult(firstResult).setMaxResults(pageSize);
        return new Pagination<T>(criteriaSearch.list(), count, page, pageSize);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getSession().createCriteria(getPersistentClass()).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> list(List<Criterion> criteria, List<Order> orders) {

        return list(criteria, orders, null, null, null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> list(List<Criterion> criteria, List<Order> orders, ProjectionList projectionList,
                        Map<String, FetchMode> fetchModeMap, Map<String, String> alias) {

        Criteria criteriaCount = getSession().createCriteria(getPersistentClass());
        criteriaCount.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (null != criteria) {
            for (Criterion criterion : criteria) {
                criteriaCount.add(criterion);
            }
        }

        if (null != orders) {
            for (Order order : orders) {
                criteriaCount.addOrder(order);
            }
        }

        if (null != projectionList) {
            criteriaCount.setProjection(projectionList);

            criteriaCount.setResultTransformer(Transformers.aliasToBean(getPersistentClass()));
        }

        if (null != fetchModeMap) {
            for (Map.Entry<String, FetchMode> entry : fetchModeMap.entrySet()) {
                criteriaCount.setFetchMode(entry.getKey(), entry.getValue());
            }
        }

        if (null != alias) {
            for (Map.Entry<String, String> entry : alias.entrySet()) {
                criteriaCount.createAlias(entry.getKey(), entry.getValue());
            }
        }

        return criteriaCount.list();
    }
}