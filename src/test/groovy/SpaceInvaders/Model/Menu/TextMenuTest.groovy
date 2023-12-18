package SpaceInvaders.Model.Menu

import spock.lang.Specification

class TextMenuTest extends Specification{

    def "Get Text" (){
        given:
            def menu = new Instructions()

        expect:
            menu.getText() != null
    }



}