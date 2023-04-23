package com.chenle.gulimall.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenle.gulimall.product.dao.AttrGroupDao;
import com.chenle.gulimall.product.entity.BrandEntity;
import com.chenle.gulimall.product.service.BrandService;
import com.chenle.gulimall.product.service.CategoryService;
import com.chenle.gulimall.product.vo.SpuItemAttrGroupVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.RedissonCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GulimallProductApplication.class)
public class GulimallProductApplicationTests {
    @Autowired
    BrandService brandService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    AttrGroupDao attrGroupDao;
    @Autowired
    RedissonClient redissonClient;

    @Test
    public void testRedisson() {
        System.out.println(redissonClient);
    }


    @Test
    public void test() {

        List<SpuItemAttrGroupVo> attrGroupWithAttrsBySpuId = attrGroupDao.getAttrGroupWithAttrsBySpuId(13L, 225L);
        System.out.println(attrGroupWithAttrsBySpuId);
    }
    @Test
    public void testRedis() {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("hello", "world_" + UUID.randomUUID().toString());
        System.out.println("redis:" + operations.get("hello"));
    }


    @Test
    public void contextLoads() {
        Long[] catelogPath = categoryService.findCategoryPath(329l);

        log.info("完整路径catelogPath={}", Arrays.asList(catelogPath));
    }

}
