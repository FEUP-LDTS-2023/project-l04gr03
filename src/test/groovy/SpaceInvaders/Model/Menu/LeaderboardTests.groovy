package SpaceInvaders.Model.Menu

import spock.lang.Specification

class LeaderboardTests extends Specification{
    def "Sort by Score"() {
        given:
        def menu = new Leaderboard()
        menu.text = Arrays.asList("João 123", "Maria 234", "Lucas 010")
        when:
        menu.sortByScore()
        then:
        menu.text == Arrays.asList("Maria 234", "João 123", "Lucas 010")
    }
}

