package com.chenle.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.chenle.common.constant.ProductConstant;
import com.chenle.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.chenle.gulimall.product.dao.AttrDao;
import com.chenle.gulimall.product.dao.AttrGroupDao;
import com.chenle.gulimall.product.dao.CategoryDao;
import com.chenle.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.chenle.gulimall.product.entity.AttrEntity;
import com.chenle.gulimall.product.entity.AttrGroupEntity;
import com.chenle.gulimall.product.entity.CategoryEntity;
import com.chenle.gulimall.product.service.AttrService;
import com.chenle.gulimall.product.service.CategoryService;
import com.chenle.gulimall.product.vo.AttrGroupRelationVo;
import com.chenle.gulimall.product.vo.AttrRespVo;
import com.chenle.gulimall.product.vo.AttrVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenle.common.utils.PageUtils;
import com.chenle.common.utils.Query;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    AttrAttrgroupRelationDao attrAttrgroupRelationDao;
    @Autowired
    AttrGroupDao attrGroupDao;

    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    AttrDao attrDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void saveAttr(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        this.save(attrEntity);
        if (attr.getAttrType() == 1 &&attr.getAttrGroupId()!=null) {
            //保存关联关系
            AttrAttrgroupRelationEntity RelationEntity = new AttrAttrgroupRelationEntity();
            RelationEntity.setAttrGroupId(attr.getAttrGroupId());
            RelationEntity.setAttrId(attrEntity.getAttrId());
            log.info("111", attrEntity.getAttrId());
            attrAttrgroupRelationDao.insert(RelationEntity);
        }

    }

    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type) {
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<AttrEntity>().eq("attr_type", "base".equalsIgnoreCase(type) ? ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode() : ProductConstant.AttrEnum.ATTR_TYPE_SALE.getCode());

        if (catelogId != 0) {
            queryWrapper.eq("catelog_id", catelogId);
        }
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.and((wrapper) -> {
                wrapper.eq("attr_id", key).or().like("attr_name", key);
            });
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );
        PageUtils pageUtils = new PageUtils(page);
        List<AttrEntity> records = page.getRecords();
        List<AttrRespVo> PES = records.stream().map((attrEntity -> {
            AttrRespVo attrresVo = new AttrRespVo();
            BeanUtils.copyProperties(attrEntity, attrresVo);
            if ("base".equalsIgnoreCase(type)) {
                AttrAttrgroupRelationEntity attrId = attrAttrgroupRelationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));
                if (attrId != null) {
                    AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrId.getAttrGroupId());
                    attrresVo.setGroupName(attrGroupEntity.getAttrGroupName());
                }
            }

            CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
            if (categoryEntity != null) {
                attrresVo.setCatelogName(categoryEntity.getName());
            }
            return attrresVo;
        })).collect(Collectors.toList());
        pageUtils.setList(PES);
        return pageUtils;
    }

    @Override
    public AttrRespVo getAttrInfo(Long attrId) {

        log.info("111");
        //查询详细信息
        AttrEntity attrEntity = this.getById(attrId);

        //查询分组信息
        AttrRespVo respVo = new AttrRespVo();
        BeanUtils.copyProperties(attrEntity, respVo);


        if (attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            //1、设置分组信息
            AttrAttrgroupRelationEntity relationEntity = attrAttrgroupRelationDao.selectOne
                    (new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrId));
            if (relationEntity != null) {
                respVo.setAttrGroupId(relationEntity.getAttrGroupId());
                //获取分组名称
                AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(relationEntity.getAttrGroupId());
                if (attrGroupEntity != null) {
                    respVo.setGroupName(attrGroupEntity.getAttrGroupName());
                }
            }
        }


        //2、设置分类信息
        Long catelogId = attrEntity.getCatelogId();

        Long[] catelogPath = categoryService.findCategoryPath(catelogId);

        respVo.setCatelogPath(catelogPath);
        CategoryEntity categoryEntity = categoryDao.selectById(catelogId);
        if (categoryEntity != null) {
            respVo.setCatelogName(categoryEntity.getName());
        }

        return respVo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateAttr(AttrVo attr) {

        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);

        this.updateById(attrEntity);

        if (attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            if (attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
                //1、修改分组关联
                AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
                relationEntity.setAttrGroupId(attr.getAttrGroupId());
                relationEntity.setAttrId(attr.getAttrId());

                Integer count = attrAttrgroupRelationDao.selectCount(new QueryWrapper<AttrAttrgroupRelationEntity>()
                        .eq("attr_id", attr.getAttrId()));

                if (count > 0) {
                    attrAttrgroupRelationDao.update(relationEntity,
                            new UpdateWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attr.getAttrId()));
                } else {
                    attrAttrgroupRelationDao.insert(relationEntity);
                }
            }
        }


    }

    @Override
    public List<AttrEntity> getRelationGroup(Long attrgroupid) {
        List<AttrAttrgroupRelationEntity> entities = attrAttrgroupRelationDao.selectList
                (new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrgroupid));

        List<Long> attrIds = entities.stream().map((attr) -> {
            return attr.getAttrId();
        }).collect(Collectors.toList());

        //根据attrIds查找所有的属性信息
        //Collection<AttrEntity> attrEntities = this.listByIds(attrIds);

        //如果attrIds为空就直接返回一个null值出去
        if (attrIds == null || attrIds.size() == 0) {
            return null;
        }

        List<AttrEntity> attrEntityList = this.baseMapper.selectBatchIds(attrIds);

        return attrEntityList;
    }

    @Override
    public void deleteRelation(AttrGroupRelationVo[] vos) {
//        attrAttrgroupRelationDao.delete(new QueryWrapper<AttrAttrgroupRelationEntity>().eq(""))
        List<AttrAttrgroupRelationEntity> collect = Arrays.asList(vos).stream().map((item) -> {
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(item, attrAttrgroupRelationEntity);
            return attrAttrgroupRelationEntity;
        }).collect(Collectors.toList());

        attrAttrgroupRelationDao.deleteBatchRelations(collect);
    }
    @Override
    public List<AttrEntity> getRelationAttr(Long attrgroupId) {

        List<AttrAttrgroupRelationEntity> entities = attrAttrgroupRelationDao.selectList
                (new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrgroupId));

        List<Long> attrIds = entities.stream().map((attr) -> {
            return attr.getAttrId();
        }).collect(Collectors.toList());

        //根据attrIds查找所有的属性信息
        //Collection<AttrEntity> attrEntities = this.listByIds(attrIds);

        //如果attrIds为空就直接返回一个null值出去
        if (attrIds == null || attrIds.size() == 0) {
            return null;
        }

        List<AttrEntity> attrEntityList = this.baseMapper.selectBatchIds(attrIds);

        return attrEntityList;
    }

    @Override
    public List<Long> selectSearchAttrs(List<Long> attrIds) {
        return baseMapper.selectsearchAttrIds(attrIds);
    }

    //获取当前分组没有被关联的属性
    @Override
    public PageUtils getNORelationAttr(Map<String, Object> params, Long attrgroupId) {
        //1、当前分组只能关联自己所属的分类里面的所有属性
        AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrgroupId);
        //获取当前分类的id
        Long catelogId = attrGroupEntity.getCatelogId();

        //2、当前分组只能关联别的分组没有引用的属性
        //2.1）、当前分类下的其它分组
        List<AttrGroupEntity> groupEntities = attrGroupDao.selectList(new QueryWrapper<AttrGroupEntity>()
                .eq("catelog_id", catelogId));

        //获取到所有的attrGroupId
        List<Long> collect = groupEntities.stream().map((item) -> {
            return item.getAttrGroupId();
        }).collect(Collectors.toList());


        //2.2）、这些分组关联的属性
        List<AttrAttrgroupRelationEntity> groupId = attrAttrgroupRelationDao.selectList
                (new QueryWrapper<AttrAttrgroupRelationEntity>().in("attr_group_id", collect));

        List<Long> attrIds = groupId.stream().map((item) -> {
            return item.getAttrId();
        }).collect(Collectors.toList());

        //2.3）、从当前分类的所有属性移除这些属性
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<AttrEntity>()
                .eq("catelog_id", catelogId).eq("attr_type",ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode());

        if (attrIds != null && attrIds.size() > 0) {
            queryWrapper.notIn("attr_id", attrIds);
        }

        //判断是否有参数进行模糊查询
        String key = (String) params.get("key");
        if (!org.apache.commons.lang.StringUtils.isEmpty(key)) {
            queryWrapper.and((w) -> {
                w.eq("attr_id",key).or().like("attr_name",key);
            });
        }
        IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(params), queryWrapper);

        PageUtils pageUtils = new PageUtils(page);


        return pageUtils;
    }
}