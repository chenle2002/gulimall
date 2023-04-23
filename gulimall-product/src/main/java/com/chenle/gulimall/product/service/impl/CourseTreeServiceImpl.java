package com.chenle.gulimall.product.service.impl;

import com.chenle.gulimall.product.entity.CourseTreeEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenle.common.utils.PageUtils;
import com.chenle.common.utils.Query;

import com.chenle.gulimall.product.dao.CourseTreeDao;
import com.chenle.gulimall.product.entity.CourseTreeEntity;
import com.chenle.gulimall.product.service.CourseTreeService;


@Service("courseTreeService")
public class CourseTreeServiceImpl extends ServiceImpl<CourseTreeDao, CourseTreeEntity> implements CourseTreeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CourseTreeEntity> page = this.page(
                new Query<CourseTreeEntity>().getPage(params),
                new QueryWrapper<CourseTreeEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CourseTreeEntity> listWithTree() {
        //1.查出所有分类
        List<CourseTreeEntity> entities = baseMapper.selectList(null);

        //2.组装成父子的树形结构
        List<CourseTreeEntity> level1 = entities.stream().filter(courseTree ->
                courseTree.getParentCid() == 0
        ).map((menu) -> {
            menu.setChildren(getchildren(menu, entities));
            return menu;
        }).sorted((menu1, menu2) -> {
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());

        return level1;
    }

    @Override
    public PageUtils listgrandfather(Map<String, Object> params) {
        IPage<CourseTreeEntity> page = this.page(
                new Query<CourseTreeEntity>().getPage(params),
                new QueryWrapper<CourseTreeEntity>().eq("parent_cid",0)
        );
        return new PageUtils(page);
    }

    //递归查找当前菜单的子菜单
    private List<CourseTreeEntity> getchildren(CourseTreeEntity root, List<CourseTreeEntity> all) {
        List<CourseTreeEntity> child = all.stream().filter(CourseTreeEntity -> {
            return root.getCourseId().equals(CourseTreeEntity.getParentCid());
        }).map(CourseTreeEntity -> {
            CourseTreeEntity.setChildren(getchildren(CourseTreeEntity, all));
            return CourseTreeEntity;
        }).sorted((menu1, menu2) -> {
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());

        return child;
    }
}