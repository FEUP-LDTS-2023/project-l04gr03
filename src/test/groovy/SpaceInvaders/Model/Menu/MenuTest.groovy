package SpaceInvaders.Model.Menu


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

    def "Number of options"(){
        given:
            def m = new PauseMenu()
        when:
            int i = m.getNumberOptions()
        then:
            i == 4
    }


}

