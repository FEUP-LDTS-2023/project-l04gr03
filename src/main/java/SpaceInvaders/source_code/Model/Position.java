package SpaceInvaders.source_code.Model;

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
        if(this.getClass() != o.getClass() || this == null){
            return false;
        }
        return this.x == ((Position) o).x && this.y == ((Position) o).y;
    }
}
