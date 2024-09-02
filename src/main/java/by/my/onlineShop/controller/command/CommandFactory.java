package by.my.onlineShop.controller.command;

import by.my.onlineShop.controller.command.impl.*;
import by.my.onlineShop.controller.command.impl.transition.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandFactory { // соотносит с каждым названием команды действие
    private static final Map<String, Command> commands = new HashMap<>();

    private CommandFactory() {
        commands.put(new CommandName().getMainCommandCommand(), new GoToMainCommand());
        commands.put(new CommandName().getProfileCommand(), new GoToProfileCommand());
        commands.put(new CommandName().getRegistrationCommand(), new LogUpCommand());
        commands.put(new CommandName().getLogInCommand(), new GoToLogInCommand());
        commands.put(new CommandName().getContactsCommand(), new GoToContactsCommand());
        commands.put(new CommandName().getPromotionsCommand(), new GoToPromotionsCommand());
        commands.put(new CommandName().getCatalogCommand(), new GoToCatalogCommand());
        commands.put(new CommandName().getCheckLoginCommand(), new LogInCommand());
        commands.put(new CommandName().getLogOutCommand(), new LogOutCommand());
        commands.put(new CommandName().getLogUpCommand(), new GoToLogUpCommand());
        commands.put(new CommandName().getMyOrdersCommand(), new GoToMyOrdersCommand());
        commands.put(new CommandName().getBasketCommand(), new GoToBasketCommand());
        commands.put(new CommandName().getViewOrdersCommand(), new GoToViewOrdersCommand());
        commands.put(new CommandName().getAddProductCommand(), new GoToAddProductCommand());
        commands.put(new CommandName().getAddPromotionCommand(), new GoToAddPromotionCommand());
        commands.put(new CommandName().getAddOrderCommand(), new GoToAddOrderCommand());
        commands.put(new CommandName().getDeleteOrderCommand(), new DeleteOrderCommand());
        commands.put(new CommandName().getCompleteUserOrderCommand(), new CompleteOrderCommand());
        commands.put(new CommandName().getAddToBasketCommand(), new AddToBasketCommand());
        commands.put(new CommandName().getConfirmOrderCommand(), new ConfirmOrderCommand());
        commands.put(new CommandName().getConfirmAddingProductCommand(), new ConfirmAddingProductCommand());
        commands.put(new CommandName().getDeleteUserOrderCommand(), new DeleteUserOrderCommand());
        commands.put(new CommandName().getEditProductCommand(), new GoToEditProductCommand());
        commands.put(new CommandName().getConfirmEditProduct(), new ConfirmProductChangeCommand());
        commands.put(new CommandName().getConfirmAddingPromotionCommand(), new ConfirmAddingPromotionCommand());
    }

    public static CommandFactory getInstance() { // Получить экземпляр
        return Holder.INSTANCE;
    }

    // на вход - название команды. На выход - действие по этой команде
    public Command getCommand(String name) {
        return Optional.ofNullable(commands.get(name)).orElse(commands.get(new CommandName().getDefaultCommand()));
    }

    private static class Holder { // создает новый экземпляр
        static final CommandFactory INSTANCE = new CommandFactory();
    }

}
