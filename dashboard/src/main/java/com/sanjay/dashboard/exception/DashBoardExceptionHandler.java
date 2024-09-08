package com.sanjay.dashboard.exception;


import com.sanjay.dashboard.response.UnifiedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sanjay Vishwakarma
 */
@ControllerAdvice
public class DashBoardExceptionHandler {


    @ExceptionHandler(DashBoardException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UnifiedResponse<Object> handleException(DashBoardException e){
        if (e.getServiceError() != null) {
            return getFailureResponse(e.getServiceError(),
                    e.getErrorMessage() == null ? e.getServiceError().getErrorMessage() : e.getErrorMessage());
        }
        return getFailureResponse(DashBoardServiceError.INTERNAL_SERVER_ERROR);

    }

    public UnifiedResponse<Object> getFailureResponse(DashBoardServiceError apiError, String message) {
        UnifiedResponse<Object> unifiedResponse = new UnifiedResponse<>();
        List<String> messageList = new ArrayList<>();
        messageList.add(message);
        unifiedResponse.setMessages(messageList);
        unifiedResponse.setResponseCode(apiError.getErrorCode());
        unifiedResponse.setError(true);
        return unifiedResponse;
    }

    public UnifiedResponse<Object> getFailureResponse(DashBoardServiceError apiError) {
        UnifiedResponse<Object> unifiedResponse = new UnifiedResponse<>();
        List<String> messageList = new ArrayList<>();
        messageList.add(apiError.getErrorMessage());
        unifiedResponse.setMessages(messageList);
        unifiedResponse.setResponseCode(apiError.getErrorCode());
        unifiedResponse.setError(true);
        return unifiedResponse;
    }
}
