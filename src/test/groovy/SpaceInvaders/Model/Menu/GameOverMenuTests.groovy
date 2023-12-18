package SpaceInvaders.Model.Menu

import spock.lang.Specification

class GameOverMenuTests extends Specification{

    def "Add char username GameOver"(){
        given:
            def menu = new GameOverMenu(0)
            Character c = 'A'

        when:
            menu.addLetter(c)

        then:
            menu.username == "A"

        when: 'username length == 6'
            Character c1 = 'a', c2 = 'b', c3 = 'c', c4 = 'd', c5 = 'e', c6 = 'l'
            menu.addLetter(c1);
            menu.addLetter(c2)
            menu.addLetter(c3)
            menu.addLetter(c4)
            menu.addLetter(c5)
            menu.addLetter(c6)

        then:
            menu.username.length() == 6
            menu.username == "Aabcde"
    }

    def "Add char username GameOver when username isn´t empty"(){
        given:
            def menu = new GameOverMenu(0)
            Character c = 'C'
            def stringBuilder = new StringBuilder()
            stringBuilder.append("AB")
            menu.setUsername(stringBuilder)

        when:
            menu.addLetter(c)

        then:
            menu.username == "ABC"
    }

    def "Remove char username GameOver"(){
        given:
            def menu = new GameOverMenu(0)
            def stringBuilder = new StringBuilder()
            stringBuilder.append("ABC")
            menu.setUsername(stringBuilder)

        when:
            menu.removeLetter()

        then:
            menu.username == "AB"
    }

    def "remove Char username GameOver when username is empty" (){
        given:
            def menu = new GameOverMenu(0)

        when:
            menu.removeLetter()

        then:
            menu.username == ""
    }

    def "isSelectedRestart is True"(){
        given:
            def menu = new GameOverMenu(0)

        expect:
            menu.isSelectedRestart()

    }

    def "isSelectedRestart is false"(){
        given:
            def menu = new GameOverMenu(0)

        when:
            menu.nextOption()

        then:
            !menu.isSelectedRestart()

    }

    def "isSelectedLeaderboard is true"(){
        given:
            def menu = new GameOverMenu(0)

        when:
            menu.nextOption()

        then:
            menu.isSelectedLeaderboard()

    }

    def "isSelectedLeaderboard is false"(){
        given:
            def menu = new GameOverMenu(0)

        when:
            menu.nextOption()
            menu.nextOption()

        then:
            !menu.isSelectedLeaderboard()

    }

    def "isSelectedExit is true"(){
        given:
            def menu = new GameOverMenu(0)

        when:
            menu.nextOption()
            menu.nextOption()

        then:
            menu.isSelectedExit()
    }

    def "isSelectedExit is false"(){
        given:
            def menu = new GameOverMenu(0)

        expect:
            !menu.isSelectedExit()
    }

    def"Get Score"() {
        given:
            def menu = new GameOverMenu(10)

        expect:
            menu.getScore() == 10
    }


}

