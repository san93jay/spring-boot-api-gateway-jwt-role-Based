package com.sanjay.dashboard.exception;


import lombok.Getter;

/**
 * @author Sanjay Vishwakarma
 */
@Getter
public enum DashBoardServiceError {
    INTERNAL_SERVER_ERROR(500, "Internal server error");

    private final int errorCode;
    private final String errorMessage;
    DashBoardServiceError(int errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
