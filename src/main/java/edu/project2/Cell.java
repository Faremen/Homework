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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cell cell = (Cell) o;

        if (isRightWall != cell.isRightWall) {
            return false;
        }
        return isBottomWall == cell.isBottomWall;
    }

    @Override
    public int hashCode() {
        int result = (isRightWall ? 1 : 0);
        result = 31 * result + (isBottomWall ? 1 : 0);
        return result;
    }
}
