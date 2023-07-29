package com.back.end.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShopReportDTO {
    private Integer count;
    private Double total;
    private Double mean;
}
