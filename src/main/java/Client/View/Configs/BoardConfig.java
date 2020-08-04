package Client.View.Configs;

public class BoardConfig {
    private int leftLineX;
    private int rightLineX;
    private int upLineY;
    private int middleLineY;
    private int bottomLineY;
    private int playAreaY;
    private int infoWidth;
    private int logX;
    private int logY;
    private int logSpace;
    private int playerHandX;
    private int opponentHandY;
    private int playerHandY;
    private int cardWidth;
    private int cardHeight;
    private int playerPlayedCardX;
    private int playerPlayedCardY;
    private int opponentPlayedCardY;
    private int playerHeroPowerX;
    private int playerHeroPowerY;
    private int opponentHeroPowerY;
    private int heroPoerWidth;
    private int heroPowerHeight;
    private int mouseX;
    private int mouseY;

    private int deckX;
    private int deckY;

    private int AiDeckX;
    private int AiDeckY;

    private int middleX;
    private int middleY;
    private int blur;
    private int maxWidth;
    private int maxHeigth;
    private int fps;
    private boolean animated;
    private boolean deckAnimationFinished;
    private boolean playAnimation;
    private boolean toMiddle;

    public BoardConfig() {
    }

    public boolean isPlayAnimation() {
        return playAnimation;
    }

    public void setPlayAnimation(boolean playAnimation) {
        this.playAnimation = playAnimation;
    }

    public boolean isToMiddle() {
        return toMiddle;
    }

    public void setToMiddle(boolean toMiddle) {
        this.toMiddle = toMiddle;
    }

    public int getBlur() {
        return blur;
    }

    public void setBlur(int blur) {
        this.blur = blur;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getMaxHeigth() {
        return maxHeigth;
    }

    public void setMaxHeigth(int maxHeigth) {
        this.maxHeigth = maxHeigth;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    public boolean isDeckAnimationFinished() {
        return deckAnimationFinished;
    }

    public void setDeckAnimationFinished(boolean deckAnimationFinished) {
        this.deckAnimationFinished = deckAnimationFinished;
    }

    public int getLeftLineX() {
        return leftLineX;
    }

    public void setLeftLineX(int leftLineX) {
        this.leftLineX = leftLineX;
    }

    public int getRightLineX() {
        return rightLineX;
    }

    public void setRightLineX(int rightLineX) {
        this.rightLineX = rightLineX;
    }

    public int getUpLineY() {
        return upLineY;
    }

    public void setUpLineY(int upLineY) {
        this.upLineY = upLineY;
    }

    public int getMiddleLineY() {
        return middleLineY;
    }

    public void setMiddleLineY(int middleLineY) {
        this.middleLineY = middleLineY;
    }

    public int getBottomLineY() {
        return bottomLineY;
    }

    public void setBottomLineY(int bottomLineY) {
        this.bottomLineY = bottomLineY;
    }

    public int getPlayAreaY() {
        return playAreaY;
    }

    public void setPlayAreaY(int playAreaY) {
        this.playAreaY = playAreaY;
    }

    public int getInfoWidth() {
        return infoWidth;
    }

    public void setInfoWidth(int infoWidth) {
        this.infoWidth = infoWidth;
    }

    public int getLogX() {
        return logX;
    }

    public void setLogX(int logX) {
        this.logX = logX;
    }

    public int getLogY() {
        return logY;
    }

    public void setLogY(int logY) {
        this.logY = logY;
    }

    public int getLogSpace() {
        return logSpace;
    }

    public void setLogSpace(int logSpace) {
        this.logSpace = logSpace;
    }

    public int getPlayerHandX() {
        return playerHandX;
    }

    public void setPlayerHandX(int playerHandX) {
        this.playerHandX = playerHandX;
    }

    public int getOpponentHandY() {
        return opponentHandY;
    }

    public void setOpponentHandY(int opponentHandY) {
        this.opponentHandY = opponentHandY;
    }

    public int getPlayerHandY() {
        return playerHandY;
    }

    public void setPlayerHandY(int playerHandY) {
        this.playerHandY = playerHandY;
    }

    public int getCardWidth() {
        return cardWidth;
    }

    public void setCardWidth(int cardWidth) {
        this.cardWidth = cardWidth;
    }

    public int getCardHeight() {
        return cardHeight;
    }

    public void setCardHeight(int cardHeight) {
        this.cardHeight = cardHeight;
    }

    public int getPlayerPlayedCardX() {
        return playerPlayedCardX;
    }

    public void setPlayerPlayedCardX(int playerPlayedCardX) {
        this.playerPlayedCardX = playerPlayedCardX;
    }

    public int getPlayerPlayedCardY() {
        return playerPlayedCardY;
    }

    public void setPlayerPlayedCardY(int playerPlayedCardY) {
        this.playerPlayedCardY = playerPlayedCardY;
    }

    public int getPlayerHeroPowerX() {
        return playerHeroPowerX;
    }

    public void setPlayerHeroPowerX(int playerHeroPowerX) {
        this.playerHeroPowerX = playerHeroPowerX;
    }

    public int getPlayerHeroPowerY() {
        return playerHeroPowerY;
    }

    public void setPlayerHeroPowerY(int playerHeroPowerY) {
        this.playerHeroPowerY = playerHeroPowerY;
    }

    public int getOpponentHeroPowerY() {
        return opponentHeroPowerY;
    }

    public void setOpponentHeroPowerY(int opponentHeroPowerY) {
        this.opponentHeroPowerY = opponentHeroPowerY;
    }

    public int getHeroPoerWidth() {
        return heroPoerWidth;
    }

    public void setHeroPoerWidth(int heroPoerWidth) {
        this.heroPoerWidth = heroPoerWidth;
    }

    public int getHeroPowerHeight() {
        return heroPowerHeight;
    }

    public void setHeroPowerHeight(int heroPowerHeight) {
        this.heroPowerHeight = heroPowerHeight;
    }

    public int getMouseX() {
        return mouseX;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public int getDeckX() {
        return deckX;
    }

    public void setDeckX(int deckX) {
        this.deckX = deckX;
    }

    public int getDeckY() {
        return deckY;
    }

    public void setDeckY(int deckY) {
        this.deckY = deckY;
    }

    public int getMiddleX() {
        return middleX;
    }

    public void setMiddleX(int middleX) {
        this.middleX = middleX;
    }

    public int getMiddleY() {
        return middleY;
    }

    public void setMiddleY(int middleY) {
        this.middleY = middleY;
    }

    public int getOpponentPlayedCardY() {
        return opponentPlayedCardY;
    }

    public void setOpponentPlayedCardY(int opponentPlayedCardY) {
        this.opponentPlayedCardY = opponentPlayedCardY;
    }

    public int getAiDeckX() {
        return AiDeckX;
    }

    public void setAiDeckX(int aiDeckX) {
        AiDeckX = aiDeckX;
    }

    public int getAiDeckY() {
        return AiDeckY;
    }

    public void setAiDeckY(int aiDeckY) {
        AiDeckY = aiDeckY;
    }
}
