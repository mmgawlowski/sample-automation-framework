package pojo.reqres.user.get;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Pojo class representing the response body for the Single User endpoint.
 */
public class SingleUser {

    private Data data;
    private Support support;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
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
        return new ToStringBuilder(this).append("data", data).append("support", support).toString();
    }
}
