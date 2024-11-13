package org.richik.inventoryservice.configs;


import org.richik.inventoryservice.models.Inventory;
import org.richik.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryConfiguration {

    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
        return args -> {
            Inventory inventory = new Inventory();
            inventory.setSkuCode("Iphone 12");
            inventory.setQuantity(10);

            Inventory inventory2 = new Inventory();
            inventory2.setSkuCode("Iphone 11");
            inventory2.setQuantity(20);

            inventoryRepository.save(inventory);
            inventoryRepository.save(inventory2);
        };
    }
}
