package SpaceInvaders.source_code.Model.Menu


import spock.lang.Specification


class MenuTest extends Specification {

    def "Next Option"(){
        given:
            def m = new StartMenu()
        when:
            m.nextOption()
        then:
            m.isSelected(1)
    }

    def "Previous Option selected < 0"(){
        given:
            def m = new StartMenu()
        when:
            m.previousOption()
        then:
            m.isSelected(3)

    }

    def "Next Option selected >= options.size()"(){
        given:
            def m = new StartMenu()
            m.selected = 3
        when:
            m.nextOption()
        then:
            m.isSelected(0)

    }

    def "Previous Option" (){
        given:
            def m = new StartMenu()
            m.selected = 1
        when:
            m.previousOption()
        then:
            m.isSelected(0)
    }

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

    def "Number of options"(){
        given:
            def m = new PauseMenu()
        when:
            int i = m.getNumberOptions()
        then:
            i == 4
    }

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

}

