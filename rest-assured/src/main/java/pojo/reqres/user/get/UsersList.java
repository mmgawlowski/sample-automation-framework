package pojo.reqres.user.get;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Pojo class representing the response body for the Users List endpoint.
 */
public class UsersList {
    private Integer page;
    private Integer perPage;
    private Integer total;
    private Integer totalPages;
    private List<Data> data = new ArrayList<>();
    private Support support;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("page", page).append("perPage", perPage).append("total", total).append("totalPages", totalPages).append("data", data).append("support", support).toString();
    }
}
