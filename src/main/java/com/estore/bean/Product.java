package com.estore.bean;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    private Integer id;

    private String name;

    private BigDecimal price;

    private String serviceFg;

    private String serviceMyf;

    private String serviceZt;

    private String serviceTh;

    private String version;

    private Integer stock;

    private String writer;

    private Integer publishId;

    private String description;

    private Integer pages;

    private String isbn;

    private String bill;

    private Date publishDate;

    private String featureImages;

    private String images;

    private Integer categoryDetailId;

    private Integer saleNum;

    private String flag;

    private String explains;

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
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getServiceFg() {
        return serviceFg;
    }

    public void setServiceFg(String serviceFg) {
        this.serviceFg = serviceFg;
    }

    public String getServiceMyf() {
        return serviceMyf;
    }

    public void setServiceMyf(String serviceMyf) {
        this.serviceMyf = serviceMyf;
    }

    public String getServiceZt() {
        return serviceZt;
    }

    public void setServiceZt(String serviceZt) {
        this.serviceZt = serviceZt;
    }

    public String getServiceTh() {
        return serviceTh;
    }

    public void setServiceTh(String serviceTh) {
        this.serviceTh = serviceTh;
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer == null ? null : writer.trim();
    }

    public Integer getPublishId() {
        return publishId;
    }

    public void setPublishId(Integer publishId) {
        this.publishId = publishId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill == null ? null : bill.trim();
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getFeatureImages() {
        return featureImages;
    }

    public void setFeatureImages(String featureImages) {
        this.featureImages = featureImages == null ? null : featureImages.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public Integer getCategoryDetailId() {
        return categoryDetailId;
    }

    public void setCategoryDetailId(Integer categoryDetailId) {
        this.categoryDetailId = categoryDetailId;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

}