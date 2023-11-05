package edu.project2;

public class Cell {

    private boolean isRightWall;
    private boolean isBottomWall;

    public Cell(boolean isRightWall, boolean isBottomWall) {
        this.isRightWall = isRightWall;
        this.isBottomWall = isBottomWall;
    }

    public boolean isRightWall() {
        return isRightWall;
    }

    public void setRightWall(boolean rightWall) {
        isRightWall = rightWall;
    }

    public boolean isBottomWall() {
        return isBottomWall;
    }

    public void setBottomWall(boolean bottomWall) {
        isBottomWall = bottomWall;
    }
}
