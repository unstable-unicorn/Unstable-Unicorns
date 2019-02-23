package com.unstable.unicorn.card;

public class UnicornCard extends Card {
    private boolean isBasic;
    private boolean isMagical;
    private boolean isBaby;

    public boolean isBaby() {
        return isBaby;
    }

    public void setBaby(boolean baby) {
        isBaby = baby;
    }

    public boolean isMagical() {
        return isMagical;
    }

    public void setMagical(boolean magical) {
        isMagical = magical;
    }

    public boolean isBasic() {
        return isBasic;
    }

    public void setBasic(boolean basic) {
        isBasic = basic;
    }
}
