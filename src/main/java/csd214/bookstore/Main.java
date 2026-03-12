package csd214.bookstore;

import csd214.bookstore.entities.ProductEntity;
import csd214.bookstore.repositories.*;
import csd214.bookstore.services.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Persistence Strategy:");
        System.out.println("1. ArrayList (Memory)");
        System.out.println("2. H2 Database (In-Memory SQL)");
        System.out.println("3. MySQL (Production)");
        System.out.print("Choice: ");

        int choice = sc.nextInt();
        IRepository<ProductEntity> repository = new MySqlRepository();

        // WIRING PHASE
        switch (choice) {
            case 2: repository = new H2Repository(); break;
            case 3: repository = new MySqlRepository(); break;
            default: repository = new InMemoryRepository(); break;
        }

        BookstoreService bookstoreService = new BookstoreService(repository);
        PenService penService = new PenService(repository);

        // INJECTION PHASE
        App app = new App(repository, bookstoreService, penService);
        try {
            app.run();
        } finally {
            repository.close();
        }
    }
}