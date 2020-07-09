package View.Panels;

import Model.CardModelView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PracticePanel extends BoardPanel {

    public PracticePanel(boolean practiceMode) {
        super(practiceMode);
    }

    @Override
    protected void paintComponent(Graphics gx) {
        super.paintComponent(gx);
    }

    @Override
    public synchronized void summonedMinion(CardModelView view, int mode, int damage, int hp) {
        super.summonedMinion(view, mode, damage, hp);
    }

    @Override
    public void drawDamages(int i, int j, int attack1, int attack2) {
        super.drawDamages(i, j, attack1, attack2);
    }

    @Override
    public void drawPracticeDamage(int i, int j, int attack1, int attack2) {
//        super.drawPracticeDamage(i, j, attack1, attack2);
    }

    @Override
    public void drawTargetsForAttack(ArrayList<Integer> targets) {
        super.drawTargetsForAttack(targets);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
    }

    @Override
    public void AiTurn(boolean aiTurn) {
        super.AiTurn(aiTurn);
    }
}
