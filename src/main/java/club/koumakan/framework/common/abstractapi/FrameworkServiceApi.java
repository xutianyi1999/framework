package club.koumakan.framework.common.abstractapi;

import club.koumakan.framework.common.http.PageRequestInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FrameworkServiceApi<T> {

    T findById(long id);

    T findById(long id, boolean translate);

    List<T> findByIds(List<Long> ids);

    List<T> findByIds(List<Long> ids, boolean translate);

    T save(T entity);

    List<T> saveAll(List<T> entityList);

    void deleteById(long id);

    void deleteByIds(List<Long> ids);

    void deleteByCondition(T condition);

    List<T> findAll();

    List<T> findAll(boolean translate);

    List<T> findByCondition(T condition);

    List<T> findByCondition(T condition, boolean translate);

    T findOneByCondition(T condition);

    T findOneByCondition(T condition, boolean translate);

    Page<T> findByPage(PageRequestInfo pageRequestInfo, T condition);

    Page<T> findByPage(PageRequestInfo pageRequestInfo, T condition, boolean translate);

    long count(T condition);

    boolean exists(T condition);

    void translate(T entity);
}
