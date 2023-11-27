package SpaceInvaders.Model.Game;

import SpaceInvaders.Model.Position;

public abstract class Element {

    private Position position;

    public Element(Position position){
        this.position = position;
    }

    public Position getPosition(){
        return position;
    }

    public void setPosition(Position position){
        this.position = position;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(this.getClass() != o.getClass() || this == null){
            return false;
        }
        return this.getPosition() == ((Element) o).getPosition();
    }
}
