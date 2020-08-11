package Client.View.View.MouseListeners;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.*;
import Client.Model.CardModelView;
import Client.Model.Images;
import Client.View.View.Panels.BoardPanel;
import Client.View.View.Update.Update;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoardMouseListener implements MouseListener {
    private BoardPanel b;

    public BoardMouseListener(BoardPanel b) {
        this.b = b;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (b.isDisabled()) {
            return;
        }
        int x = e.getX();
        int y = e.getY();
        if (y >= b.getConfig().getPlayerHeroPowerY()) {
            if (!b.isCardSelected()) {
                b.setMouseStartX(x);
                b.setMouseStartY(y);
            }
            if (x >= 750 && x <= 850 && y >= 700 && y <= 850) {
                RequestHandler.getInstance().sendRequest(new HeroCanAttackRequest());
                if (b.getRes().isHeroCanAttack()) {
                    b.setPlayedIndex(-1);
                    RequestHandler.getInstance().sendRequest(new TargetListRequest(b));
                }
            } else if (x >= b.getConfig().getPlayerHeroPowerX() && x <= (b.getConfig().getPlayerHeroPowerX() + b.getConfig().getHeroPoerWidth()) && y >= b.getConfig().getPlayerHeroPowerY() && y <= (b.getConfig().getPlayerHeroPowerY() + b.getConfig().getHeroPowerHeight())) {
                playHeroPower();
            } else if (y >= b.getConfig().getPlayerHandY()) {
                selectCard(x, y);
            }
        } else if (y >= b.getConfig().getMiddleLineY() && y < b.getConfig().getPlayerHeroPowerY()) {
            if (b.isCardSelected()) {
                b.setMouseDesX(x);
                b.setMouseDesY(y);
                if (x >= b.getConfig().getLeftLineX() && x <= b.getConfig().getRightLineX() && y >= b.getConfig().getMiddleLineY() && y <= b.getConfig().getPlayAreaY()) {
                    cardSelectedAction(x, y);
                }
                b.setCardSelected(false);
            } else {
                for (Images image : b.getFriendlyPlayedImages()) {
                    if (x >= image.getX() && x <= image.getX() + image.getWidth() && y >= image.getY() && y <= image.getY() + image.getHeigth()) {
                        RequestHandler.getInstance().sendRequest(new CanDoActionRequest(image.getIndex()));
                        boolean flag = b.getRes().isCanDoAction();
                        if (flag) {
                            b.setPlayedCardSelected(true);
                            RequestHandler.getInstance().sendRequest(new TargetListRequest(b));
                            b.setPlayedCardSelectedName(image.getName());
                            b.setPlayedIndex(image.getIndex());
                            return;
                        }
                    }
                }
                b.removeButton();
                b.setPlayedCardSelected(false);
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (b.isDisabled()) {
            return;
        }
        b.getCardPreview().setVisible(false);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (b.isDisabled()) {
            return;
        }
//        Update.render();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (b.isDisabled()) {
            return;
        }
        if (e.getSource() instanceof JButton) {
            b.setFlag(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (b.isDisabled()) {
            return;
        }
        if (e.getSource() instanceof JButton) {
            b.setFlag(false);
        }

    }


    private void playHeroPower() {
        RequestHandler.getInstance().sendRequest(new HeroPowerCanBePlayedRequest());
        int i = b.getRes().getHeroPowerCanBePlayed();
        switch (i) {
            case 1:
                b.drawFriendlyTargetsForHeroPower();
                break;
            case 2:
                b.drawEnemmyTargetForHeroPower();
                break;
            case 3:
                RequestHandler.getInstance().sendRequest(new PlayHeroPowerRequest(0));
                break;
            case 0:
                break;
        }
    }

    private void selectCard(int x, int y) {
        int k = -2;
        int i = 0;
        String st = null;
        for (Images images : b.getHandImages()) {
            if ((x >= images.getX() && x <= images.getX() + images.getWidth()) && (y >= images.getY() && y <= images.getY() + images.getHeigth())) {
                st = images.getName();
                k = i;
                break;
            }
            i++;
        }
        if (k == -2) {
            return;
        }
        int mana = b.getRes().board.getNotUsedMana();
        CardModelView view = b.getRes().board.getHandCards().get(k);
        if (mana >= view.getManaCost()) {
            b.setHandCardSelectedName(st);
            b.setCardSelected(true);
        }
//        Update.render();
    }

    private void cardSelectedAction(int x, int y) {
        new Thread(() -> {
            b.setDeckIndex(0);
            if (b.getFriendlyPlayedImages().size() > 0) {
                if (x <= b.getFriendlyPlayedImages().get(0).getX()) {
                    b.setDeckIndex(0);
                } else if (x >= b.getFriendlyPlayedImages().get(b.getFriendlyPlayedImages().size() - 1).getX()) {
                    b.setDeckIndex(b.getFriendlyPlayedImages().size());
                } else {
                    for (int j = 0; j < b.getFriendlyPlayedImages().size() - 1; j++) {

                        if ((x > (b.getFriendlyPlayedImages().get(j).getX() +
                                b.getFriendlyPlayedImages().get(j).getWidth() - 10))
                                && (x < (b.getFriendlyPlayedImages().get(j + 1).getX() + 15))) {
                            b.setDeckIndex(j + 1);
                            break;
                        }
                    }
                }
            }
            RequestHandler.getInstance().sendRequest(new CanBePlayedRequest(b.getHandCardSelectedName()));
            boolean flag = b.getRes().isCanBePlayed();
            RequestHandler.getInstance().sendRequest(new PureModelViewRequest(b.getHandCardSelectedName()));
            CardModelView view = b.getRes().getCardModelView();
            boolean s = false;
            if (view.isNeedEnemyTarget()) {
                b.selectEnemyTargetForCardActions();
                s = true;
            }
            if (view.isNeedFriendlyTarget()) {
                b.selectFriendlyTarget();
                s = true;
            }
            if (s) {
                synchronized (b.getConfig()) {
                    try {
                        b.getConfig().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (flag) {
                b.getConfig().setPlayAnimation(true);
                b.setX1(b.getMouseStartX());
                b.setY1(b.getMouseStartY());
                b.getPlayTimer().start();
            }
        }).start();

    }


}
