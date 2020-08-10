package Server.Controller.MainLogic;

import Server.Controller.Manager.Managers;
import Server.Model.CardModelView;
import Server.Model.Cards.*;
import Server.Model.Enums.Type;

import java.util.*;

class CardModelViewGetter {

    CardModelView getViewModelOf(List<Card> list, int index) {
        Card cards = list.get(index);
        String string = cards.getName().toLowerCase();
        if (cards instanceof Minion) {
            Minion minions = (Minion) cards;
            return new CardModelView(string, minions.getManaCost(), minions.getDamage(),
                    minions.getHealth(), minions.getType(), minions.getAttributes(),
                    minions.isSleep(), minions.isCanBeAttacked(), minions.isNeedFriendlyTarget(),
                    minions.isNeedEnemyTarget());
        }
        return null;
    }

    public CardModelView getWeaponViewModel(Weapon weapon) {
        if (weapon != null) {
            return new CardModelView(weapon.getName(), weapon.getManaCost(), weapon.getDamage(), weapon.getDurability(), Type.Weapon, null, false, false, false, false);
        }
        return null;
    }

    CardModelView getPureViewModelOf(String string, Card cards, Managers managers) {
        int mana = cards.getManaCost();
        if (managers != null) {
//            mana -= managers.getFriendlyManaDecrease();
        }
        if (cards instanceof Minion) {
            Minion minions = (Minion) cards;
            return new CardModelView(string, mana, minions.getDamage(), minions.getHealth(), minions.getType(), minions.getAttributes(), minions.isSleep(), minions.isCanBeAttacked(), minions.isNeedFriendlyTarget(), minions.isNeedEnemyTarget());
        } else if (cards instanceof Weapon) {
            Weapon weapon = (Weapon) cards;
            return new CardModelView(string, mana, weapon.getDamage(), weapon.getDurability(), weapon.getType(), null, false, false, false, false);
        } else {
            Spell spell = (Spell) cards;
            return new CardModelView(string, mana, spell.getType(), spell.isNeedFriendlyTarget(), spell.isNeedEnemyTarget());
        }
    }

    CardModelView getPureViewModelOf(String string, Card cards) {
        int mana = cards.getManaCost();
        if (cards instanceof Minion) {
            Minion minions = (Minion) cards;
            return new CardModelView(string, mana, minions.getDamage(), minions.getHealth(), minions.getType(), minions.getAttributes(), minions.isSleep(), minions.isCanBeAttacked(), minions.isNeedFriendlyTarget(), minions.isNeedEnemyTarget());
        } else if (cards instanceof Weapon) {
            Weapon weapon = (Weapon) cards;
            return new CardModelView(string, mana, weapon.getDamage(), weapon.getDurability(), weapon.getType(), null, false, false, false, false);
        } else {
            Spell spell = (Spell) cards;
            return new CardModelView(string, mana, spell.getType(), spell.isNeedFriendlyTarget(), spell.isNeedEnemyTarget());
        }
    }

}
