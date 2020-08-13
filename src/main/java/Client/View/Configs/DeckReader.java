package Client.View.Configs;


import Server.Model.Enums.Carts;

import java.util.*;

public class DeckReader {

    private List<Carts> friend;
    private List<Carts> enemy;

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

    public List<Carts> getFriend() {
        return friend;
    }

    public void setFriend(List<Carts> friend) {
        this.friend = friend;
    }

    public List<Carts> getEnemy() {
        return enemy;
    }

    public void setEnemy(List<Carts> enemy) {
        this.enemy = enemy;
    }
}
