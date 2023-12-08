package SpaceInvaders.Controller.Sound

import SpaceInvaders.Model.Sound.Sound_Options
import spock.lang.Specification

class SoundManagerTests extends Specification {

    def "should play and then stop sound"() {
        given:
            def soundManager = SoundManager.getInstance()

        when: "First play the sound"
            soundManager.playSound(Sound_Options.LASER)

        then:
            soundManager.isSoundPlaying(Sound_Options.LASER)

        when: "Then stop the sound"
            soundManager.stopSound(Sound_Options.LASER)

        then:
            !soundManager.isSoundPlaying(Sound_Options.LASER)
    }

    def "Should Play the sound"(){
        given:
            def soundManager = SoundManager.getInstance()
            soundManager.playSound(Sound_Options.MUSIC)
            soundManager.playSound(Sound_Options.ALIEN_SHIP_HIGH)
            soundManager.playSound(Sound_Options.ALIEN_SHIP_LOW)
            soundManager.playSound(Sound_Options.DESTRUCTION)
            soundManager.playSound(Sound_Options.COLLECTABLE)
            soundManager.playSound(Sound_Options.LASER)
            soundManager.playSound(Sound_Options.MENU_SWITCH)

        expect:
            soundManager.isSoundPlaying(Sound_Options.MUSIC)
            soundManager.isSoundPlaying(Sound_Options.LASER)
            soundManager.isSoundPlaying(Sound_Options.MENU_SWITCH)
            soundManager.isSoundPlaying(Sound_Options.ALIEN_SHIP_LOW)
            soundManager.isSoundPlaying(Sound_Options.ALIEN_SHIP_HIGH)
            soundManager.isSoundPlaying(Sound_Options.COLLECTABLE)
            soundManager.isSoundPlaying(Sound_Options.DESTRUCTION)
    }

    def "should play and then stop all sounds"() {
        given:
            def soundManager = SoundManager.getInstance()
            soundManager.playSound(Sound_Options.MUSIC)
            soundManager.playSound(Sound_Options.ALIEN_SHIP_HIGH)
            soundManager.playSound(Sound_Options.ALIEN_SHIP_LOW)
            soundManager.playSound(Sound_Options.DESTRUCTION)
            soundManager.playSound(Sound_Options.COLLECTABLE)
            soundManager.playSound(Sound_Options.LASER)
            soundManager.playSound(Sound_Options.MENU_SWITCH)

        when:
            soundManager.stopAllSounds()

        then:
            !soundManager.isSoundPlaying(Sound_Options.MUSIC)
            !soundManager.isSoundPlaying(Sound_Options.LASER)
            !soundManager.isSoundPlaying(Sound_Options.MENU_SWITCH)
            !soundManager.isSoundPlaying(Sound_Options.ALIEN_SHIP_LOW)
            !soundManager.isSoundPlaying(Sound_Options.ALIEN_SHIP_HIGH)
            !soundManager.isSoundPlaying(Sound_Options.COLLECTABLE)
            !soundManager.isSoundPlaying(Sound_Options.DESTRUCTION)
    }

    def "should resume playing"() {
        given:
            def soundManager = SoundManager.getInstance()
            soundManager.playSound(Sound_Options.MUSIC)
            soundManager.playSound(Sound_Options.ALIEN_SHIP_HIGH)
            soundManager.playSound(Sound_Options.ALIEN_SHIP_LOW)

        when: "Starts playing"
            soundManager.stopAllSounds()
            soundManager.resumePlaying()
        then:
            soundManager.isSoundPlaying(Sound_Options.MUSIC)
            soundManager.isSoundPlaying(Sound_Options.ALIEN_SHIP_LOW)
            soundManager.isSoundPlaying(Sound_Options.ALIEN_SHIP_HIGH)
    }


    def "Should play and stop sound  (individually and continuous sounds)"(){
        given:
            def soundManager = SoundManager.getInstance()
            soundManager.playSound(Sound_Options.MUSIC)
            soundManager.playSound(Sound_Options.ALIEN_SHIP_HIGH)
            soundManager.playSound(Sound_Options.ALIEN_SHIP_LOW)
            soundManager.playSound(Sound_Options.DESTRUCTION)
            soundManager.playSound(Sound_Options.COLLECTABLE)
            soundManager.playSound(Sound_Options.LASER)
            soundManager.playSound(Sound_Options.MENU_SWITCH)

        when:
            soundManager.stopSound(Sound_Options.MUSIC)
            soundManager.stopSound(Sound_Options.ALIEN_SHIP_HIGH)
            soundManager.stopSound(Sound_Options.ALIEN_SHIP_LOW)
            soundManager.stopSound(Sound_Options.DESTRUCTION)
            soundManager.stopSound(Sound_Options.COLLECTABLE)
            soundManager.stopSound(Sound_Options.LASER)
            soundManager.stopSound(Sound_Options.MENU_SWITCH)

        then:
            !soundManager.isSoundPlaying(Sound_Options.LASER)
            !soundManager.isSoundPlaying(Sound_Options.COLLECTABLE)
            !soundManager.isSoundPlaying(Sound_Options.MUSIC)
            !soundManager.isSoundPlaying(Sound_Options.MENU_SWITCH)
            !soundManager.isSoundPlaying(Sound_Options.ALIEN_SHIP_LOW)
            !soundManager.isSoundPlaying(Sound_Options.ALIEN_SHIP_HIGH)
            !soundManager.isSoundPlaying(Sound_Options.DESTRUCTION)
    }
    


}


