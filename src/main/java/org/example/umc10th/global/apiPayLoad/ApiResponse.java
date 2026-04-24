package org.example.umc10th.global.apiPayLoad;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.umc10th.global.apiPayLoad.code.BaseErrorCode;
import org.example.umc10th.global.apiPayLoad.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess","code","message","result"})
public class ApiResponse<T> {
    @JsonProperty("isSuccess")
    private final boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;

    public static <T> ApiResponse<T> onSuccess(T result, BaseSuccessCode code){
        return new ApiResponse<>(
                true,
                code.getCode(),
                code.getMessage(),
                result
        );
    }

    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result){
        return new ApiResponse<>(false, code.getCode(), code.getMessage(), result);
    }
}
