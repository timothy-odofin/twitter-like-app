/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odofin.oyejide.twitterlikeapp.model.dto.response.exception;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author JIDEX
 */
public class ApiErrorResponse {

    @Getter
    @Setter
    private int status;
    @Getter
    @Setter
    private String error_code;
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private String detail;

    // getter and setters
    //Builder 
    public static final class ApiErrorResponseBuilder {

        private int status;
        private String error_code;
        private String message;
        private String detail;

        public ApiErrorResponseBuilder() {
        }

        public static ApiErrorResponseBuilder anApiErrorResponse() {
            return new ApiErrorResponseBuilder();
        }

        public ApiErrorResponseBuilder withStatus(int status) {
            this.status = status;
            return this;
        }

        public ApiErrorResponseBuilder withError_code(String error_code) {
            this.error_code = error_code;
            return this;
        }

        public ApiErrorResponseBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ApiErrorResponseBuilder withDetail(String detail) {
            this.detail = detail;
            return this;
        }

        public ApiErrorResponse build() {
            ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
            apiErrorResponse.status = this.status;
            apiErrorResponse.error_code = this.error_code;
            apiErrorResponse.detail = this.detail;
            apiErrorResponse.message = this.message;
            return apiErrorResponse;
        }
    }
}
