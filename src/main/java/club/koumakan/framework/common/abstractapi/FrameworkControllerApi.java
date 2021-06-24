package club.koumakan.framework.common.abstractapi;

import club.koumakan.framework.common.http.MsgResult;
import club.koumakan.framework.common.http.PageRequestInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FrameworkControllerApi<T> {

    MsgResult<T> findById(long id);

    MsgResult<List<T>> findByIds(List<Long> ids);

    MsgResult<T> save(T entity);

    MsgResult<List<T>> saveAll(List<T> entityList);

    MsgResult<Void> deleteById(long id);

    MsgResult<Void> deleteByIds(List<Long> ids);

    MsgResult<Void> deleteByCondition(T condition);

    MsgResult<List<T>> findAll();

    MsgResult<List<T>> findByCondition(T condition);

    MsgResult<T> findOneByCondition(T condition);

    MsgResult<Page<T>> findByPage(PageRequestInfo<T> pageRequestInfo);

    MsgResult<Long> count(T condition);

    MsgResult<Boolean> exists(T condition);
}
