package club.koumakan.framework.common.abstractapi.impl;

import club.koumakan.framework.common.abstractapi.FrameworkEntityApi;
import club.koumakan.framework.common.abstractapi.FrameworkServiceApi;
import club.koumakan.framework.common.http.PageRequestInfo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public abstract class FrameworkServiceApiImpl<T extends FrameworkEntityApi> implements FrameworkServiceApi<T> {

    private final JpaRepository<T, Long> dao;

    public FrameworkServiceApiImpl(JpaRepository<T, Long> dao) {
        this.dao = dao;
    }

    @Override
    public T findById(long id) {
        return findById(id, false);
    }

    @Override
    public T findById(long id, boolean translate) {
        T entity = dao.findById(id).orElse(null);

        if (translate) {
            translate(entity);
        }
        return entity;
    }

    @Override
    public List<T> findByIds(List<Long> ids) {
        return findByIds(ids, false);
    }

    @Override
    public List<T> findByIds(List<Long> ids, boolean translate) {
        List<T> entityList = dao.findAllById(ids);

        if (translate) {
            for (T entity : entityList) {
                translate(entity);
            }
        }
        return entityList;
    }

    @Override
    public T save(T entity) {
        return dao.save(entity);
    }

    @Override
    @Transactional
    public List<T> saveAll(List<T> entityList) {
        ArrayList<T> list = new ArrayList<>(entityList.size());

        for (T entity : entityList) {
            list.add(dao.save(entity));
        }

        dao.flush();
        return list;
    }

    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByIds(List<Long> ids) {
        for (Long id : ids) {
            deleteById(id);
        }
        dao.flush();
    }

    @Override
    @Transactional
    public void deleteByCondition(T condition) {
        List<T> entityList = dao.findAll(Example.of(condition));

        for (T entity : entityList) {
            deleteById(entity.getId());
        }
        dao.flush();
    }

    @Override
    public List<T> findAll() {
        return findAll(false);
    }

    @Override
    public List<T> findAll(boolean translate) {
        List<T> entityList = dao.findAll();

        if (translate) {
            for (T entity : entityList) {
                translate(entity);
            }
        }
        return entityList;
    }

    @Override
    public List<T> findByCondition(T condition) {
        return findByCondition(condition, false);
    }

    @Override
    public List<T> findByCondition(T condition, boolean translate) {
        List<T> entityList = dao.findAll(Example.of(condition));

        if (translate) {
            for (T entity : entityList) {
                translate(entity);
            }
        }
        return entityList;
    }

    @Override
    public T findOneByCondition(T condition) {
        return findOneByCondition(condition, false);
    }

    @Override
    public T findOneByCondition(T condition, boolean translate) {
        T t = dao.findOne(Example.of(condition)).orElse(null);

        if (translate) {
            translate(t);
        }
        return t;
    }

    @Override
    public Page<T> findByPage(PageRequestInfo<T> pageRequestInfo) {
        return findByPage(pageRequestInfo, false);
    }

    @Override
    public Page<T> findByPage(PageRequestInfo<T> pageRequestInfo, boolean translate) {
        Direction direction;

        if (PageRequestInfo.ASC.equalsIgnoreCase(pageRequestInfo.getDirection())) {
            direction = Direction.ASC;
        } else {
            direction = Direction.DESC;
        }

        PageRequest pageRequest = PageRequest.of(
                pageRequestInfo.getPageNum(),
                pageRequestInfo.getPageSize(),
                direction,
                pageRequestInfo.getFieldName()
        );

        Page<T> page = conditionPage(pageRequest, pageRequestInfo.getCondition());

        if (translate) {
            for (T entity : page.getContent()) {
                translate(entity);
            }
        }
        return page;
    }

    public Page<T> conditionPage(PageRequest pageRequest, T condition) {
        Page<T> page;

        if (condition != null) {
            page = dao.findAll(Example.of(condition), pageRequest);
        } else {
            page = dao.findAll(pageRequest);
        }
        return page;
    }

    @Override
    public long count(T condition) {
        return dao.count(Example.of(condition));
    }

    @Override
    public boolean exists(T condition) {
        return dao.exists(Example.of(condition));
    }
}
