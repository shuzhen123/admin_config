package com.mzfk.entity;



import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sz
 * @Title: Columns
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/11/2611:25
 */
//@Data
//@Entity
//@Table(name = "columns")
public class Columns {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;


    @Column(name = "order",columnDefinition = "tinyint(4) NOT NULL COMMENT '水平坐标: 1,2,3'")
    private Integer order;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "columnId")//指2表连接的关键字
    private List<ColumnItems> columnItems = new ArrayList<>();


    //表中的外键id
    @Column(name = "category_id",columnDefinition = "varchar(32) unsigned NOT NULL")
    private String categoryId;


    @Override
    public String toString() {
        return "Columns{" +
                "id='" + id + '\'' +
                ", order=" + order +
                ", columnItems=" + columnItems +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<ColumnItems> getColumnItems() {
        return columnItems;
    }

    public void setColumnItems(List<ColumnItems> columnItems) {
        this.columnItems = columnItems;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
