package com.willie.cloud.vod.base.page;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

/**
 * <p>功能 描述:</p>
 * <p>创  建 人:Willie</p>
 * <p>创建 时间:2018/4/10 8:59</p>
 */
public abstract class PageUtil<E> {
    private static int DEFAULT_PAGE_NUM = 0;
    private static int DEFAULT_PAGE_SIZE = 0;

    /**
     * 取得分页
     *
     * @param typedQuery
     * @param pageable
     * @param <E>
     * @return
     */
    public static <E> Page<E> getPage(TypedQuery<E> typedQuery, Pageable pageable) {

        List pageResultList = typedQuery.getResultList();
        int page = DEFAULT_PAGE_NUM;
        int pageSize = DEFAULT_PAGE_SIZE;
        if (CollectionUtils.isEmpty(pageResultList)) {
            pageResultList = Collections.EMPTY_LIST;
        } else {
            page = pageable.getPageNumber();
            pageSize = pageable.getPageSize();
        }

        typedQuery.setFirstResult(page * pageSize);
        typedQuery.setMaxResults(pageSize);

        Page<E> videoPage = new PageImpl<E>(pageResultList, pageable, pageResultList.size());
        return videoPage;
    }
}
