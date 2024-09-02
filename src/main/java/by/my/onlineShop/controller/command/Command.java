package by.my.onlineShop.controller.command;

import by.my.onlineShop.controller.context.RequestContextHelper;

import javax.servlet.http.HttpServletResponse;

public interface Command {

    // расшифровка запроса + сам запрос
    // возвращает - ответ на запрос с типом маршрутизации
    CommandResult execute(RequestContextHelper helper, HttpServletResponse response);
}
