package club.koumakan.framework.common.abstractapi.impl;

import club.koumakan.framework.common.abstractapi.FrameworkControllerApi;
import club.koumakan.framework.common.abstractapi.FrameworkDAOApi;
import club.koumakan.framework.common.abstractapi.FrameworkEntityApi;
import club.koumakan.framework.common.http.MsgResult;
import club.koumakan.framework.common.http.PageRequestInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public abstract class FrameworkControllerApiImpl<T extends FrameworkEntityApi,
        DAO extends JpaRepository<T, Long> & FrameworkDAOApi> implements FrameworkControllerApi<T> {

    private final FrameworkServiceApiImpl<T, DAO> service;

    public FrameworkControllerApiImpl(FrameworkServiceApiImpl<T, DAO> service) {
        this.service = service;
    }

    @Override
    @GetMapping("/findById")
    public MsgResult<T> findById(long id) {
        try {
            return MsgResult.success(service.findById(id, true));
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error();
        }
    }

    @Override
    @GetMapping("/findByIds")
    public MsgResult<List<T>> findByIds(List<Long> ids) {
        try {
            return MsgResult.success(service.findByIds(ids, true));
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error();
        }
    }

    @Override
    @PostMapping("/save")
    public MsgResult<T> save(@RequestBody T entity) {
        try {
            return MsgResult.success(service.save(entity));
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error();
        }
    }

    @Override
    @PostMapping("/saveAll")
    public MsgResult<List<T>> saveAll(@RequestBody List<T> entityList) {
        try {
            return MsgResult.success(service.saveAll(entityList));
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error();
        }
    }

    @Override
    @PostMapping("/deleteById")
    public MsgResult<T> deleteById(long id) {
        try {
            service.deleteById(id);
            return MsgResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error();
        }
    }

    @Override
    @PostMapping("/deleteByIds")
    public MsgResult<T> deleteByIds(@RequestBody List<Long> ids) {
        try {
            service.deleteByIds(ids);
            return MsgResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error();
        }
    }

    @Override
    @PostMapping("/deleteByCondition")
    public MsgResult<T> deleteByCondition(@RequestBody T condition) {
        try {
            service.deleteByCondition(condition);
            return MsgResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error();
        }
    }

    @Override
    @GetMapping("/findAll")
    public MsgResult<List<T>> findAll() {
        try {
            return MsgResult.success(service.findAll(true));
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error();
        }
    }

    @Override
    @GetMapping("/findByCondition")
    public MsgResult<List<T>> findByCondition(T condition) {
        try {
            return MsgResult.success(service.findByCondition(condition, true));
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error();
        }
    }

    @Override
    @GetMapping("/findByPage")
    public MsgResult<Page<T>> findByPage(PageRequestInfo pageRequestInfo, T condition) {
        try {
            return MsgResult.success(service.findByPage(pageRequestInfo, condition, true));
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error();
        }
    }
}
