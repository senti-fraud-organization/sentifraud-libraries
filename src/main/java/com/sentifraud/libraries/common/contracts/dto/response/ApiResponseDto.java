package com.sentifraud.libraries.common.contracts.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record ApiResponseDto<T>(
        T data,
        String code,
        List<String> infos,
        long elapsedTime,
        LocalDateTime timestamp
) {
    // Builder class for constructing ApiResponse
    public static class Builder<T> {
        private T data;
        private String code = "200";  // Default success code
        private List<String> infos = new ArrayList<>();
        private long elapsedTime;
        private LocalDateTime timestamp = LocalDateTime.now();

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> code(String code) {
            this.code = code;
            return this;
        }

        public Builder<T> addInfo(String info) {
            this.infos.add(info);
            return this;
        }

        public Builder<T> infos(List<String> infos) {
            this.infos = infos;
            return this;
        }

        public Builder<T> elapsedTime(long elapsedTime) {
            this.elapsedTime = elapsedTime;
            return this;
        }

        public ApiResponseDto<T> build() {
            return new ApiResponseDto<>(data, code, infos, elapsedTime, timestamp);
        }
    }

    // Static factory method for creating a builder
    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    // Utility method for success response
    public static <T> ApiResponseDto<T> success(T data) {
        return ApiResponseDto.<T>builder()
                .data(data)
                .code("200")
                .addInfo("Request processed successfully")
                .elapsedTime(System.currentTimeMillis())
                .build();
    }

    // Utility method for error response
    public static <T> ApiResponseDto<T> error(String code, String message) {
        return ApiResponseDto.<T>builder()
                .code(code)
                .addInfo(message)
                .elapsedTime(System.currentTimeMillis())
                .build();
    }
}
