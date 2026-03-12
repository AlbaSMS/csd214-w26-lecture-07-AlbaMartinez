package csd214.bookstore.services;

import csd214.bookstore.entities.BookEntity;
import csd214.bookstore.entities.ProductEntity;
import csd214.bookstore.repositories.IRepository;
import csd214.bookstore.repositories.InMemoryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookstoreServiceTest {

    @Test
    void testPerformSaleReducesInventory() {
        // 1. ARRANGE (Using InMemoryRepository for speed)
        IRepository repo = new InMemoryRepository();
        BookEntity testBook = new BookEntity("Author", "Test Book", 10.0, 5);
        repo.save(testBook);

        BookstoreService service = new BookstoreService(repo);
        // 2. ACT
        service.performSale(testBook.getId());
        // 3. ASSERT
        ProductEntity updated = (ProductEntity) repo.findById(testBook.getId());
        assertEquals(4, ((BookEntity)updated).getCopies());
    }
}
