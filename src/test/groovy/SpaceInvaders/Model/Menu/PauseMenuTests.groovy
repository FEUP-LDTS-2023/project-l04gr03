package SpaceInvaders.Model.Menu

import spock.lang.Specification

class PauseMenuTests extends Specification{
    def "Get Option PauseMenu"(){
        given:
            def menu = new PauseMenu()

        expect:
            expected == menu.getOption(i)

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
            menu.isSelectedContinue()

    }

    def "isSelectedContinue is false"(){
        given:
            def menu = Spy(PauseMenu)

        when:
            menu.isSelected(0) >> false

        then:
            !menu.isSelectedContinue()

    }

    def "isSelectedInstructions is true"(){
        given:
            def menu = Spy(PauseMenu)

        when:
            menu.isSelected(1) >> true

        then:
            menu.isSelectedInstructions()

    }

    def "isSelectedInstructions is false"(){
        given:
            def menu = Spy(PauseMenu)

        when:
            menu.isSelected(1) >> false

        then:
            !menu.isSelectedInstructions()

    }

    def "isSelectedRestart is true"(){
        given:
            def menu = Spy(PauseMenu)

        when:
            menu.isSelected(2) >> true

        then:
            menu.isSelectedRestart()
    }

    def "isSelectedRestart is false"(){
        given:
            def menu = Spy(PauseMenu)

        when:
            menu.isSelected(2) >> false

        then:
            !menu.isSelectedRestart()
    }

    def "isSelectedExit is true"(){
        given:
            def menu = Spy(PauseMenu)

        when:
            menu.isSelected(3) >> true

        then:
            menu.isSelectedExit()
    }

    def "isSelectedExit is false"(){
        given:
            def menu = Spy(PauseMenu)

        when:
            menu.isSelected(3) >> false

        then:
            !menu.isSelectedExit()
    }
}

