package Controller;

import Controller.Manager.Managers;
import Model.CardModelView;
import Model.Cards.Card;
import Model.Cards.Minion;
import Model.Cards.Spell;
import Model.Cards.Weapon;
import Model.Enums.Type;

import java.awt.image.BufferedImage;

class CardModelViewGetter {

    CardModelView getViewModelOf(String list, int index, Managers gameManager) {
        if (list.equalsIgnoreCase("friendly")) {
            Card cards = gameManager.getFriendlyPlayedCards().get(index);
            String string = cards.getName().toLowerCase();
            BufferedImage image = Admin.getInstance().pictureOf(string);
            if (cards instanceof Minion) {
                Minion minions = (Minion) cards;
                return new CardModelView(image, string, minions.getManaCost(), minions.getDamage(), minions.getHealth(), minions.getType(), minions.getAttributes(), minions.isSleep(), minions.isCanBeAttacked(), minions.isNeedFriendlyTarget(), minions.isNeedEnemyTarget());
            }
            return null;
        } else {
            Card cards = gameManager.getEnemyPlayedCards().get(index);
            String string = cards.getName().toLowerCase();
            BufferedImage image = Admin.getInstance().pictureOf(string);
            if (cards instanceof Minion) {
                Minion minions = (Minion) cards;
                return new CardModelView(image, string, minions.getManaCost(), minions.getDamage(), minions.getHealth(), minions.getType(), minions.getAttributes(), minions.isSleep(), minions.isCanBeAttacked(), minions.isNeedFriendlyTarget(), minions.isNeedEnemyTarget());
            }
            return null;
        }
    }

    CardModelView getWeaponViewModel(String string, Managers gameManger) {
        if (string.equalsIgnoreCase("friendly")) {
            Weapon weapon = gameManger.getFriendlyWeapon();
            BufferedImage image = Admin.getInstance().pictureOf(weapon.getName().toLowerCase());
            return new CardModelView(image, weapon.getName(), weapon.getManaCost(), weapon.getDamage(), weapon.getDurability(), Type.Weapon, null, false, false, false, false);
        } else {
            Weapon weapon = gameManger.getEnemyWeapon();
            BufferedImage image = Admin.getInstance().pictureOf(weapon.getName().toLowerCase());
            return new CardModelView(image, weapon.getName(), weapon.getManaCost(), weapon.getDamage(), weapon.getDurability(), Type.Weapon, null, false, false, false, false);
        }
    }

    CardModelView getPureViewModelOf(String string, Card cards, BufferedImage image, Managers gameManager) {
        int mana = cards.getManaCost();
        if (gameManager != null) {
            mana -= gameManager.getFriendlyManaDecrease();
        }
        if (cards instanceof Minion) {
            Minion minions = (Minion) cards;
            return new CardModelView(image, string, mana, minions.getDamage(), minions.getHealth(), minions.getType(), minions.getAttributes(), minions.isSleep(), minions.isCanBeAttacked(), minions.isNeedFriendlyTarget(), minions.isNeedEnemyTarget());
        } else if (cards instanceof Weapon) {
            Weapon weapon = (Weapon) cards;
            return new CardModelView(image, string, mana, weapon.getDamage(), weapon.getDurability(), weapon.getType(), null, false, false, false, false);
        } else {
            Spell spell = (Spell) cards;
            return new CardModelView(image, string, mana, spell.getType(), spell.isNeedFriendlyTarget(), spell.isNeedEnemyTarget());
        }
    }


}
