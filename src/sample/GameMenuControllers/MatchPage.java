package sample.GameMenuControllers;

import Classes.User;

public class MatchPage implements GameMenController{
    private User user;

    @Override
    public void setUser(User user) {
        this.user = user;
    }
}
