package Configs;

import Model.Enums.Carts;

import java.util.ArrayList;

public class DeckReader {

    private ArrayList<Carts> friend;
    private ArrayList<Carts> enemy;

    public DeckReader() {
        friend = new ArrayList<>();
        enemy = new ArrayList<>();
        initilize();
    }

    void initilize() {
//        friend.add(Carts.learnjavadonic);
//        friend.add(Carts.bookofspecters);
//        friend.add(Carts.hosseinhima);
//        friend.add(Carts.aghahaghi);
//        friend.add(Carts.lachin);
//        friend.add(Carts.quiz);
//        friend.add(Carts.sprint);
//        friend.add(Carts.blessingoftheancients);
//        friend.add(Carts.aylar);
//        friend.add(Carts.matin);
//        friend.add(Carts.soroush);
//        friend.add(Carts.polymorph);
//        friend.add(Carts.sprint);
//        friend.add(Carts.faeze);
//        friend.add(Carts.shahryar);
//        friend.add(Carts.khashayar);
//        friend.add(Carts.mobin);
//        friend.add(Carts.ali);
//        friend.add(Carts.highmastersaman);
//        friend.add(Carts.benyamin);
//        friend.add(Carts.hossein);
//        friend.add(Carts.yasaman);
//        friend.add(Carts.nima);
//
//        enemy.add(Carts.strengthinnumbersdr);
//        enemy.add(Carts.benyamin);
//        enemy.add(Carts.fierywaraxe);
//        enemy.add(Carts.swarmofcats);
//        enemy.add(Carts.mobin);
//        enemy.add(Carts.ali);
//        enemy.add(Carts.highmastersaman);
//        enemy.add(Carts.nima);
//        enemy.add(Carts.yasaman);
//        enemy.add(Carts.faeze);
//        enemy.add(Carts.aghahaghi);
//        enemy.add(Carts.lachin);
    }


    public ArrayList<Carts> getFriend() {
        return friend;
    }

    public void setFriend(ArrayList<Carts> friend) {
        this.friend = friend;
    }

    public ArrayList<Carts> getEnemy() {
        return enemy;
    }

    public void setEnemy(ArrayList<Carts> enemy) {
        this.enemy = enemy;
    }
}
