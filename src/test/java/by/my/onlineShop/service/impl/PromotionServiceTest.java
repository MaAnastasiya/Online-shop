package by.my.onlineShop.service.impl;

import by.my.onlineShop.entity.Promotion;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.PromotionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PromotionServiceTest {
    private final PromotionService promotionService;
    PromotionServiceTest(){
        promotionService = Mockito.mock(PromotionServiceImpl.class);
    }

    // возвращает пользователя по id, если данные введены корректно
    @Test
    public void testRetrieveUserInformationById_ShouldReturnTrue_WhenDataIsCorrect() throws ServiceException {
        byte tmp = 30;
        Optional <Promotion> promotion = Optional.of(new Promotion(1,"Летняя распродажа","Покупайте товары со скидкой во время летней распродажи!",tmp,new Date(),new Date(),"promotions/summer_sale.jpg"));
        Mockito.when(promotionService.retrievePromotionById(1)).thenReturn(promotion);
        Optional<Promotion> actual = promotionService.retrievePromotionById(1);
        assertTrue(actual.isPresent());
    }

    @Test
    public void testRetrieveUserInformationById_ShouldReturnFalse_WhenDataIsNotCorrect() throws ServiceException {
        Optional <Promotion> promotion = Optional.empty();
        Mockito.when(promotionService.retrievePromotionById(10)).thenReturn(promotion);
        Optional<Promotion> actual = promotionService.retrievePromotionById(10);
        assertFalse(actual.isPresent());
    }


}










/*
    @Test
    public void testRetrievePromotions_ShouldReturnEquals_WhenDataIsCorrect() throws ServiceException, ParseException {
        List<Promotion> actual = promotionService.retrievePromotions();

        byte tmp = 30;
        Promotion promotionFirst = new Promotion(1,"Летняя распродажа","Покупайте товары со скидкой во время летней распродажи!",tmp,actual.get(0).getBeginningDate(),actual.get(0).getExpirationDate(),"promotions/summer_sale.jpg");

        tmp = 50;
        Promotion promotionSecond = new Promotion(2,"Слушай музыку","Акция \"Слушай музыку\". Купите наушники со скидкой и слушайте любимую музыку где захотите!",tmp,actual.get(1).getBeginningDate(),actual.get(1).getExpirationDate(),"promotions/listen_music.jpg");

        List<Promotion> expected = new LinkedList<>();
        expected.add(promotionFirst);
        expected.add(promotionSecond);
        Mockito.when(promotionService.retrievePromotions()).thenReturn(expected);

        assertEquals(expected, actual);
    }
*/



