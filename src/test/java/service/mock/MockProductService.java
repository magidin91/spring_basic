package service.mock;

import com.education.entity.Product;
import com.education.entity.Type;
import com.education.service.ProductService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Mock ProductService для тестирования ProductController
 */
@Service
public class MockProductService implements ProductService {
    @Override
    public List<Product> findAll() {
        return new ArrayList<>();
    }

    @Override
    public Product findById(Object id) {
        return new Product(Integer.valueOf((String) id), "testProduct",
                Date.valueOf("2020-03-25"), 0, new Type(3, "Мороженое"));
    }

    @Override
    public Product create(Product product) {
        return product;
    }

    @Override
    public Product update(Product product) {
        return product;
    }

    @Override
    public void delete(Object id) {
    }
}