package SpaceInvaders.Model.Menu

import spock.lang.Specification

class LeaderboardTests extends Specification{
    Leaderboard menu

    def setup(){
        menu = new Leaderboard()
    }

    def "Sort by Score"() {
        given:
            menu.text = Arrays.asList("João 123", "Maria 234", "Lucas 010")

        when:
            menu.sortByScore()

        then:
            menu.text == Arrays.asList("Maria 234", "João 123", "Lucas 010")
    }

    def "Leaderboard Constructor"(){
        given:
            def text = menu.getText()
            def s1 = text.get(0)
            def s2 = text.get(1)
            String[] splitS1 = s1.split(" ", -1)
            String[] splitS2 = s2.split(" ", -1)

        expect:
            Integer.parseInt(splitS2[1]) < Integer.parseInt(splitS1[1]);
    }
}

