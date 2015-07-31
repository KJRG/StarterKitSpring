package pl.spring.demo.aop;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.annotation.Autowired;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.IdAware;

import java.lang.reflect.Method;

public class BookDaoAdvisor implements MethodBeforeAdvice {

	@Autowired
	private Sequence sequence;
	
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        if (hasAnnotation(method, o, NullableId.class)) {
            checkNotNullId(objects[0]);
            setId(objects[0], o);
        }
    }
    
    private void setId(Object bookObject, Object bookDaoImplObject) throws Exception {
    	if(bookObject instanceof BookEntity && bookDaoImplObject instanceof BookDaoImpl) {
    		BookDaoImpl bookDaoImpl = (BookDaoImpl) bookDaoImplObject;
    		long nextId = sequence.nextValue(bookDaoImpl.findAll());
    		((BookEntity) bookObject).setId(nextId);
    	}
    }

    private void checkNotNullId(Object o) {
        if (o instanceof IdAware && ((IdAware) o).getId() != null) {
            throw new BookNotNullIdException();
        }
    }

    private boolean hasAnnotation (Method method, Object o, Class annotationClazz) throws NoSuchMethodException {
        boolean hasAnnotation = method.getAnnotation(annotationClazz) != null;

        if (!hasAnnotation && o != null) {
            hasAnnotation = o.getClass().getMethod(method.getName(), method.getParameterTypes()).getAnnotation(annotationClazz) != null;
        }
        return hasAnnotation;
    }
}
