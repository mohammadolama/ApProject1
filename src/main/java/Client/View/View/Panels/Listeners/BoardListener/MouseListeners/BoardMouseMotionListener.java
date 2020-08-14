package Client.View.View.Panels.Listeners.BoardListener.MouseListeners;

import Client.Controller.Responses;
import Client.Model.CardModelView;
import Client.Model.Images;
import Client.View.View.Panels.BoardPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class BoardMouseMotionListener implements MouseMotionListener {
    private BoardPanel b;

    public BoardMouseMotionListener(BoardPanel b) {
        this.b = b;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (b.isDisabled()) {
            return;
        }
        b.getCardPreview().setVisible(false);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (b.isDisabled()) {
            return;
        }

        b.getCardPreview().setVisible(false);
        int x = e.getX();
        int y = e.getY();
        if (x >= 1500 && y >= 50 && y <= 150) {
            b.getActionChartPanel().setVisible(true);
        } else {
            b.getActionChartPanel().setVisible(false);
            if (y >= b.getConfig().getPlayerHandY()) {
                friendlyHandView(x, y);
            } else if (y < b.getConfig().getPlayerHandY()) {
                if (y > b.getConfig().getMiddleLineY()) {
                    friendlyPlayedView(x, y);
                } else {
                    enemyPlayedView(x, y);
                }
            }
        }
    }

    private void friendlyHandView(int x, int y) {
        int k = -2;
        int i = 0;
        for (Images images : b.getHandImages()) {
            if ((x >= images.getX() && x <= images.getX() + images.getWidth()) && (y >= images.getY() && y <= images.getY() + images.getHeigth())) {
                k = i;
                break;
            }
            i++;
        }
        if (k == -2) {
            return;
        }
        CardModelView model = b.getRes().board.getHandCards().get(k);
        b.getCardPreview().setCardModelView(model);
        b.getCardPreview().setBounds(x - 50, y - 360, 260, 370);
        b.getCardPreview().setVisible(true);
    }

    private void friendlyPlayedView(int x, int y) {
        if (!b.isFlag()) {
            int i = -1;
            if (x >= 900 && x <= 1000 && y >= 700 && y <= 800) {
                i = -2;
            }
            if (i != -2) {
                for (Images images : b.getFriendlyPlayedImages()) {
                    if ((x >= images.getX() && x <= images.getX() + images.getWidth()) && (y >= images.getY() && y <= images.getY() + images.getHeigth())) {
                        i = images.getIndex();
                        break;
                    }
                }
            }
            if (i == (-1)) {
                return;
            }
            CardModelView view;
            if (i == -2) {
                view = new CardModelView(Responses.getInstance().board.getFriendlyHero().toLowerCase(), 0);
            } else {
                view = b.getRes().board.getDownPlayedCards().get(i);
            }
            b.getCardPreview().setCardModelView(view);
            b.getCardPreview().setBounds(x - 50, y - 360, 260, 370);
            b.getCardPreview().setVisible(true);
        }
    }

    private void enemyPlayedView(int x, int y) {
        if (!b.isFlag()) {
            int i = -1;
            for (Images images : b.getEnemyPlayedImages()) {
                if ((x >= images.getX() && x <= images.getX() + images.getWidth()) && (y >= images.getY() && y <= images.getY() + images.getHeigth())) {
                    i = images.getIndex();
                    break;
                }
            }
            if (i == (-1)) {
                return;
            }
            CardModelView view = b.getRes().board.getUpPlayedCards().get(i);
            b.getCardPreview().setCardModelView(view);
            b.getCardPreview().setBounds(x - 50, y, 260, 370);
            b.getCardPreview().setVisible(true);
        }
    }

}
