package club.koumakan.framework.common.abstractapi.impl;

import club.koumakan.framework.common.abstractapi.FrameworkDAOApi;
import club.koumakan.framework.common.abstractapi.FrameworkEntityApi;
import club.koumakan.framework.common.abstractapi.FrameworkServiceApi;
import club.koumakan.framework.common.http.PageRequestInfo;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public abstract class FrameworkServiceApiImpl<
        T extends FrameworkEntityApi,
        DAO extends JpaRepository<T, Long> & FrameworkDAOApi> implements FrameworkServiceApi<T> {

    private final DAO dao;

    public FrameworkServiceApiImpl(DAO dao) {
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
        T newEntity = entity;

        if (entity.getId() != null) {
            T dbEntity = dao.findById(entity.getId()).get();
            BeanUtil.copyProperties(entity, dbEntity, CopyOptions.create());
            newEntity = dbEntity;
        }

        return dao.save(newEntity);
    }

    @Override
    public List<T> saveAll(List<T> entityList) {
        ArrayList<T> newEntityList = new ArrayList<>(entityList.size());

        for (T entity : entityList) {
            T newEntity = entity;

            if (entity.getId() != null) {
                T dbEntity = dao.findById(entity.getId()).get();
                BeanUtil.copyProperties(entity, dbEntity, CopyOptions.create());
                newEntity = dbEntity;
            }
            newEntityList.add(newEntity);
        }

        return dao.saveAll(newEntityList);
    }

    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        dao.deleteAllByIdIn(ids);
    }

    @Override
    public void deleteByCondition(T condition) {
        List<T> entityList = dao.findAll(Example.of(condition));
        dao.deleteAll(entityList);
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
        translate(t);
        return t;
    }

    @Override
    public Page<T> findByPage(PageRequestInfo pageRequestInfo, T condition) {
        return findByPage(pageRequestInfo, condition, false);
    }

    @Override
    public Page<T> findByPage(PageRequestInfo pageRequestInfo, T condition, boolean translate) {
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

        Page<T> page;

        if (condition != null) {
            page = dao.findAll(Example.of(condition), pageRequest);
        } else {
            page = dao.findAll(pageRequest);
        }

        if (translate) {
            for (T entity : page.getContent()) {
                translate(entity);
            }
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
