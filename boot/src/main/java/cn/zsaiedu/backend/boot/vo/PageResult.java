package cn.zsaiedu.backend.boot.vo;

import com.github.pagehelper.Page;
import lombok.Data;

@Data
public class PageResult {

    private int pageNum;

    private int pageSize;

    private long total;

    private int pages;

    public PageResult(Page page) {
        this.pages = null == page ? 0 : page.getPages();
        this.pageNum = null == page ? 0 : page.getPageNum();
        this.total = null == page ? 0 : page.getTotal();
        this.pageSize = null == page ? 0 : page.getPageSize();
    }

}
