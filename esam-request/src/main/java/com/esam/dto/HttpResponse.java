package com.esam.dto;

import com.esam.constant.HttpConst;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author trutao
 * @create 2020-11-24 21:02
 */

@Data
@ToString
public class HttpResponse<T> implements Serializable {

    private static final long serialVersionUID = 4309507748301151682L;

    private String status;
    private String statusCode;
    private String message;
    private T resultData;

    public HttpResponse() {
    }

    /**
     * @param status
     * @param statusCode
     * @param message
     * @param resultData
     */
    public HttpResponse(String status, String statusCode, String message, T resultData) {
        super();
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.resultData = resultData;
    }

    /**
     * @param status
     * @param httpStatus
     * @param message
     * @param resultData
     */
    public HttpResponse(String status, HttpStatus httpStatus, String message, T resultData) {
        super();
        this.status = status;
        this.statusCode = String.valueOf(httpStatus.value());
        this.message = message;
        this.resultData = resultData;
    }

    /**
     * @param status
     * @param statusCode
     * @param message
     */
    public HttpResponse(String status, String statusCode, String message) {
        super();
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
    }

    /**
     * @param status
     * @param httpStatus
     * @param message
     */
    public HttpResponse(String status, HttpStatus httpStatus, String message) {
        super();
        this.status = status;
        this.statusCode = String.valueOf(httpStatus.value());
        this.message = message;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return HttpConst.SUCCESS.equalsIgnoreCase(status);
    }

    @JsonIgnore
    public boolean isFailure() {
        return HttpConst.FAILURE.equalsIgnoreCase(status);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((resultData == null) ? 0 : resultData.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        HttpResponse<?> other = (HttpResponse<?>) obj;
        if (message == null) {
            if (other.message != null) {
                return false;
            }
        } else if (!message.equals(other.message)) {
            return false;
        }

        if (resultData == null) {
            if (other.resultData != null) {
                return false;
            }
        } else if (!resultData.equals(other.resultData)) {
            return false;
        }

        if (status == null) {
            if (other.status != null) {
                return false;
            }
        } else if (!status.equals(other.status)) {
            return false;
        }

        if (statusCode == null) {
            if (other.statusCode != null) {
                return false;
            }
        } else if (!statusCode.equals(other.statusCode)) {
            return false;
        }

        return true;
    }

}

