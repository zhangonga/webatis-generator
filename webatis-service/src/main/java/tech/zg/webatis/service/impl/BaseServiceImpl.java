package tech.zg.webatis.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tech.zg.webatis.entity.BaseEntity;
import tech.zg.webatis.exception.BaseException;
import tech.zg.webatis.mapper.BaseMapper;
import tech.zg.webatis.service.BaseService;

import java.io.Serializable;

/**
 * baseService 实现类
 * <p>
 *
 * @author: 张弓
 * @date:
 * @version: 1.0.0
 */
@Service("baseService")
public abstract class BaseServiceImpl<PK extends Serializable, E extends BaseEntity> implements BaseService<PK, E> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);

    /**
     * baseMapper
     */
    private BaseMapper<PK, E> baseMapper;

    /**
     * 给baseMapper赋值,入参要是baseMapper的子接口
     * <p>
     *
     * @param baseMapper 具体的mapper是baseMapper的子接口
     * @return
     * @throws
     * @author: 张弓
     * @date:
     * @version: 1.0.0
     */
    public void setBaseMapper(BaseMapper<PK, E> baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 新增对象
     * <p>
     *
     * @param entity 对象
     * @return int 返回的主键
     * @throws BaseException
     * @author: 张弓
     * @date:
     * @version: 1.0.0
     */
    @Override
    public int save(E entity) throws BaseException {
        return baseMapper.save(entity);
    }

    /**
     * 通过主键, 删除对象
     * <p>
     *
     * @param id 主键
     * @return int 删除行数
     * @throws BaseException
     * @author: 张弓
     * @date:
     * @version: 1.0.0
     */
    @Override
    public int remove(PK id) throws BaseException {
        return baseMapper.remove(id);
    }

    /**
     * 更新对象
     * <p>
     *
     * @param entity 对象
     * @return int 成功的行数
     * @throws BaseException
     * @author: 张弓
     * @date:
     * @version: 1.0.0
     */
    @Override
    public int update(E entity) throws BaseException {
        return baseMapper.update(entity);

    }

    /**
     * 通过主键, 查询对象
     * <p>
     *
     * @param id 主键
     * @return E 实体
     * @throws BaseException
     * @author: 张弓
     * @date:
     * @version: 1.0.0
     */
    @Override
    public E get(PK id) throws BaseException {
        return baseMapper.get(id);
    }
}
