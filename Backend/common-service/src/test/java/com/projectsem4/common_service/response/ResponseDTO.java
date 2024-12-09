package com.projectsem4.common_service.response;

import com.projectsem4.common_service.util.MsgUtil;
import lombok.*;

import static com.projectsem4.common_service.util.MsgUtil.SPLIT;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {
    private boolean success;
    private Data<T> data;
    @lombok.Data
    @Builder
    @AllArgsConstructor
    public static
    class Data<D> {
        String msg_content;
        String msg_code;
        D payload;
    }
    public static <T> ResponseDTO<T> ok() {
        return ResponseDTO.<T>builder().success(true).build();
    }
    public static <T> ResponseDTO<T> ok(String message) {
        return msgExtract(ResponseDTO.<T>builder().success(true)
                .data(Data.<T>builder().
                        msg_content(message)
                        .build())
                .build());
    }
    public static <T> ResponseDTO<T> ok(String message, T data) {
        return msgExtract(ResponseDTO.<T>builder().success(true) .data(Data.<T>builder().msg_content(message) .payload(data) .build())
                .build());
    }
    public static <T> ResponseDTO<T> ok(T data) {
        return ResponseDTO.<T>builder().success(true)
                .data(Data.<T>builder()
                        .payload(data)
                        .build())
                .build();
    }
    public static <T> ResponseDTO<T> err(String msg) {
        return msgExtract(ResponseDTO.<T>builder().success(false) .data(Data.<T>builder()
                        .msg_content(msg)
                        .build())
                .build());
    }
    public static <T> ResponseDTO<T> errBadRequest(String msg) {
        return msgExtract (ResponseDTO.<T>builder().success(false)
                .data(Data.<T>builder()
                        .msg_code(msg)
                        .msg_content(MsgUtil.getMessage(msg))
                        .build())
                .build());
    }
    public static <T> ResponseDTO<T> errBadRequest(String code, Object... args) {
        return msgExtract(ResponseDTO.<T>builder().success(false)
                .data(Data.<T>builder()
                        .msg_code(code)
                        .msg_content(MsgUtil.getMessage(code, args))
                        .build())
                .build());
    }

    private static <T> ResponseDTO<T> msgExtract(ResponseDTO<T> response) {
        String[] extract = response.data.msg_content.split(SPLIT);
        if (extract.length == 2) {
            response.data.msg_code = extract[0];
            response.data.msg_content = extract[1];
        }
        return response;
    }
}