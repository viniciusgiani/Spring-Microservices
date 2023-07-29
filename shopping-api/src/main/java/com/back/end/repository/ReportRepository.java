package com.back.end.repository;

import com.back.end.dto.ShopReportDTO;
import com.back.end.model.Shop;

import java.time.LocalDate;
import java.util.List;
public interface ReportRepository {
    List<Shop> getShopByFilters(LocalDate startDate, LocalDate endDate, Float minValue);
    ShopReportDTO getReportByDate(LocalDate startDate, LocalDate endDate);

}
