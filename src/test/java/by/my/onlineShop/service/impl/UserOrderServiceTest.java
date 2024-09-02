package by.my.onlineShop.service.impl;

import by.my.onlineShop.entity.UserOrder;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.UserOrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserOrderServiceTest {

    private final UserOrderService userOrderService ;

    UserOrderServiceTest(){
        userOrderService = Mockito.mock(UserOrderServiceImpl.class);
    }

    @Test
    public void testRetrieveUserOrderById_ShouldReturnTrue_WhenDataIsCorrect() throws ServiceException {
        Optional <UserOrder> userOrder = Optional.of(new UserOrder(1,"г. Москва, ул. Лилий д. 22",new Date(),new Date(),"ожидается"));
        Mockito.when(userOrderService.retrieveUserOrderById(1)).thenReturn(userOrder);
        Optional<UserOrder> actual = userOrderService.retrieveUserOrderById(1);
        assertTrue(actual.isPresent());
    }

    @Test
    public void testRetrieveUserOrderById_ShouldReturnFalse_WhenDataIsNotCorrect() throws ServiceException {
        Optional <UserOrder> userOrder = Optional.empty();
        Mockito.when(userOrderService.retrieveUserOrderById(10)).thenReturn(userOrder);
        Optional<UserOrder> actual = userOrderService.retrieveUserOrderById(10);
        assertFalse(actual.isPresent());
    }

}












/*
    @Test
    public void testRetrieveUserOrderByStatus_ShouldReturnEquals_WhenDataIsCorrect() throws ServiceException {
        String status = "ожидается";
        List<UserOrder> actual = userOrderService.retrieveUserOrderByStatus(status);

        UserOrder userOrderFirst = new UserOrder(1,"г. Москва, ул. Лилий д. 22",actual.get(0).getOrderDate(),actual.get(0).getDeliveryDate(),actual.get(0).getStatus());
        UserOrder userOrderSecond = new UserOrder(2,"г. Саект-Петербург, ул. Васильков д. 28",actual.get(1).getOrderDate(),actual.get(1).getDeliveryDate(),actual.get(1).getStatus());
        UserOrder userOrderThird = new UserOrder(3,"г. Воронеж, ул. Ромашек д. 28",actual.get(2).getOrderDate(),actual.get(2).getDeliveryDate(),actual.get(2).getStatus());

        List<UserOrder> expected = new LinkedList<>();
        expected.add(userOrderFirst);
        expected.add(userOrderSecond);
        expected.add(userOrderThird);

        assertEquals(expected, actual);
    }
*/