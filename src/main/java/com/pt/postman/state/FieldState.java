package com.pt.postman.state;

import java.util.Objects;

/**
 * @author nate-pt
 * @date 2021/8/12 15:52
 * @Since 1.8
 * @Description 属性信息
 */
public class FieldState {
    private String key;

    private String value;

    private boolean checked;


    public FieldState() {
        this.key = null;
        this.value = null;
        this.checked = false;
    }


    public FieldState(String key, String value, boolean checked) {
        this.key = key;
        this.value = value;
        this.checked = checked;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FieldState that = (FieldState) o;
        return checked == that.checked &&
                Objects.equals(key, that.key) &&
                Objects.equals(value, that.value);
    }
}
