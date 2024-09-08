package com.sanjay.dashboard.exception;

import lombok.Getter;

/**
 * @author Sanjay Vishwakarma
 */
@Getter
public class DashBoardException extends Exception{

    private DashBoardServiceError serviceError;
    private final String errorMessage;

    public DashBoardException(DashBoardServiceError error, String message){
        super();
        this.serviceError=error;
        this.errorMessage=message;
    }

    public DashBoardException(DashBoardServiceError error){
        super();
        this.serviceError=error;
        this.errorMessage=null;
    }

}
