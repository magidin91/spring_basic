package service;

import com.education.entity.Type;
import com.education.exceptions.EntityAlreadyExistsException;
import com.education.exceptions.EntityHasDetailsException;
import com.education.exceptions.EntityIllegalArgumentException;
import com.education.exceptions.EntityNotFoundException;
import com.education.jpa.TypeRepository;
import com.education.service.TypeService;
import config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class TypeServiceTest {
    @Autowired
    private TypeService typeService;

    @Autowired
    private TypeRepository typeRepository;

    @Test
    public void findAllTest() {
        List<Type> exp = List.of(
                new Type(1, "Сыр"), new Type(2, "Молоко"), new Type(3, "Мороженое"));
        assertTrue(typeService.findAll().containsAll(exp));
    }

    @Test
    public void findByIdTest() {
        Type type = new Type(1, "Сыр");
        assertEquals(type, typeService.findById(1));
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findByNullIdTest() {
        typeService.findById(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findByWrongFormatIdTest() {
        typeService.findById("wrong id");
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByNotExistedIdTest() {
        typeService.findById(-1);
    }

    @Test
    public void createTypeTest() {
        Type type = new Type(4, "Тест");
        typeService.create(type);
        assertEquals(type, typeRepository.findById(4).get());
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNUllTypeTest() {
        typeService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createTypeWithNullIdTest() {
        typeService.create(new Type(null, "Тест"));
    }


    @Test(expected = EntityAlreadyExistsException.class)
    public void createTypeAlreadyExists() {
        typeService.create(new Type(1, "Сыр"));
    }

    @Test(expected = EntityHasDetailsException.class)
    public void deleteTypeWithDetails() {
       typeService.delete(1);
    }

    @Test
    public void deleteType() {
        typeRepository.save( new Type(5, "Тест"));
        typeService.findAll().forEach(System.out::println);
        typeService.delete(5);
    }
}
