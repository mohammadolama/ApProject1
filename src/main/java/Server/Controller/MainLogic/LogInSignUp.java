package Server.Controller.MainLogic;


import Server.Model.Account;
import Server.Model.Player;

public class LogInSignUp {

    public void DeleteAccount(Player player) {
        LOGGER.playerlog(player, "Account Deleted");
        DataBaseManagment.deleteAccount(player.getUsername());
    }

    public String create(String user, String pass) {
        if (DuplicateUserChecker(user)) {
            DataBaseManagment.saveAccount(new Account(user, pass));
            Player player = new Player(user, pass);
            DataBaseManagment.savePlayer(player);
            LOGGER.playerlog(player, "Account created");
            return "ok";
        }
        return "user already exist";
    }

    private boolean DuplicateUserChecker(String user) {
        Account account = DataBaseManagment.accountReader(user);
        return account == null;
    }

    public String check(String user, String password, ClientHandler clientHandler) {
        if (UserFinder(user)) {
            if (PassChecker(user, password)) {
                Player player = DataBaseManagment.PlayerJsonReader(user);
                clientHandler.setPlayer(player);
                LOGGER.playerlog(player, "Log_in");
                if (player.getNewToGame()) {
                    return "new player";
                } else {
                    return "welcome";
                }
            } else {
                return "wrong password";
            }
        } else {
            return "user not found";
        }
    }

    private boolean UserFinder(String user) {
        Account account = DataBaseManagment.accountReader(user);
        return account != null;
    }

    private boolean PassChecker(String username, String password) {
        Account account = DataBaseManagment.accountReader(username);
        return account.getPassword().equals(password);
    }
}