package by.my.onlineShop.service.impl;

import by.my.onlineShop.entity.Order;
import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceTest {
    private final OrderService orderService;
    OrderServiceTest(){
        orderService = Mockito.mock(OrderServiceImpl.class);
    }


    @Test
    public void testRetrieveOrdersByUser_ShouldReturnEquals_WhenDataIsCorrect() throws ServiceException {
        Order orderFirst = new Order(4,5,2,3,2);
        List<Order> expected = new LinkedList<>();
        expected.add(orderFirst);

        Mockito.when(orderService.retrieveOrdersByUser(2)).thenReturn(expected);
        List<Order> actual = orderService.retrieveOrdersByUser(2);
        assertEquals(expected, actual);
    }

    @Test
    public void testRetrieveOrdersByUserWithoutUserOrder_ShouldReturnEquals_WhenDataIsCorrect() throws ServiceException {
        List<Order> expected = new LinkedList<>();
        Mockito.when(orderService.retrieveOrdersByUser(1)).thenReturn(expected);
        List<Order> actual = orderService.retrieveOrdersByUserWithoutUserOrder(1);
        assertEquals(expected, actual);
    }


    @Test
    public void testRetrieveOrdersByUserOrder_ShouldReturnEquals_WhenDataIsCorrect() throws ServiceException {
        Order orderFirst = new Order(3,2,3,2,1);
        List<Order> expected = new LinkedList<>();
        expected.add(orderFirst);

        Mockito.when(orderService.retrieveOrdersByUserOrder(2)).thenReturn(expected);

        List<Order> actual = orderService.retrieveOrdersByUserOrder(2);
        assertEquals(expected, actual);
    }

}
