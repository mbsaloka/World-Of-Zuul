public class CommandWords {
  private static final String[] validCommands = {"go", "quit", "help", "look"};

  public boolean isCommand(String command) {
      for (String validCommand : validCommands) {
          if (validCommand.equals(command)) {
              return true;
          }
      }
      return false;
  }

  public void showAll() {
      for (String command : validCommands) {
          System.out.print(command + " ");
      }
      System.out.println();
  }
}
