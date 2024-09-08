package com.sanjay.dashboard.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Sanjay Vishwakarma
 */
@Data
@NoArgsConstructor
public class AddressDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
}
