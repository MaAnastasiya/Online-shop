package by.my.onlineShop.service.impl;

import by.my.onlineShop.exeptions.ServiceException;
import by.my.onlineShop.service.BankCardService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BankCardServiceTest {
    private BankCardService bankCardService;
    BankCardServiceTest(){
        bankCardService = Mockito.mock(BankCardServiceImpl.class);
    }

    // инициализировать пул соединения
   /* @BeforeAll
    static void initializeConnectionPool() throws SQLException {
        Statement statement = Mockito.mock(Statement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        Mockito.when(statement.executeQuery("SELECT * FROM " + Table.BANK_CARD + " WHERE card_number=?")).thenReturn(resultSet);
        Connection jdbcConnection = Mockito.mock(Connection.class);
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);

    }*/

    // проверка, существует ли банковская карта
    // возвращает true, когда данные корректны
    @Test
    public void testIsBankCardExist_ShouldReturnTrue_WhenDataIsCorrect() throws ServiceException {
        Mockito.when(bankCardService.isBankCardExist(4276880099433504L, 11,
                2022, 291)).thenReturn(true);
        boolean actual = bankCardService.isBankCardExist(4276880099433504L, 11,
                2022, 291);
        assertTrue(actual);
    }

    // проверка, существует ли банковская карта
    // возвращает false, когда данные некорректны
    @Test
    public void testIsBankCardExist_ShouldReturnFalse_WhenDataIsNotCorrect() throws ServiceException {
        Mockito.when(bankCardService.isBankCardExist(4276880099433504L, 11,
                2022, 291)).thenReturn(false);
        boolean actual = bankCardService.isBankCardExist(4276880099433504L, 11,
                2022, 921);
        assertFalse(actual);
    }
}