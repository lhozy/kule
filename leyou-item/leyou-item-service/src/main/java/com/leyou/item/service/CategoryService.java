package com.leyou.item.service;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据父节点id查找
     * @param pid
     * @return
     */
    public List<Category> queryCategoryByPid(Long pid) {
        Category category=new Category();
        category.setParentId(pid);
        return this.categoryMapper.select(category);

    }
}
