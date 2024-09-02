package by.my.onlineShop.service.impl;

import by.my.onlineShop.entity.Category;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceTest {
    private final CategoryService categoryService;

    CategoryServiceTest(){
        categoryService = Mockito.mock(CategoryServiceImpl.class);
    }
    // инициализировать пул соединения
   /* @BeforeAll
    static void initializeConnectionPool() throws ConnectionException {
        ConnectionPool.getInstance().initialize();
    }*/

    // возвращает категорию по id, когда данные верны
    @Test
    public void testRetrieveCategoryBtId_ShouldReturnCategory_WhenDataIsCorrect() throws ServiceException {
        Optional <Category> category = Optional.of(new Category(2, "Компьютерные мыши"));
        Mockito.when(categoryService.retrieveCategoryBtId(2L)).thenReturn(category);
        Optional<Category> actual = categoryService.retrieveCategoryBtId(2L);
        assertTrue(actual.isPresent());
    }

    // возвращает категорию по id, когда данные неверны
    @Test
    public void testRetrieveCategoryBtId_ShouldReturnNull_WhenDataIsNotCorrect() throws ServiceException {
        Optional <Category> category = Optional.empty();
        Mockito.when(categoryService.retrieveCategoryBtId(4L)).thenReturn(category);
        Optional<Category> actual = categoryService.retrieveCategoryBtId(4L);
        assertFalse(actual.isPresent());
    }

    // создает список категорий, когда данные неравны нулю
    @Test
    public void testRetrieveCategories_ShouldReturnCategories_WhenDataIsNotNull() throws ServiceException {

        Category categoryFirst = new Category();
        categoryFirst.setId(1);
        categoryFirst.setCategoryName("Ноутбуки");

        Category categorySecond = new Category();
        categorySecond.setId(2);
        categorySecond.setCategoryName("Компьютерные мыши");

        Category categoryThree = new Category();
        categoryThree.setId(3);
        categoryThree.setCategoryName("Наушники");

        List<Category> expected = new LinkedList<>();
        expected.add(categorySecond);
        expected.add(categoryThree);
        expected.add(categoryFirst);

        Mockito.when(categoryService.retrieveCategories()).thenReturn(expected);
        List<Category> actual = categoryService.retrieveCategories();


        assertEquals(expected, actual);
    }
}