public class Command {
  private final String commandWord;
  private final String secondWord;

  public Command(String commandWord, String secondWord) {
      this.commandWord = commandWord;
      this.secondWord = secondWord;
  }

  public String getCommandWord() {
      return commandWord;
  }

  public String getSecondWord() {
      return secondWord;
  }

  public boolean hasSecondWord() {
      return secondWord != null;
  }

  public boolean isUnknown() {
      return commandWord == null;
  }
}
