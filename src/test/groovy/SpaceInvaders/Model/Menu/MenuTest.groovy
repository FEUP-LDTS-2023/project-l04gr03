package SpaceInvaders.Model.Menu


import spock.lang.Specification


class MenuTest extends Specification {

    def "Next Option"(){
        given:
            def menu = new StartMenu()

        when:
            menu.nextOption()

        then:
            menu.isSelected(1)
    }

    def "Previous Option selected < 0"(){
        given:
            def menu = new StartMenu()

        when:
            menu.previousOption()

        then:
            menu.isSelected(3)

    }

    def "Next Option selected >= options.size()"(){
        given:
            def menu = new StartMenu()
            menu.selected = 3

        when:
            menu.nextOption()

        then:
            menu.isSelected(0)

    }

    def "Previous Option" (){
        given:
            def menu = new StartMenu()
            menu.selected = 1

        when:
            menu.previousOption()

        then:
            menu.isSelected(0)
    }

    def "Number of options"(){
        given:
            def menu = new PauseMenu()

        when:
            int i = menu.getNumberOptions()

        then:
            i == 4
    }


}

