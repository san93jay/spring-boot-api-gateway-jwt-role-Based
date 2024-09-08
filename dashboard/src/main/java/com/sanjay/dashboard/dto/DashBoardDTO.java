package com.sanjay.dashboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author Sanjay Vishwakarma
 */
@Setter
@Getter
@NoArgsConstructor
public class DashBoardDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String serviceName;
    private AddressDTO address;
    private List<ExperienceDTO> experience;
    private List<SkillsDTO> skills;
    private List<ProjectsDetailsDTO> projectsDetails;


}
