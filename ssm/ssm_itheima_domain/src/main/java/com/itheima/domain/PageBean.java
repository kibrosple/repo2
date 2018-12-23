package com.itheima.domain;

/**
 * 分页对象
 */
public class PageBean<T> {
    private int totalCount; // 总记录数
    private int totalPage ; // 总页码
    private User user;      //
    private int start;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    private int currentPage ; //当前页码
    private int rows;//每页显示的记录数

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", user=" + user +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }


}
