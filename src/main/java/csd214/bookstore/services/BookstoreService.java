package csd214.bookstore.services;

import csd214.bookstore.entities.ProductEntity;
import csd214.bookstore.entities.PublicationEntity;
import csd214.bookstore.repositories.IRepository;

public class BookstoreService {
    private IRepository<ProductEntity> repository;

    // Dependency Injection via Constructor
    public BookstoreService(IRepository<ProductEntity> repository) {
        this.repository = repository;
    }

    public void performSale(Long id) {
        ProductEntity item = repository.findById(id);
        if (item == null) return;

        if (item instanceof PublicationEntity pub) {
            if (pub.getCopies() > 0) {
                pub.setCopies(pub.getCopies() - 1);
                repository.save(pub);
                System.out.println("Sold 1 copy of: " + pub.getTitle());
            } else {
                System.out.println("Out of stock!");
            }
        } else {
            System.out.println("Sold: " + item.getName());
        }
    }

    public void applyDiscount(Long id, double percent) {
        // 1. Fetch the item from the Pantry (Repository)
        ProductEntity item = repository.findById(id);
        if (item != null) {
            // 2. Apply Business Logic (The math)
            double oldPrice = item.getPrice();
            double newPrice = oldPrice * (1 - percent);
            item.setPrice(newPrice);
            // 3. Update the Pantry (Save)
            repository.save(item);

            System.out.printf("Service: Discount applied to %s. Price dropped from $%.2f to $%.2f\n",
                    item.getName(), oldPrice, newPrice);
        } else {
            System.out.println("Service Error: Item not found!");
        }
    }
}