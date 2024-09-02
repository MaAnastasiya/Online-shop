package by.my.onlineShop.controller.command;

public class CommandResult {
    private final String page; // страница
    private final CommandResultType type;// тип переадресации

    public CommandResult(String page, CommandResultType resultType) {
        this.page = page;
        type = resultType;
    }

    public boolean isRedirect() {
        return type == CommandResultType.REDIRECT;
    }

    public boolean isForward() {
        return type == CommandResultType.FORWARD;
    }

    public String getPage() {
        return page;
    }
}
