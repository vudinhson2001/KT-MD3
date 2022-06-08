package service;

import model.Product;

import java.util.List;

public interface ProductService extends GeneralService<Product> {
    List<Product> findByPrice(int start, int end);
}
