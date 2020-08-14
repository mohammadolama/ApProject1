package Server.Controller.MainLogic;

import Server.Controller.Manager.Managers;
import Server.Model.CardModelView;
import Server.Model.Cards.Card;
import Server.Model.Cards.Minion;
import Server.Model.Cards.Spell;
import Server.Model.Cards.Weapon;
import Server.Model.Enums.Type;

class CardModelViewGetter {

    public CardModelView getWeaponViewModel(Weapon weapon) {
        if (weapon != null) {
            return new CardModelView(weapon.getName(), weapon.getManaCost(), weapon.getDamage(), weapon.getDurability(), Type.Weapon, null, false, false, false, false);
        }
        return null;
    }

    CardModelView getPureViewModelOf(String string, Card cards, Managers managers) {
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
