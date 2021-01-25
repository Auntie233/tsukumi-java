package top.auntie.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(0,null, data);
    }

    public static <T> Result<T> success() {
        return new Result<>(0,null, null);
    }

    public static <T> Result<T> failed(String message) {
        return new Result<>(-1, message, null);
    }

    public static <T> Result<T> failed(int code, String message) {
        return new Result<>(code, message, null);
    }

}
