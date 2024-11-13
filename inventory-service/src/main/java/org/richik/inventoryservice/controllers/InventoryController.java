package org.richik.inventoryservice.controllers;


import lombok.RequiredArgsConstructor;
import org.richik.inventoryservice.services.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;


//    @GetMapping("{sku-code}")
    @GetMapping
    public ResponseEntity<?> getInventory(@RequestParam List<String> skuCode) {
        return ResponseEntity.status(200)
                .body(inventoryService.getItem(skuCode));
    }
}
