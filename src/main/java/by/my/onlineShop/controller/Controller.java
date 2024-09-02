package by.my.onlineShop.controller;

import by.my.onlineShop.controller.command.Command;
import by.my.onlineShop.controller.command.CommandFactory;
import by.my.onlineShop.controller.command.CommandResult;
import by.my.onlineShop.controller.context.RequestContextHelper;
import by.my.onlineShop.dao.connection.ConnectionPool;
import by.my.onlineShop.exeptions.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);

    private static final String COMMAND = "command";
    private static final String PATH = "/online_shop_war/online-shop?";
    private static final String MAIN_COMMAND = "command=main";

    @Override
    public void init() throws ServletException { // выгрузка БД
        super.init();
        try {
            ConnectionPool.getInstance().initialize();
        } catch (ConnectionException e) {
            logger.error("Servlet wasn't init!");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() { //выгрузка из памяти все созданные экземпляры сервлетов
        try {
            ConnectionPool.getInstance().destroy();
            super.destroy();
        } catch (ConnectionException e) {
            logger.error("Servlet wasn't destroy!");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandName = request.getParameter(COMMAND);// определяем команду

        if (commandName == null || "".equals(commandName)) { // нет команды на переход, значит глав стр
            response.sendRedirect(PATH + MAIN_COMMAND);
        } else { //переход на другую стр по команде
            Command command = CommandFactory.getInstance().getCommand(commandName);
            RequestContextHelper contextHelper = new RequestContextHelper(request);

            CommandResult result = command.execute(contextHelper, response);
            dispatch(result, request, response);
        }
    }

    private void dispatch(CommandResult result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //отправка на новую страницу
        if (result.isRedirect()) {
            response.sendRedirect(PATH + result.getPage());
        } else {
            request.getRequestDispatcher(result.getPage()).forward(request, response);
        }
    }
}