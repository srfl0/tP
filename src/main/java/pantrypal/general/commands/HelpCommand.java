package pantrypal.general.commands;


import pantrypal.general.control.Ui;

public class HelpCommand extends Command {
    private final Command[] COMMAND_LIST = new Command[] {
            new ExitCommand()
    };


    @Override
    public void execute(Ui ui) {
        ui.printHelpMessage(COMMAND_LIST);
    }

    @Override
    public String getCommandDescription() {
        return "";
    }

    @Override
    public String getCommandName() {
        return "";
    }
}
