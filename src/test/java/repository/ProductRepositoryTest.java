package repository;

import com.education.entity.Product;
import com.education.entity.Type;
import com.education.repositories.ProductRepository;
import config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findAll() {
        List<Product> exp = List.of(
                new Product(1, "Эскимо_тест", Date.valueOf("2020-03-25"), 10, new Type(3, "Мороженое")),
                new Product(2, "Сыр Пермский_тест", Date.valueOf("2020-04-25"), 12, new Type(1, "Сыр")),
                new Product(3, "Молоко нытвенское_тест", Date.valueOf("2020-05-25"), 1, new Type(2, "Молоко"))
        );
        assertTrue(productRepository.findAll().containsAll(exp));
    }

    @Test
    public void findByName() {
        List<Product> exp = List.of(new Product(1, "Эскимо_тест", Date.valueOf("2020-03-25"), 10, new Type(3, "Мороженое")));
        assertEquals(exp, productRepository.findByName("Эскимо_тест"));
    }

    @Test
    public void findByDate() {
        List<Product> exp = List.of(new Product(1, "Эскимо_тест", Date.valueOf("2020-03-25"), 10, new Type(3, "Мороженое")));
        assertEquals(exp, productRepository.findByExpiredDate(Date.valueOf("2020-03-25")));
    }

    @Test
    public void findByType() {
        List<Product> exp = List.of(new Product(1, "Эскимо_тест", Date.valueOf("2020-03-25"), 10, new Type(3, "Мороженое")));
        assertEquals(exp, productRepository.findByTypeName("Мороженое"));
    }

    @Test
    public void findByExpiredDateBefore() {
        List<Product> exp = List.of(new Product(1, "Эскимо_тест", Date.valueOf("2020-03-25"), 10, new Type(3, "Мороженое")));
        assertEquals(exp, productRepository.findByExpiredDateBefore(Date.valueOf("2020-04-25")));
    }

    @Test
    public void findByPriceIsLessThan() {
        List<Product> exp = List.of(new Product(3, "Молоко нытвенское_тест", Date.valueOf("2020-05-25"), 1, new Type(2, "Молоко")));
        assertEquals(exp, productRepository.findByPriceIsLessThan(10));
    }

    @Test
    public void saveAndDelete() {
        Product product = new Product(4, "test", Date.valueOf("2020-06-07"), 1, new Type(1, "Сыр"));
        productRepository.save(product);
        assertEquals(product, productRepository.findById(4).get());
        productRepository.deleteById(4);
        assertTrue(productRepository.findById(4).isEmpty());
    }
}