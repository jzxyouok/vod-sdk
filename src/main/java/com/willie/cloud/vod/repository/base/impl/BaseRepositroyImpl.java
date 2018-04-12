package com.willie.cloud.vod.repository.base.impl;

import com.willie.cloud.vod.repository.base.BaseRepositroy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/************************************************
 * <p>Copyright © by whxxykj</p>
 * <p>All right reserved.</p>
 * <p>Create Author: Willie</p>
 * <p> Create Date  : 2018/4/12</p>
 * <p>Last version : 1.0</p>
 * <p>Description  : </p>
 * <p>Last Update Date:</p>
 * <p>Change Log:</p>
 **************************************************/
public class BaseRepositroyImpl<E, PK> extends SimpleJpaRepository implements BaseRepositroy {
    protected static int DEFAULT_TOTAL = 0;//默认记录总数
    protected final EntityManager entityManager;
    protected final Class<E> domainClass;

    public BaseRepositroyImpl(Class<E> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
        this.domainClass = domainClass;
    }

    /**
     * 使用jpql构造分页
     *
     * @param jpql     查询语句
     * @param pageable 分页
     * @param <E>      实体
     * @return 分页page
     */
    public <E> Page<E> buildBasePage(String jpql, Pageable pageable) {
        TypedQuery typedQuery = entityManager.createQuery(jpql, domainClass);
        typedQuery.setFirstResult(pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());
        List pageResultList = typedQuery.getResultList();

        int total = DEFAULT_TOTAL;//默认记录总数
        if (CollectionUtils.isEmpty(pageResultList)) {
            pageResultList = Collections.EMPTY_LIST;
        } else {
            TypedQuery countTypedQuery = entityManager.createQuery(jpql, domainClass);
            total = countTypedQuery.getResultList().size();
        }
        return new PageImpl<E>(pageResultList, pageable, total);
    }

    /**
     * 使用原生sql构造分页
     *
     * @param sql      查询语句
     * @param pageable 分页
     * @param <E>      实体
     * @return 分页page
     */
    public <E> Page<E> buildNativeBasePage(String sql, Pageable pageable) {
        Query typedQuery = entityManager.createNativeQuery(sql, domainClass);
        typedQuery.setFirstResult(pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());
        List pageResultList = typedQuery.getResultList();
        int total = DEFAULT_TOTAL;//默认记录总数
        if (CollectionUtils.isEmpty(pageResultList)) {
            pageResultList = Collections.EMPTY_LIST;
        } else {
            total = baseNativePageCount(sql);
        }
        return new PageImpl<E>(pageResultList, pageable, total);
    }

    /**
     * 使用原生sql统计
     *
     * @param sql 查询语句
     * @return
     */
    private int baseNativePageCount(String sql) {
        StringBuilder countSql = new StringBuilder(" select count(*) from ");
        countSql.append("(").append(sql).append(") as base");
        Query countQuery = entityManager.createNativeQuery(countSql.toString());
        Object countResult = countQuery.getSingleResult();
        BigInteger bigInteger = Objects.isNull(countResult) ? BigInteger.valueOf(0) : (BigInteger) countResult;
        return bigInteger.intValue();
    }
}
