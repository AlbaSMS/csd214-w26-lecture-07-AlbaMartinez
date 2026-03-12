package csd214.bookstore.services;

import csd214.bookstore.entities.PenEntity;
import csd214.bookstore.entities.ProductEntity;
import csd214.bookstore.repositories.IRepository;

/**
 * Specialist Service.
 * Note how it only cares about Pen logic.
 */
public class PenService {
    private IRepository<ProductEntity> repository;

    public PenService(IRepository<ProductEntity> repository) {
        this.repository = repository;
    }

    public void updatePenColor(Long id, String newColor) {
        ProductEntity item = repository.findById(id);
        if (item instanceof PenEntity pen) {
            pen.setColor(newColor);
            repository.save(pen);
            System.out.println("Service: Pen updated to " + newColor);
        } else {
            System.out.println("Service Error: Item ID " + id + " is not a Pen!");
        }
    }
}