package by.my.onlineShop.service;

import by.my.onlineShop.entity.Category;
import by.my.onlineShop.exeptions.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CategoryService { // Категории обслуживания

    // Извлечение категорий
    // На выход - список категорий
    List<Category> retrieveCategories() throws ServiceException;


    // Способ получения категории по id
    // возвращает категорию по id (option)
    Optional<Category> retrieveCategoryBtId(long categoryId) throws ServiceException;
}
