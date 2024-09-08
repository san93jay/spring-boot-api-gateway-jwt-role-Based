package com.sanjay.dashboard.serviceImpl;


import com.sanjay.dashboard.dto.DashBoardDTO;
import com.sanjay.dashboard.response.UnifiedResponse;
import com.sanjay.dashboard.service.DashBoradService;
import org.springframework.stereotype.Service;

/**
 * @author Sanjay Vishwakarma
 */
@Service
public class DashBoardServiceImpl implements DashBoradService {

    @Override
    public UnifiedResponse<DashBoardDTO> getDashBoard() {
        return null;
    }
}
