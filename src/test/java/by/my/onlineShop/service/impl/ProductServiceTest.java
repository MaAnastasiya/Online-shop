package by.my.onlineShop.service.impl;

import by.my.onlineShop.entity.Product;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {
    private final ProductService productService;
    ProductServiceTest(){
        productService = Mockito.mock(ProductServiceImpl.class);
    }

    @Test
    public void testRetrieveProductByCategory_ShouldReturnTrue_WhenDataIsCorrect() throws ServiceException {
        Product product = new Product(4,3,"A4Tech Bloody G500","Наушники с микрофоном, мониторные (охватывающие), портативные, 20-20000 Гц",76.57,true,"headphones/A4Tech_Bloody_G500.jpg",3);
        Mockito.when(productService.retrieveProductById(4)).thenReturn(Optional.of(product));
        Optional<Product> actual = productService.retrieveProductById(4);
        assertTrue(actual.isPresent());
    }

    @Test
    public void testRetrieveProductByCategory_ShouldReturnFalse_WhenDataIsNotCorrect() throws ServiceException {
        Optional <Product> product = Optional.empty();
        Mockito.when(productService.retrieveProductById(10)).thenReturn(product);
        Optional<Product> actual = productService.retrieveProductById(10);
        assertFalse(actual.isPresent());
    }


    @Test
    public void testRetrieveProductsByCategory_ShouldReturnEquals_WhenDataIsCorrect() throws ServiceException {
        Product productFirst = new Product(4,3,"A4Tech Bloody G500","Наушники с микрофоном, мониторные (охватывающие), портативные, 20-20000 Гц",76.57,true,"headphones/A4Tech_Bloody_G500.jpg",3);
        productFirst.setPromotionId(2);
        Product productSecond = new Product(5,3,"Xiaomi Mi True Wireless Earbuds Basic 2 TWSEJ061LS","Беспроводные наушники с микрофоном, внутриканальные, портативные, Bluetooth 5.0, 20-20000 Гц, время работы 4 ч",42,false,"headphones/Xiaomi_Mi_True_Wireless_Earbuds_Basic_2_TWSEJ061LS.jpg",1);
        productSecond.setPromotionId(2);
        List<Product> expected = new LinkedList<>();
        expected.add(productFirst);
        expected.add(productSecond);
        Mockito.when(productService.retrieveProductsByCategory(3)).thenReturn(expected);

        List<Product> actual = productService.retrieveProductsByCategory(3);

        assertEquals(expected, actual);
    }






}
