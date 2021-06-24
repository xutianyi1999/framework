package club.koumakan.framework.common.abstractapi.impl;

import club.koumakan.framework.common.abstractapi.FrameworkControllerApi;
import club.koumakan.framework.common.abstractapi.FrameworkEntityApi;
import club.koumakan.framework.common.abstractapi.FrameworkServiceApi;
import club.koumakan.framework.common.http.MsgResult;
import club.koumakan.framework.common.http.PageRequestInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public abstract class FrameworkControllerApiImpl<T extends FrameworkEntityApi> implements FrameworkControllerApi<T> {

    private final FrameworkServiceApi<T> service;

    public FrameworkControllerApiImpl(FrameworkServiceApi<T> service) {
        this.service = service;
    }

    @Override
    @GetMapping("/findById")
    @ResponseBody
    @ApiOperation("根据ID查找单条记录")
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
    @ResponseBody
    @ApiOperation("根据ID查找多条记录")
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
    @ResponseBody
    @ApiOperation("保存记录")
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
    @ResponseBody
    @ApiOperation("保存多条记录")
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
    @ResponseBody
    @ApiOperation("根据ID删除记录")
    public MsgResult<Void> deleteById(long id) {
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
    @ResponseBody
    @ApiOperation("根据ID删除多条记录")
    public MsgResult<Void> deleteByIds(@RequestBody List<Long> ids) {
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
    @ResponseBody
    @ApiOperation("根据条件删除记录")
    public MsgResult<Void> deleteByCondition(@RequestBody T condition) {
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
    @ResponseBody
    @ApiOperation("查找全部记录")
    public MsgResult<List<T>> findAll() {
        try {
            return MsgResult.success(service.findAll(true));
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error();
        }
    }

    @Override
    @PostMapping("/findByCondition")
    @ResponseBody
    @ApiOperation("根据条件查找记录")
    public MsgResult<List<T>> findByCondition(@RequestBody T condition) {
        try {
            return MsgResult.success(service.findByCondition(condition, true));
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error();
        }
    }

    @Override
    @PostMapping("/findOneByCondition")
    @ResponseBody
    @ApiOperation("根据条件查找一条记录")
    public MsgResult<T> findOneByCondition(@RequestBody T condition) {
        try {
            return MsgResult.success(service.findOneByCondition(condition, true));
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error();
        }
    }

    @Override
    @PostMapping("/findByPage")
    @ResponseBody
    @ApiOperation("分页查询记录")
    public MsgResult<Page<T>> findByPage(@RequestBody PageRequestInfo<T> pageRequestInfo) {
        try {
            return MsgResult.success(service.findByPage(pageRequestInfo, true));
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error();
        }
    }

    @Override
    @PostMapping("/count")
    @ResponseBody
    @ApiOperation("统计")
    public MsgResult<Long> count(@RequestBody T condition) {
        try {
            return MsgResult.success(service.count(condition));
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error();
        }
    }

    @Override
    @PostMapping("/exists")
    @ResponseBody
    @ApiOperation("判断记录是否存在")
    public MsgResult<Boolean> exists(@RequestBody T condition) {
        try {
            return MsgResult.success(service.exists(condition));
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error();
        }
    }
}
