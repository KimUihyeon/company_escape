package com.company.escape.util.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author kuh
 * @since 2020.04.07
 */

@Builder
@Data
@AllArgsConstructor
public class PublicBusApiResponse {

    private String code;
    private String errorString;
    private String data;
    private LocalDateTime delay;
}
