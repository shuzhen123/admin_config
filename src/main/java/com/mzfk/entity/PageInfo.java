package com.mzfk.entity;

import java.util.List;

/**
 * @author sz
 * @Title: PageInfo
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/10/229:43
 */
public class PageInfo {
    /**
     *
     */
    private static final long serialVersionUID = 3279139923697403180L;


    //当前页
    private int curPage=1;
    //每页数量
    private int pageSize = 25;
    //总数量
    private long totalSize = 0;
    //当前总页数
    private int totalPage = (int) ((totalSize + pageSize - 1) / pageSize);
    //
    private List<?> list;

    public int getCurPage() {
        return curPage;
    }
    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalSize() {
        return totalSize;
    }
    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }
    public int getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public List<?> getList() {
        return list;
    }
    public void setList(List<?> list) {
        this.list = list;
    }
}
