package com.hacktues.api.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class MaterialCreateRequestTemp {
    private String name;
    private String description;
    private List<Long> fileIds;
}
