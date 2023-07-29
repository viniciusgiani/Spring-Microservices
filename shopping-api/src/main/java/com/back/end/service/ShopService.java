package com.back.end.service;

import com.back.end.converter.DTOConverter;
import com.back.end.dto.*;
import com.back.end.exception.ProductNotFoundException;
import com.back.end.model.Shop;
import com.back.end.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;
    private final ProductService productService;
    private final UserService userService;

    public List<ShopDTO> getAll() {
        List<Shop> shops = shopRepository.findAll();
        return shops.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier) {
        List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);
        return shops.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO) {
        List<Shop> shops = shopRepository.findAllByDateGreaterThan(shopDTO.getDateTime());
        return shops.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public ShopDTO findById(long ProductId) {
        Optional<Shop> shop = shopRepository.findById(ProductId);
        if (shop.isPresent()) {
            return DTOConverter.convert(shop.get());
        }
        throw new ProductNotFoundException();
    }

    public ShopDTO save(ShopDTO shopDTO, String key) {
        UserDTO userDTO = userService.getUserBySsn(shopDTO.getUserIdentifier(), key);
        validateProduct(shopDTO.getItems());

        shopDTO.setTotal(shopDTO.getItems().stream().map(x -> x.getPrice()).reduce((float) 0, Float::sum));

        Shop shop = Shop.convert(shopDTO);
        shop.setDateTime(LocalDateTime.now());

        shop = shopRepository.save(shop);
        return DTOConverter.convert(shop);
    }

    private boolean validateProduct(List<ItemDTO> items) {
        for (ItemDTO item : items) {
            ProductDTO productDTO = productService.getProductByIdentifier(item.getProductIdentifier());
            if (productDTO == null) {
                return false;
            }
            item.setPrice(productDTO.getPrice());
        }
        return true;
    }

    public List<ShopDTO> getShopsByFilters(LocalDate startDate, LocalDate endDate, Float minValue) {
        List<Shop> shops = shopRepository.getShopByFilters(startDate, endDate, minValue);
        return shops.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public ShopReportDTO getReportByDate(LocalDate startDate, LocalDate endDate) {
        return shopRepository.getReportByDate(startDate, endDate);
    }
}
