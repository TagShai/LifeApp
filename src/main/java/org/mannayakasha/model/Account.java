package org.mannayakasha.model;

/**
 * Simple JavaBean object, that represents entity Account
 *
 * @author Pavel
 * @version 1.0 28.08.2017.
 */
public class Account extends Entity {
    private String username;
    private String password;
    private String confirmPassword;
    private AccessType accessType;

    public Account() {}

    public Account(int id, String username, String password, AccessType accessType) {
        super(id);
        this.username = username;
        this.password = password;
        this.accessType = accessType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public AccessType getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        if (accessType.toUpperCase().equals("ADMIN")) {
            this.accessType = AccessType.ADMIN;
        } else if (accessType.toUpperCase().equals("USER"))  {
            this.accessType = AccessType.USER;
        }
    }

    // TODO: 28.08.2017 equals(), hashcode()

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", accessType=" + accessType +
                '}';
    }
}
