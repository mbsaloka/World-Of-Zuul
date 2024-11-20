public class Game {
  private final Parser parser;
  private Room currentRoom;

  public Game() {
      createRooms();
      parser = new Parser();
  }

  private void createRooms() {
      Room outside, theater, pub, lab, office;

      outside = new Room("outside the main entrance of the university");
      theater = new Room("in a lecture theater");
      pub = new Room("in the campus pub");
      lab = new Room("in a computing lab");
      office = new Room("in the admin office");

      outside.setExit("east", theater);
      outside.setExit("south", lab);
      outside.setExit("west", pub);

      theater.setExit("west", outside);

      pub.setExit("east", outside);

      lab.setExit("north", outside);
      lab.setExit("east", office);

      office.setExit("west", lab);

      currentRoom = outside;
  }

  public void play() {
      printWelcome();

      boolean finished = false;
      while (!finished) {
          Command command = parser.getCommand();
          finished = processCommand(command);
      }
      System.out.println("Thank you for playing. Goodbye!");
  }

  private void printWelcome() {
      System.out.println("Welcome to the World of Zuul!");
      System.out.println("Type 'help' if you need help.");
      System.out.println();
      System.out.println(currentRoom.getDescription());
      System.out.println(currentRoom.getExitString());
  }

  private boolean processCommand(Command command) {
      if (command.isUnknown()) {
          System.out.println("I don't know what you mean...");
          return false;
      }

      String commandWord = command.getCommandWord();
      switch (commandWord) {
          case "help":
              printHelp();
              break;
          case "go":
              goRoom(command);
              break;
          case "quit":
              return quit(command);
          case "look":
              System.out.println(currentRoom.getDescription());
              System.out.println(currentRoom.getExitString());
              break;
      }
      return false;
  }

  private void printHelp() {
      System.out.println("You are lost. You are alone.");
      System.out.println("Your command words are:");
      parser.showCommands();
  }

  private void goRoom(Command command) {
      if (!command.hasSecondWord()) {
          System.out.println("Go where?");
          return;
      }

      String direction = command.getSecondWord();
      Room nextRoom = currentRoom.getExit(direction);

      if (nextRoom == null) {
          System.out.println("There is no door!");
      } else {
          currentRoom = nextRoom;
          System.out.println(currentRoom.getDescription());
          System.out.println(currentRoom.getExitString());
      }
  }

  private boolean quit(Command command) {
      if (command.hasSecondWord()) {
          System.out.println("Quit what?");
          return false;
      }
      return true;
  }

  public static void main(String[] args) {
      Game game = new Game();
      game.play();
  }
}
