package com.crointech.croincommon.common;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Stephen Suen
 * @date 2021-04-01 2:33 下午
 * 做咩呀???
 * <p>
 * ━━━━━━感觉萌萌哒━━━━━━
 */
@Getter
@Data
public class DomainPageableEntity<T> implements Serializable {

    private static final long serialVersionUID = 7289749599339304468L;

    private Long total;

    private List<T> data;


    private DomainPageableEntity(Long total, List<T> data) {
        this.setTotal(total);
        this.setData(data);
    }

    public static <T> DomainPageableEntity<T> pageBuilder(Long total, List<T> data) {
        return new DomainPageableEntity<T>(total, data);
    }

    public void setTotal(Long total) {
        if (total < 0) {
            total = 0L;
        }
        this.total = total;
    }

    public void setData(List<T> data) {
        if (data == null || data.size() <= 0) {
            data = new LinkedList<>();
        }
        this.data = data;
    }
}
