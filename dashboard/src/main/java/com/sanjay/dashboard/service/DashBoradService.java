package com.sanjay.dashboard.service;


import com.sanjay.dashboard.dto.DashBoardDTO;
import com.sanjay.dashboard.response.UnifiedResponse;

/**
 * @author Sanjay Vishwakarma
 */
public interface DashBoradService {
    UnifiedResponse<DashBoardDTO> getDashBoard();
}
