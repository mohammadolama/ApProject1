package Controller;

import View.Sounds.SoundAdmin;
import View.Update.Update;

public class RequestHandler {
    private static Admin admin = Admin.getInstance();

    public RequestHandler() {
        admin = Admin.getInstance();
    }

    public enum SendRequest {

        /* Common Requests */
        FrameRender, Player, FriendlyHeroName, EnemyHeroName, PlaySound, LogOut, LogIn, SignUp, LvlUp, UnlockHeros,
        FinishGame,

        /* Collection Panel */
        UpdateDrawingPanel, CreateNewDeck, GetCardOf,

        /* InfoPassive Requests */
        PassiveList,

        /* Play Requests*/
        FriendlyName, EnemyName,
        DeckAnimationPicture, DownPowerUsage, UpPowerUsage, DownPowerMana, UpPowerMana, CanDoAction, SetSleep,
        GameLog, GameLogSize, DownDeckSize, UpDeckSize, EndTurn, Log, VisiblePanel, Exit, PureModelView, ModelView,
        DownHp, UpHp, FriendlyPlayedCard, EnemyPlayedCard, FriendlyHandCard, FrinedlyHandSize, EnemyHandSize,
        FriendlyPlayedSize, EnemyPlayedSize, FriendlyPlayedCardName, EnemyPlayedCardName, FriendlyHandCradName,
        CanBePlayed, CheckMana, PlayCard, PlayHeroPower, DownHasWeapon, UpHasWeapon,
        DownWeapon, UpWeapon, FriendlyTotalMana, FriendlyNotUsedMana, DeckHasNext, DownDefence, UpDefence,
        HeroPoerModelView, FriendlyActionModel, EnemyActionModel, HeroPowerCanBePlayed, AylarAction,

        /* Status */
        BestDecks, DeleteAccount, CloneDeck, SaveAndUpdate,

        /* SHOP */
        ProperCards, Wallet, Price, SellCard, BuyCard,

        /* Setting */
        IncreaseSound, DecreaseSound, MuteSound;

        @SuppressWarnings("unchecked")
        public <T> T response(String value) {
            switch (this) {
                case FriendlyHeroName:
                    return (T) admin.friendlyHeroName();
                case EnemyHeroName:
                    return (T) admin.enemyHeroName();
                case FriendlyName:
                    return (T) Admin.getInstance().friendlyPlayerName();
                case EnemyName:
                    return (T) Admin.getInstance().enemyPlayerName();

                /**/
                case DeckAnimationPicture:
                    return (T) Admin.getInstance().deckAnimationCard(value);


                case DownPowerUsage:
                    return (T) Integer.valueOf(admin.friendlyHeroPowerusedTimes());
                case UpPowerUsage:
                    return (T) Integer.valueOf(admin.enemyHeroPowerusedTimes());
                case DownPowerMana:
                    return (T) (admin.friendlyPowerMana() + "");
                case UpPowerMana:
                    return (T) (admin.enemyPowerMana() + "");
                case GameLog:
                    return (T) admin.gameLog();
                case GameLogSize:
                    return (T) Integer.valueOf(admin.gameLog().size());
                case UpDeckSize:
                    return (T) (admin.enemyDeckCards().size() + "");
                case DownDeckSize:
                    return (T) (admin.friendlyDeckCards().size() + "");
                case DownHp:
                    return (T) (admin.friendlyHero().getHealth() + "");
                case UpHp:
                    return (T) (admin.enemyHero().getHealth() + "");
                case DownDefence:
                    return (T) (admin.friendlyHero().getDefence() + "");
                case UpDefence:
                    return (T) (admin.enemyHero().getDefence() + "");
                case DownWeapon:
                    return (T) admin.getWeaponViewModel("friendly");
                case UpWeapon:
                    return (T) admin.getWeaponViewModel("enemy");
                case FriendlyNotUsedMana:
                    return (T) Integer.valueOf(admin.friendlNotUsedmana());
                case FriendlyTotalMana:
                    return (T) Integer.valueOf(admin.friendlyTotalMana());
                case FrinedlyHandSize:
                    return (T) Integer.valueOf(admin.friendlyHandCards().size());
                case EnemyHandSize:
                    return (T) Integer.valueOf(admin.enemyHandCards().size());
                case FriendlyPlayedSize:
                    return (T) Integer.valueOf(admin.friendlyPlayedCards().size());
                case EnemyPlayedSize:
                    return (T) Integer.valueOf(admin.enemyPlayedCards().size());
                case Wallet:
                    return (T) Long.valueOf(admin.wallet());
                case Price:
                    return (T) Long.valueOf(admin.price(value));
                case Player:
                    return (T) admin.player();
                case CloneDeck:
                    return (T) admin.CloneDeck(value);
                case PassiveList:
                    return (T) admin.generatePassive();
                case GetCardOf:
                    return (T) admin.getCardOf(value);
                case PlaySound:
                    admin.playSound(value);
                    return null;
                case HeroPowerCanBePlayed:
                    return (T) Integer.valueOf(admin.heroPowerCanBePlayed());
                case BestDecks:
                    return (T) admin.bestDecks();
                case PureModelView:
                    return (T) admin.getPureViewModelOf(value);
                case FriendlyActionModel:
                    return (T) admin.friendlyActionModels();
                case EnemyActionModel:
                    return (T) admin.enemyActionModels();
                case AylarAction:
                    admin.aylarAction(value);
                    return null;
                case UpdateDrawingPanel:
                    admin.updateDrawingPanel(value);
                    return null;
                case FinishGame:
                    admin.finishGame();
                    return null;
                case CreateNewDeck:
                    admin.createNewDeck();
                    return null;
                case LvlUp:
                    admin.levelUp();
                    return null;
                case UnlockHeros:
                    admin.unlockHero();
                    return null;
                case SellCard:
                    admin.sellCard(value);
                    return null;
                case BuyCard:
                    admin.buyCard(value);
                    return null;
                case SaveAndUpdate:
                    admin.saveAndUpdate();
                    Update.refresh();
                    return null;
                case FrameRender:
                    Update.render();
                    return null;
                case DeleteAccount:
                    admin.deleteAccount();
                    return null;
                case EndTurn:
                    admin.endTurn();
                    return null;
                case Log:
                    admin.Log(value);
                    return null;
                case VisiblePanel:
                    Update.render();
                    admin.setVisiblePanel(value);
                    return null;
                case Exit:
                    admin.exit();
                    return null;
                case LogOut:
                    admin.logOut();
                    return null;
                default:
                    return null;
            }
        }


        @SuppressWarnings("unchecked")
        public <T> T response(String value, int i) {
            switch (this) {
                case FriendlyPlayedCard:
                    return (T) admin.pictureOf(admin.friendlyPlayedCards().get(i).getName().toLowerCase());
                case EnemyPlayedCard:
                    return (T) admin.pictureOf(admin.enemyPlayedCards().get(i).getName().toLowerCase());
                case FriendlyPlayedCardName:
                    return (T) (admin.friendlyPlayedCards().get(i).getName());
                case EnemyPlayedCardName:
                    return (T) (admin.enemyPlayedCards().get(i).getName());
                case FriendlyHandCradName:
                    return (T) (admin.friendlyHandCards().get(i).getName());
                case PlayHeroPower:
                    admin.playHeroPower(i);
                    return null;
                case SetSleep:
                    admin.setSleep(i);
                    return null;
                case FriendlyHandCard:
                    return (T) admin.pictureOf(admin.friendlyHandCards().get(i).getName().toLowerCase());
                case ModelView:
                    return (T) admin.getViewModelOf(value, i);
                case HeroPoerModelView:
                    return (T) admin.getHeroPowerViewModelOf(i);
                case IncreaseSound:
                    SoundAdmin.increaseSound();
                    return null;
                case DecreaseSound:
                    SoundAdmin.decreaseSound();
                    return null;
                case MuteSound:
                    SoundAdmin.stopStart(i);
                    return null;
                case ProperCards:
                    return (T) admin.properCards(i);
                default:
                    return null;
            }
        }

        public <T> T response(String value, int i, int target) {
            if (this == SendRequest.PlayCard) {
                admin.playCard(value, i, target);
                return null;
            }
            return null;
        }

        public <T> T response(String value1, String value2) {
            switch (this) {
                case LogIn:
                    admin.logIn(value1, value2);
                    return null;
                case SignUp:
                    admin.signUp(value1, value2);
                    return null;
                default:
                    return null;
            }
        }

        public boolean response(int i, String value) {
            switch (this) {
                case DownHasWeapon:
                    return (admin.friendlyWeapon() != null);
                case UpHasWeapon:
                    return admin.enemyWeapon() != null;
                case CanBePlayed:
                    return admin.canBePlayed(value);
                case CheckMana:
                    return admin.ManaChecker(value);
                case DeckHasNext:
                    return admin.enemyDeckCards().size() > 0;
                case CanDoAction:
                    return admin.canDoAction(i);
                default:
                    return false;
            }
        }
    }
}
