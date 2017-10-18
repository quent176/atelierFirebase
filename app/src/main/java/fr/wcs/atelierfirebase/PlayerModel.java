package fr.wcs.atelierfirebase;

public class PlayerModel {

    private String player;
    private String mdp;
    private int score;

    public PlayerModel(String player, String mdp, int score) {
        this.player = player;
        this.mdp = mdp;
        this.score = score;
    }

    public PlayerModel() {
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
