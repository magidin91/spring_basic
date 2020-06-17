package service;

import com.education.entity.Type;
import com.education.exceptions.EntityAlreadyExistsException;
import com.education.exceptions.EntityHasDetailsException;
import com.education.exceptions.EntityIllegalArgumentException;
import com.education.exceptions.EntityNotFoundException;
import com.education.repositories.TypeRepository;
import com.education.service.impl.DefaultTypeService;
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
public class DefaultTypeServiceTest {
    @Autowired
    private DefaultTypeService defaultTypeService;

    @Autowired
    private TypeRepository typeRepository;

    @Test
    public void findAllTest() {
        List<Type> exp = List.of(
                new Type(1, "Сыр"), new Type(2, "Молоко"), new Type(3, "Мороженое"));
        assertTrue(defaultTypeService.findAll().containsAll(exp));
    }

    @Test
    public void findByIdTest() {
        Type type = new Type(1, "Сыр");
        assertEquals(type, defaultTypeService.findById(1));
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findByNullIdTest() {
        defaultTypeService.findById(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findByWrongFormatIdTest() {
        defaultTypeService.findById("wrong id");
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByNotExistedIdTest() {
        defaultTypeService.findById(-1);
    }

    @Test
    public void createTypeTest() {
        Type type = new Type(4, "Тест");
        defaultTypeService.create(type);
        assertEquals(type, typeRepository.findById(4).get());
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNUllTypeTest() {
        defaultTypeService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createTypeWithNullIdTest() {
        defaultTypeService.create(new Type(null, "Тест"));
    }


    @Test(expected = EntityAlreadyExistsException.class)
    public void createTypeAlreadyExists() {
        defaultTypeService.create(new Type(1, "Сыр"));
    }

    @Test(expected = EntityHasDetailsException.class)
    public void deleteTypeWithDetails() {
       defaultTypeService.delete(1);
    }

    @Test
    public void deleteType() {
        typeRepository.save( new Type(5, "Тест"));
        defaultTypeService.findAll().forEach(System.out::println);
        defaultTypeService.delete(5);
    }
}
