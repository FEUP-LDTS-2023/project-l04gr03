package SpaceInvaders.Model.Menu

import spock.lang.Specification

class GameOverMenuTests extends Specification{

    def "Add char username GameOver"(){
        given:
        def m = new GameOverMenu(0)
        Character c = 'A'
        when:
        m.addLetter(c)
        then:
        m.username == "A"

        when: 'username length == 6'
        Character c1 = 'a', c2 = 'b', c3 = 'c', c4 = 'd', c5 = 'e', c6 = 'l'
        m.addLetter(c1);
        m.addLetter(c2)
        m.addLetter(c3)
        m.addLetter(c4)
        m.addLetter(c5)
        m.addLetter(c6)
        then:
        m.username.length() == 6
        m.username == "Aabcde"
    }

    def "Add char username GameOver when username isn´t empty"(){
        given:
        def m = new GameOverMenu(0)
        Character c = 'C'
        StringBuilder s = new StringBuilder()
        s.append("AB")
        m.setUsername(s)
        when:
        m.addLetter(c)
        then:
        m.username == "ABC"
    }

    def "Remove char username GameOver"(){
        given:
        def m = new GameOverMenu(0)
        StringBuilder s = new StringBuilder()
        s.append("ABC")
        m.setUsername(s)
        when:
        m.removeLetter()
        then:
        m.username == "AB"
    }

    def "remove Char username GameOver when username is empty" (){
        given:
        def m = new GameOverMenu(0)
        when:
        m.removeLetter()
        then:
        m.username == ""
    }

    def "isSelectedRestart is True"(){
        given:
        def menu = new GameOverMenu(0)
        expect:
            menu.isSelectedRestart() == true

    }

    def "isSelectedRestart is false"(){
        given:
            def menu = new GameOverMenu(0)
        when:
            menu.nextOption()
        then:
            menu.isSelectedRestart() == false

    }

    def "isSelectedLeaderboard is true"(){
        given:
            def menu = new GameOverMenu(0)
        when:
            menu.nextOption()
        then:
            menu.isSelectedLeaderboard() == true

    }

    def "isSelectedLeaderboard is false"(){
        given:
            def menu = new GameOverMenu(0)
        when:
            menu.nextOption()
            menu.nextOption()
        then:
            menu.isSelectedLeaderboard() == false

    }

    def "isSelectedExit is true"(){
        given:
            def menu = new GameOverMenu(0)
        when:
            menu.nextOption()
            menu.nextOption()
        then:
            menu.isSelectedExit() == true
    }

    def "isSelectedExit is false"(){
        given:
            def menu = new GameOverMenu(0)
        expect:
            menu.isSelectedExit() == false
    }

    def"Get Score"() {
        given:
            def menu = new GameOverMenu(10)
        expect:
            menu.getScore() == 10
    }


}

