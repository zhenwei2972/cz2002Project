/**
 * This is an interface of user
 * @author Team Stars
 * @version 1.0
 * @since 2020
 */
public interface User  {
    /**
     * set username
     * @param inputID the username for user
     */
    public void setUsername(String inputID);
    /**
     * set password
     * @param inputPW the password for user
     */
    public void setPassword(String inputPW);
    /**
     * getter function username
     * @return user username
     */
    public String getUsername();
    /**
     * gertter function password
     * @return user password
     */
    public String getPassword();
}
