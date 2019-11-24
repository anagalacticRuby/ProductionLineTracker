package io.github.anagalacticruby;

class Employee {
  private StringBuilder name;
  private String username;
  private final String password;
  private String email;

  /**
   * A mutator that sets the name of the employee to the one input by a user.
   *
   * @param name the name input
   */
  private void setName(StringBuilder name) {
    this.name = name;
  }

  /**
   * A mutator that sets the username of the employee to the first letter of the first name.
   * concatenated with the employee's last name.
   *
   * @param username the employee's username
   */
  private void setUsername(String username) {
    String[] lastNames = username.split(" ");
    this.username = (username.charAt(0) + lastNames[1]).toLowerCase();
  }

  /**
   * A mutator that sets the employee's email to the employee's first
   * name.theirlastname@oracleacademy.Test
   *
   * @param email the employee's email
   */
  private void setEmail(String email) {
    String[] firstNames = email.split(" ");
    this.email = (firstNames[0] + "." + firstNames[1]).toLowerCase() + "@oracleacademy.Test";
  }

  /**
   * A boolean method that checks if the employee has set a valid password or not. And if not, it
   * sets one for them.
   *
   * @param password the password input by the employee
   * @return the employee's initial password
   */
  private boolean isValidPassword(String password) {
    int lowerCaseCount = 0;
    int upperCaseCount = 0;
    int specialCharCount = 0;
    for (Character a : password.toCharArray()) {
      if (Character.isLowerCase(a)) {
        lowerCaseCount++;
      } else if (!Character.isLetterOrDigit(a)) {
        specialCharCount++;
      } else if (Character.isUpperCase(a)) {
        upperCaseCount++;
      }
    }
    return lowerCaseCount != 0 && upperCaseCount != 0 && specialCharCount != 0;
  }

  /**
   * This method checks if the employee's name contains a space in it.
   *
   * @param name the employee's name input in the employee name text field
   * @return true if the employee's name contains a space in it
   */
  private boolean checkName(StringBuilder name) {
    return name.toString().contains(" ");
  }

  /**
   * A constructor for employee objects. For use in the Employee Tab.
   *
   * @param name the name input by the user in the employee name text field
   * @param password the password input by the user in the employee password field
   */
  // String first name + surname, String password
  Employee(String name, String password) {

    StringBuilder checkingUser = new StringBuilder(name);
    setName(checkingUser); // Note that the name field is of StringBuilder type
    if (checkName(checkingUser)) {
      setUsername(name);
      setEmail(name);

    } else {
      this.username = "default";
      this.email = "user@oracleacademy.Test";
    }
    if (isValidPassword(password)) {
      this.password = password;
    } else {
      this.password = "pw";
    }
  }

  /**
   * A toString method that prints the details of the employee's account.
   *
   * @return the string containing details about the employee
   */
  @Override
  public String toString() {
    return "Employee Details\n"
        + "Name : "
        + name
        + "\nUsername : "
        + username
        + "\nEmail : "
        + email
        + "\nInitial Password : "
        + password;
  }
}
