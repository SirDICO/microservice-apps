package com.dico.inventoryservice;

import com.dico.inventoryservice.Model.Inventory;
import com.dico.inventoryservice.Repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return  args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("Home Deck Ipan set");
			inventory.setQuantity(500);


			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("Samsung Tv Set");
			inventory1.setQuantity(200);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);

		};
	}

}
