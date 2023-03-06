package com.tony.framework.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class TableResultBean<T> implements Serializable {
    private int code;
    private String message = "success";
    private long total;
    private List<T> rows;
    private Map<String,Object> extra;

    public TableResultBean(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
