package Users;

public class Base {
  protected String name;
  protected boolean adm;

  public boolean getPrivileges() {
    return this.adm;
  }

  public String getName() {
    return this.name;
  }
}
