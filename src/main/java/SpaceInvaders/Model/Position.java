package SpaceInvaders.Model;

public class Position {
    private int x;

    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x) {this.x = x;}

    public void setY(int y) {this.y = y;}

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Position)){
            return false;
        }
        return this.x == ((Position) o).x && this.y == ((Position) o).y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }
}
