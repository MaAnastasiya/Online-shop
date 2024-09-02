package by.my.onlineShop.controller.command;

import by.my.onlineShop.controller.command.impl.*;
import by.my.onlineShop.controller.command.impl.transition.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandFactoryTest {

    @Test
    public void testCreateCommand_ShouldReturnAddToBasketCommand_WhenCommandIsAddToBasket() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getAddToBasketCommand());
        assertEquals(actual.getClass(), AddToBasketCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnCompleteOrderCommand_WhenCommandIsCompleteUserOrder() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getCompleteUserOrderCommand());
        assertEquals(actual.getClass(), CompleteOrderCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnConfirmAddingProductCommand_WhenCommandIsConfirmAddingProduct() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getConfirmAddingProductCommand());
        assertEquals(actual.getClass(), ConfirmAddingProductCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnConfirmAddingPromotionCommand_WhenCommandIsConfirmAddingPromotion() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getConfirmAddingPromotionCommand());
        assertEquals(actual.getClass(), ConfirmAddingPromotionCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnConfirmOrderCommand_WhenCommandIsConfirmOrder() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getConfirmOrderCommand());
        assertEquals(actual.getClass(), ConfirmOrderCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnConfirmProductChangeCommand_WhenCommandIsConfirmProductChange() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getConfirmEditProduct());
        assertEquals(actual.getClass(), ConfirmProductChangeCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnDeleteOrderCommand_WhenCommandIsDeleteOrder() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getDeleteOrderCommand());
        assertEquals(actual.getClass(), DeleteOrderCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnDeleteUserOrderCommand_WhenCommandIsDeleteUserOrder() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getDeleteUserOrderCommand());
        assertEquals(actual.getClass(), DeleteUserOrderCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnLogInCommand_WhenCommandIsCheckLogin() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getCheckLoginCommand());
        assertEquals(actual.getClass(), LogInCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnLogOutCommand_WhenCommandIsLogOut() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getLogOutCommand());
        assertEquals(actual.getClass(), LogOutCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnLogUpCommand_WhenCommandIsRegistration() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getRegistrationCommand());
        assertEquals(actual.getClass(), LogUpCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToAddOrderCommand_WhenCommandIsAddOrder() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getAddOrderCommand());
        assertEquals(actual.getClass(), GoToAddOrderCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToAddProductCommand_WhenCommandIsAddProduct() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getAddProductCommand());
        assertEquals(actual.getClass(), GoToAddProductCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToAddPromotionCommand_WhenCommandIsAddPromotion() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getAddPromotionCommand());
        assertEquals(actual.getClass(), GoToAddPromotionCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToBasketCommand_WhenCommandIsBasket() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getBasketCommand());
        assertEquals(actual.getClass(), GoToBasketCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToCatalogCommand_WhenCommandIsCatalog() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getCatalogCommand());
        assertEquals(actual.getClass(), GoToCatalogCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToContactsCommand_WhenCommandIsContacts() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getContactsCommand());
        assertEquals(actual.getClass(), GoToContactsCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToEditProductCommand_WhenCommandIsEditProduct() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getEditProductCommand());
        assertEquals(actual.getClass(), GoToEditProductCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToLogInCommand_WhenCommandIsLogIn() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getLogInCommand());
        assertEquals(actual.getClass(), GoToLogInCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToLogUpCommand_WhenCommandIsLogUp() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getLogUpCommand());
        assertEquals(actual.getClass(), GoToLogUpCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToMainCommand_WhenCommandIsMain() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getMainCommandCommand());
        assertEquals(actual.getClass(), GoToMainCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToMyOrdersCommand_WhenCommandIsMyOrders() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getMyOrdersCommand());
        assertEquals(actual.getClass(), GoToMyOrdersCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToProfileCommand_WhenCommandIsProfile() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getProfileCommand());
        assertEquals(actual.getClass(), GoToProfileCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToPromotionsCommand_WhenCommandIsPromotions() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getPromotionsCommand());
        assertEquals(actual.getClass(), GoToPromotionsCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToViewOrdersCommand_WhenCommandIsViewOrders() {
        Command actual = CommandFactory.getInstance().getCommand(new CommandName().getViewOrdersCommand());
        assertEquals(actual.getClass(), GoToViewOrdersCommand.class);
    }
}