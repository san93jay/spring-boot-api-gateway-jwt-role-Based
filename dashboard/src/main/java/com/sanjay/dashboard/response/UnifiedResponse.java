package com.sanjay.dashboard.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

/**
 * @author Sanjay Vishwakarma
 */
@Data
@ToString
public class UnifiedResponse<T> {
    private T response;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date timestamp;
    private boolean error;
    private List<String> messages;
    private Integer responseCode = 200;
    private HttpStatus httpStatus = HttpStatus.OK;
    private String nextUrl;
    private String screenName;
}
