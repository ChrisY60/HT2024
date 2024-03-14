package com.hacktues.api.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class MaterialResponse {
    private final String name;
    private final Date date;
}
