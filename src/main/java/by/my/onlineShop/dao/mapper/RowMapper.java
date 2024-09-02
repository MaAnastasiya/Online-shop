package by.my.onlineShop.dao.mapper;

import by.my.onlineShop.entity.Identifiable;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends Identifiable> { // Составитель строк

    // Метод создания объекта сущности из результирующего набора
    //набор результатов param { @набор результатов ссылки} указатель,
    // для которого заданы необработанные данные, которые будут сопоставлены с объектом сущности
    // На выходе - объект сущности с полями, заданными из данных строки
    T map(ResultSet resultSet) throws SQLException;
}
