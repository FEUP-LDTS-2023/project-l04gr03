package SpaceInvaders.source_code.Model.Game;

import SpaceInvaders.source_code.Model.Position;

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
}
