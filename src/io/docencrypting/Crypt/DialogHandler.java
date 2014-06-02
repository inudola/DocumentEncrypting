package io.docencrypting.Crypt;

/**
 * Dialog handler
 */
public abstract class DialogHandler {

    public enum Type {
        NOT_ANSWER,
        YES,
        NO
    }

    private Type needChange = Type.NOT_ANSWER;

    public Type getNeedChange() {
        return needChange;
    }

    public void setNeedChange(Type needChange) {
        this.needChange = needChange;
    }

    public boolean isNeedChange() {
        return needChange.equals(Type.YES);
    }

    public abstract boolean show(String message, String title);

}
