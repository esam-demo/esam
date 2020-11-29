package com.esam.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author trutao
 * @create 2020-11-25 20:15
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestDto implements Serializable {

    private static final long serialVersionUID = -5337108823703057930L;

    protected String creator;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    protected Date createDate;

    protected String updater;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    protected Date updateDate;

    private String requestedFor;

    private String type;

    private String status;

    private String justification;

    private String resourceId;

    private String resourceName;

    private String resouceType;

    private String source;

    private String comment;


}
