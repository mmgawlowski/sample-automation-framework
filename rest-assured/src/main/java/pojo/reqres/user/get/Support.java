package pojo.reqres.user.get;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Pojo class representing the part of the response body for the Single User and Users List endpoints
 * marked as "support".
 */
public class Support {
    private String url;
    private String text;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("url", url).append("text", text).toString();
    }

}
