package com.example.shiro.common.service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.UpdateChainWrapper;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tianhao
 * @2019/12/9 0009 20:26
 */

public interface IService<T> {
    boolean save(T entity);

    @Transactional(
            rollbackFor = {Exception.class}
    )
    default boolean saveBatch(Collection<T> entityList) {
        return this.saveBatch(entityList, 1000);
    }

    boolean saveBatch(Collection<T> entityList, int batchSize);

    @Transactional(
            rollbackFor = {Exception.class}
    )
    default boolean saveOrUpdateBatch(Collection<T> entityList) {
        return this.saveOrUpdateBatch(entityList, 1000);
    }

    boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize);

    boolean removeById(Serializable id);

    boolean removeByMap(Map<String, Object> columnMap);

    boolean remove(Wrapper<T> queryWrapper);

    boolean removeByIds(Collection<? extends Serializable> idList);

    boolean updateById(T entity);

    boolean update(T entity, Wrapper<T> updateWrapper);

    default boolean update(Wrapper<T> updateWrapper) {
        return this.update((T)null, updateWrapper);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    default boolean updateBatchById(Collection<T> entityList) {
        return this.updateBatchById(entityList, 1000);
    }

    boolean updateBatchById(Collection<T> entityList, int batchSize);

    boolean saveOrUpdate(T entity);

    T getById(Serializable id);

    Collection<T> listByIds(Collection<? extends Serializable> idList);

    Collection<T> listByMap(Map<String, Object> columnMap);

    default T getOne(Wrapper<T> queryWrapper) {
        return this.getOne(queryWrapper, true);
    }

    T getOne(Wrapper<T> queryWrapper, boolean throwEx);

    Map<String, Object> getMap(Wrapper<T> queryWrapper);

    <V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper);

    int count(Wrapper<T> queryWrapper);

    default int count() {
        return this.count(Wrappers.emptyWrapper());
    }

    List<T> list(Wrapper<T> queryWrapper);

    default List<T> list() {
        return this.list(Wrappers.emptyWrapper());
    }

    IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper);

    default IPage<T> page(IPage<T> page) {
        return this.page(page, Wrappers.emptyWrapper());
    }

    List<Map<String, Object>> listMaps(Wrapper<T> queryWrapper);

    default List<Map<String, Object>> listMaps() {
        return this.listMaps(Wrappers.emptyWrapper());
    }

    default List<Object> listObjs() {
        return this.listObjs(Function.identity());
    }

    default <V> List<V> listObjs(Function<? super Object, V> mapper) {
        return this.listObjs(Wrappers.emptyWrapper(), mapper);
    }

    default List<Object> listObjs(Wrapper<T> queryWrapper) {
        return this.listObjs(queryWrapper, Function.identity());
    }

    <V> List<V> listObjs(Wrapper<T> queryWrapper, Function<? super Object, V> mapper);

    IPage<Map<String, Object>> pageMaps(IPage<T> page, Wrapper<T> queryWrapper);

    default IPage<Map<String, Object>> pageMaps(IPage<T> page) {
        return this.pageMaps(page, Wrappers.emptyWrapper());
    }

    BaseMapper<T> getBaseMapper();

    default QueryChainWrapper<T> query() {
        return new QueryChainWrapper(this.getBaseMapper());
    }

    default LambdaQueryChainWrapper<T> lambdaQuery() {
        return new LambdaQueryChainWrapper(this.getBaseMapper());
    }

    default UpdateChainWrapper<T> update() {
        return new UpdateChainWrapper(this.getBaseMapper());
    }

    default LambdaUpdateChainWrapper<T> lambdaUpdate() {
        return new LambdaUpdateChainWrapper(this.getBaseMapper());
    }

    default boolean saveOrUpdate(T entity, Wrapper<T> updateWrapper) {
        return this.update(entity, updateWrapper) || this.saveOrUpdate(entity);
    }
}
