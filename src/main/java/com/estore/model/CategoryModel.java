package com.estore.model;

import com.estore.bean.CategoryDetail;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryModel {
    private Integer id;

    private String name;

    //每一个大类别下对应着
    private List<CategoryDetail> categoryDetailList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryDetail> getCategoryDetailList() {
        return categoryDetailList;
    }

    public void setCategoryDetailList(List<CategoryDetail> categoryDetailList) {
        this.categoryDetailList = categoryDetailList;
    }
}
