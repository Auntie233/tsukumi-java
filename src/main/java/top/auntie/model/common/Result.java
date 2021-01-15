package top.auntie.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private String code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>("000", null, data);
    }

    public static <T> Result<T> failed(String message) {
        return new Result<>("999", message, null);
    }

}
