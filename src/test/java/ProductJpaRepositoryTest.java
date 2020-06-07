import com.education.entity.JpaProduct;
import com.education.entity.Type;
import com.education.jpa.ProductJpaRepository;
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
public class ProductJpaRepositoryTest {
    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Test
    public void findAll() {
        List<JpaProduct> exp = List.of(
                new JpaProduct(1, "Эскимо_тест", Date.valueOf("2020-03-25"), 10, new Type(3, "Мороженое")),
                new JpaProduct(2, "Сыр Пермский_тест", Date.valueOf("2020-04-25"), 12, new Type(1, "Сыр")),
                new JpaProduct(3, "Молоко нытвенское_тест", Date.valueOf("2020-05-25"), 1, new Type(2, "Молоко"))
        );
        assertTrue(productJpaRepository.findAll().containsAll(exp));
    }

    @Test
    public void findByName() {
        List<JpaProduct> exp = List.of(new JpaProduct(1, "Эскимо_тест", Date.valueOf("2020-03-25"), 10, new Type(3, "Мороженое")));
        assertEquals(exp, productJpaRepository.findByName("Эскимо_тест"));
    }

    @Test
    public void findByDate() {
        List<JpaProduct> exp = List.of(new JpaProduct(1, "Эскимо_тест", Date.valueOf("2020-03-25"), 10, new Type(3, "Мороженое")));
        assertEquals(exp, productJpaRepository.findByExpiredDate(Date.valueOf("2020-03-25")));
    }

    @Test
    public void findByType() {
        List<JpaProduct> exp = List.of(new JpaProduct(1, "Эскимо_тест", Date.valueOf("2020-03-25"), 10, new Type(3, "Мороженое")));
        assertEquals(exp, productJpaRepository.findByTypeName("Мороженое"));
    }

    @Test
    public void findByExpiredDateBefore() {
        List<JpaProduct> exp = List.of(new JpaProduct(1, "Эскимо_тест", Date.valueOf("2020-03-25"), 10, new Type(3, "Мороженое")));
        assertEquals(exp, productJpaRepository.findByExpiredDateBefore(Date.valueOf("2020-04-25")));
    }

    @Test
    public void findByPriceIsLessThan() {
        List<JpaProduct> exp = List.of(new JpaProduct(3, "Молоко нытвенское_тест", Date.valueOf("2020-05-25"), 1, new Type(2, "Молоко")));
        assertEquals(exp, productJpaRepository.findByPriceIsLessThan(10));
    }

    @Test
    public void saveAndDelete() {
        JpaProduct product = new JpaProduct(4, "test", Date.valueOf("2020-06-07"), 1, new Type(1, "Сыр"));
        productJpaRepository.save(product);
        assertEquals(product, productJpaRepository.findById(4).get());
        productJpaRepository.deleteById(4);
        assertTrue(productJpaRepository.findById(4).isEmpty());
    }
}