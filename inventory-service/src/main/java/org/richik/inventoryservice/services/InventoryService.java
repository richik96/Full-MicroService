package org.richik.inventoryservice.services;


import org.richik.inventoryservice.dtos.InventoryResponseDto;
import org.richik.inventoryservice.models.Inventory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventoryService {

    public List<InventoryResponseDto> getItem(List<String> skuCode);
}
