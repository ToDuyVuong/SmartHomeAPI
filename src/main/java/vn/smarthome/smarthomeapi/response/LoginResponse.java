package vn.smarthome.smarthomeapi.response;

import vn.smarthome.smarthomeapi.entity.User;

public class LoginResponse {
    private User user;
    private String message;

    public void setUser(User user) {
        this.user = user;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public LoginResponse(User user, String message) {
        this.user = user;
        this.message = message;
    }
}
