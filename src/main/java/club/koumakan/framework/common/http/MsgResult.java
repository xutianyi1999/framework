package club.koumakan.framework.common.http;

import lombok.Data;

@Data
public class MsgResult<T> {

    public static final int SUCCESS = 200;
    public static final int ERROR = 500;

    private int code;
    private String msg;
    private T data;

    public MsgResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> MsgResult<T> success() {
        return new MsgResult<>(SUCCESS, null, null);
    }

    public static <T> MsgResult<T> success(String msg, T data) {
        return new MsgResult<>(SUCCESS, msg, data);
    }

    public static <T> MsgResult<T> success(T data) {
        return new MsgResult<>(SUCCESS, null, data);
    }

    public static <T> MsgResult<T> success(String msg) {
        return new MsgResult<>(SUCCESS, msg, null);
    }

    public static <T> MsgResult<T> error(String msg) {
        return new MsgResult<>(ERROR, msg, null);
    }

    public static <T> MsgResult<T> error() {
        return new MsgResult<>(ERROR, null, null);
    }
}
