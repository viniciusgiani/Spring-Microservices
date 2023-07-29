package com.back.end.controller;
import java.time.LocalDate;
import java.util.List;

import com.back.end.dto.ShopDTO;
import com.back.end.dto.ShopReportDTO;
import com.back.end.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class 	ShopController {

    private final ShopService shopService;

    @GetMapping("/shopping")
    public List<ShopDTO> getShops() {
        return shopService.getAll();
    }

    @GetMapping("/shopping/shopByUser/{userIdentifier}")
    public List<ShopDTO> getShops(@PathVariable String userIdentifier) {
        return shopService.getByUser(userIdentifier);
    }

    @GetMapping("/shopping/shopByDate")
    public List<ShopDTO> getShops(@RequestBody ShopDTO shopDTO) {
        return shopService.getByDate(shopDTO);
    }

    @GetMapping("/shopping/{id}")
    public ShopDTO findById(@PathVariable Long id) {
        return shopService.findById(id);
    }

    @PostMapping("/shopping/")
    @ResponseStatus(HttpStatus.CREATED)
    public ShopDTO newShop(
            @RequestHeader(name = "key", required=true) String key,
            @RequestBody ShopDTO shopDTO) {
        return shopService.save(shopDTO, key);
    }

    @GetMapping("/shopping/search")
    public List<ShopDTO> getShopsByFilter(
            @RequestParam(name = "startDate", required=true)
            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate startDate,
            @RequestParam(name = "endDate", required=false)
            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate endDate,
            @RequestParam(name = "minValue", required=false) Float minValue) {
        return shopService.getShopsByFilters(startDate, endDate, minValue);
    }

    @GetMapping("/shopping/report")
    public ShopReportDTO getReportByDate(
            @RequestParam(name = "startDate", required=true)
            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate startDate,
            @RequestParam(name = "endDate", required=true)
            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate endDate) {
        return shopService.getReportByDate(startDate, endDate);
    }

}
