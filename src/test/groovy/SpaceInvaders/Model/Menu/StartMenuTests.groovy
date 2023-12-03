package SpaceInvaders.Model.Menu

import spock.lang.Specification

class StartMenuTests extends Specification{
    def "Get Option StartMenu"(){
        given:
        def m = new StartMenu()
        expect:
        expected == m.getOption(i)
        where:
        i | expected
        0 | "Play"
        1 | "Leaderboard"
        2 | "Instructions"
        3 | "Exit"
    }

    def "isSelectedPlay is True"(){
        given:
            def menu = Spy(StartMenu)
        when:
            menu.isSelected(0) >> true
        then:
            menu.isSelectedPlay() == true

    }

    def "isSelectedPlay is false"(){
        given:
        def menu = Spy(StartMenu)
        when:
            menu.isSelected(0) >> false
        then:
            menu.isSelectedPlay() == false

    }

    def "isSelectedLeaderboard is true"(){
        given:
            def menu = Spy(StartMenu)
        when:
            menu.isSelected(1) >> true
        then:
            menu.isSelectedLeaderboard() == true

    }

    def "isSelectedLeaderboard is false"(){
        given:
            def menu = Spy(StartMenu)
        when:
            menu.isSelected(1) >> false
        then:
            menu.isSelectedLeaderboard() == false

    }

    def "isSelectedInstructions is true"(){
        given:
            def menu = Spy(StartMenu)
        when:
            menu.isSelected(2) >> true
        then:
            menu.isSelectedInstructions() == true
    }

    def "isSelectedInstructions is false"(){
        given:
            def menu = Spy(StartMenu)
        when:
            menu.isSelected(2) >> false
        then:
            menu.isSelectedInstructions() == false
    }

    def "isSelectedExit is true"(){
        given:
        def menu = Spy(StartMenu)
        when:
        menu.isSelected(3) >> true
        then:
        menu.isSelectedExit() == true
    }

    def "isSelectedExit is false"(){
        given:
            def menu = Spy(StartMenu)
        when:
            menu.isSelected(3) >> false
        then:
            menu.isSelectedExit() == false
    }


}

