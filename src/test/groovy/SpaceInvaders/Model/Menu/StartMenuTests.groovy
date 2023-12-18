package SpaceInvaders.Model.Menu

import spock.lang.Specification

class StartMenuTests extends Specification{
    def "Get Option StartMenu"(){
        given:
            def menu = new StartMenu()
        expect:
            expected == menu.getOption(i)

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
            menu.isSelectedPlay()

    }

    def "isSelectedPlay is false"(){
        given:
        def menu = Spy(StartMenu)

        when:
            menu.isSelected(0) >> false

        then:
            !menu.isSelectedPlay()

    }

    def "isSelectedLeaderboard is true"(){
        given:
            def menu = Spy(StartMenu)

        when:
            menu.isSelected(1) >> true

        then:
            menu.isSelectedLeaderboard()

    }

    def "isSelectedLeaderboard is false"(){
        given:
            def menu = Spy(StartMenu)

        when:
            menu.isSelected(1) >> false

        then:
            !menu.isSelectedLeaderboard()

    }

    def "isSelectedInstructions is true"(){
        given:
            def menu = Spy(StartMenu)

        when:
            menu.isSelected(2) >> true

        then:
            menu.isSelectedInstructions()
    }

    def "isSelectedInstructions is false"(){
        given:
            def menu = Spy(StartMenu)

        when:
            menu.isSelected(2) >> false

        then:
            !menu.isSelectedInstructions()
    }

    def "isSelectedExit is true"(){
        given:
            def menu = Spy(StartMenu)

        when:
            menu.isSelected(3) >> true

        then:
            menu.isSelectedExit()
    }

    def "isSelectedExit is false"(){
        given:
            def menu = Spy(StartMenu)

        when:
            menu.isSelected(3) >> false

        then:
            !menu.isSelectedExit()
    }


}

