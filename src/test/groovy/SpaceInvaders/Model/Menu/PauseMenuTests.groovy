package SpaceInvaders.Model.Menu

import spock.lang.Specification

class PauseMenuTests extends Specification{
    def "Get Option PauseMenu"(){
        given:
        def m = new PauseMenu()
        expect:
        expected == m.getOption(i)
        where:
        i | expected
        0 | "Continue"
        1 | "Instructions"
        2 | "Restart"
        3 | "Exit"
    }

    def "isSelectedContinue is True"(){
        given:
        def menu = Spy(PauseMenu)
        when:
        menu.isSelected(0) >> true
        then:
        menu.isSelectedContinue() == true

    }

    def "isSelectedContinue is false"(){
        given:
        def menu = Spy(PauseMenu)
        when:
        menu.isSelected(0) >> false
        then:
        menu.isSelectedContinue() == false

    }

    def "isSelectedInstructions is true"(){
        given:
        def menu = Spy(PauseMenu)
        when:
        menu.isSelected(1) >> true
        then:
        menu.isSelectedInstructions() == true

    }

    def "isSelectedInstructions is false"(){
        given:
        def menu = Spy(PauseMenu)
        when:
        menu.isSelected(1) >> false
        then:
        menu.isSelectedInstructions() == false

    }

    def "isSelectedRestart is true"(){
        given:
        def menu = Spy(PauseMenu)
        when:
        menu.isSelected(2) >> true
        then:
        menu.isSelectedRestart() == true
    }

    def "isSelectedRestart is false"(){
        given:
        def menu = Spy(PauseMenu)
        when:
        menu.isSelected(2) >> false
        then:
        menu.isSelectedRestart() == false
    }

    def "isSelectedExit is true"(){
        given:
        def menu = Spy(PauseMenu)
        when:
        menu.isSelected(3) >> true
        then:
        menu.isSelectedExit() == true
    }

    def "isSelectedExit is false"(){
        given:
        def menu = Spy(PauseMenu)
        when:
        menu.isSelected(3) >> false
        then:
        menu.isSelectedExit() == false
    }
}

