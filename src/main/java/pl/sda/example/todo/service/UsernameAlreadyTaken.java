package pl.sda.example.todo.service;

public class UsernameAlreadyTaken extends RuntimeException {
    public UsernameAlreadyTaken(String username) {
        super("username " + username + " already taken");
    }
}
