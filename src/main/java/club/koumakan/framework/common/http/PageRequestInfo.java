package club.koumakan.framework.common.http;

import lombok.Data;

@Data
public class PageRequestInfo<T> {

    public static final String ASC = "ASC";
    public static final String DESC = "DESC";

    private int pageNum;
    private int pageSize;
    private String direction;
    private String fieldName;
    private T condition;
}
