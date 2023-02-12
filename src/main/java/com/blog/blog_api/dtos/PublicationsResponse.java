package com.blog.blog_api.dtos;

import java.util.List;

public class PublicationsResponse {
    private List<PublicationDTO> content;
    private int pageNumber;
    private int pageSize;
    private long totalElement;
    private  int totalPage;
    private boolean latest;
    public List<PublicationDTO> getContent() {
        return content;
    }
    public void setContent(List<PublicationDTO> content) {
        this.content = content;
    }
    public int getPageNumber() {
        return pageNumber;
    }
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public long getTotalElement() {
        return totalElement;
    }
    public void setTotalElement(long totalElement) {
        this.totalElement = totalElement;
    }
    public int getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public boolean isLatest() {
        return latest;
    }
    public void setLatest(boolean latest) {
        this.latest = latest;
    }
    public PublicationsResponse() {
     super();
    }

}
