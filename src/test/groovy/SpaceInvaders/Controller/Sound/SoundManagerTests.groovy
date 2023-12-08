package SpaceInvaders.Controller.Sound

import SpaceInvaders.Model.Sound.Sound_Options
import spock.lang.Specification

class SoundManagerTests extends Specification {

    def "should play and then stop sound"() {
        given:
        def soundManager = SoundManager.getInstance()

        when:
        soundManager.playSound(Sound_Options.LASER)

        then:
        soundManager.isSoundPlaying(Sound_Options.LASER)

        when:
        soundManager.stopSound(Sound_Options.LASER)

        then:
        !soundManager.isSoundPlaying(Sound_Options.LASER)
    }

    def "should play and then stop all sounds"() {
        given:
        def soundManager = SoundManager.getInstance()

        when:
        soundManager.playSound(Sound_Options.MUSIC)
        soundManager.playSound(Sound_Options.LASER)
        soundManager.playSound(Sound_Options.MENU_SWITCH)

        then:
        soundManager.isSoundPlaying(Sound_Options.MUSIC)
        soundManager.isSoundPlaying(Sound_Options.LASER)
        soundManager.isSoundPlaying(Sound_Options.MENU_SWITCH)

        when:
        soundManager.stopAllSounds()

        then:
        !soundManager.isSoundPlaying(Sound_Options.MUSIC)
        !soundManager.isSoundPlaying(Sound_Options.LASER)
        !soundManager.isSoundPlaying(Sound_Options.MENU_SWITCH)
    }

    def "should resume playing after the stop"() {
        given:
        def soundManager = SoundManager.getInstance()

        when:
        soundManager.playSound(Sound_Options.MUSIC)
        soundManager.playSound(Sound_Options.LASER)

        then:
        soundManager.stopAllSounds()
        !soundManager.isSoundPlaying(Sound_Options.MUSIC)
        !soundManager.isSoundPlaying(Sound_Options.LASER)

        when:
        soundManager.resumePlaying()

        then:
        soundManager.isSoundPlaying(Sound_Options.MUSIC)
        soundManager.isSoundPlaying(Sound_Options.LASER)
    }

    def "should play and then stop collectable sound continuously"() {
        given:
        def soundManager = SoundManager.getInstance()

        when:
        soundManager.playSound(Sound_Options.COLLECTABLE)

        then:
        soundManager.isSoundPlaying(Sound_Options.COLLECTABLE)

        when:
        soundManager.stopSound(Sound_Options.COLLECTABLE)

        then:
        !soundManager.isSoundPlaying(Sound_Options.COLLECTABLE)
    }

    def "should check if the background music is playing continuously"() {
        given:
        def soundManager = SoundManager.getInstance()

        when:
        soundManager.playSound(Sound_Options.MUSIC)

        then:
        soundManager.isSoundPlaying(Sound_Options.MUSIC)

        when:
        soundManager.stopSound(Sound_Options.MUSIC)

        then:
        !soundManager.isSoundPlaying(Sound_Options.MUSIC)
    }

    def "should play and then stop alien ship sounds continuously"() {
        given:
        def soundManager = SoundManager.getInstance()

        when:
        soundManager.playSound(Sound_Options.ALIEN_SHIP_LOW)

        then:
        soundManager.isSoundPlaying(Sound_Options.ALIEN_SHIP_LOW)

        when:
        soundManager.stopSound(Sound_Options.ALIEN_SHIP_LOW)

        then:
        !soundManager.isSoundPlaying(Sound_Options.ALIEN_SHIP_LOW)

        when:
        soundManager.playSound(Sound_Options.ALIEN_SHIP_HIGH)

        then:
        soundManager.isSoundPlaying(Sound_Options.ALIEN_SHIP_HIGH)

        when:
        soundManager.stopSound(Sound_Options.ALIEN_SHIP_HIGH)

        then:
        !soundManager.isSoundPlaying(Sound_Options.ALIEN_SHIP_HIGH)
    }
}


