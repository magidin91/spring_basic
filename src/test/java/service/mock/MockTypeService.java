package service.mock;

import com.education.entity.Type;
import com.education.service.TypeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock TypeService для тестирования TypeController
 */
@Service
public class MockTypeService implements TypeService {
    @Override
    public List<Type> findAll() {
        return new ArrayList<>();
    }

    @Override
    public Type findById(Object id) {
        return new Type(Integer.valueOf((String) id), "typeTest");
    }

    @Override
    public Type create(Type type) {
        return type;
    }

    @Override
    public Type update(Type type) {
        return type;
    }

    @Override
    public void delete(Object id) {
    }
}
