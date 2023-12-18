package SpaceInvaders.Controller.Sound

import SpaceInvaders.Model.Sound.Sound
import SpaceInvaders.Model.Sound.Sound_Options
import spock.lang.Specification

class SoundManagerTests extends Specification {

    SoundManager soundManager
    SoundManager soundManagerSpy
    def laserSound = Mock(Sound)
    def dyingSound = Mock(Sound)
    def switchOption = Mock(Sound)
    def backgroundMusic = Mock(Sound)
    def collectableSound = Mock(Sound)
    def alienShipLowPitch = Mock(Sound)
    def alienShipHighPitch = Mock(Sound)

    def setup(){
        soundManager = SoundManager.getInstance()
        soundManager.setLaser(laserSound)
        soundManager.setDyingSound(dyingSound)
        soundManager.setSwitchOption(switchOption)
        soundManager.setBackgroundMusic(backgroundMusic)
        soundManager.setCollectableSound(collectableSound)
        soundManager.setAlienShipLowPitch(alienShipLowPitch)
        soundManager.setAlienShipHighPitch(alienShipHighPitch)
        soundManagerSpy = Spy(soundManager)
    }


    def "Should Play the sound Music"(){
        when:
            soundManagerSpy.playSound(Sound_Options.MUSIC)

        then:
            1 * backgroundMusic.playContinuously()
    }

    def "Should Play Alien ship high"(){
        when:
            soundManagerSpy.playSound(Sound_Options.ALIEN_SHIP_HIGH)

        then:
            1 * alienShipHighPitch.playContinuously()
    }

    def "Should Play Alien ship low"(){
        when:
            soundManagerSpy.playSound(Sound_Options.ALIEN_SHIP_LOW)

        then:
            1 * alienShipLowPitch.playContinuously()
    }

    def "Should Play dying sound"(){
        when:
            soundManagerSpy.playSound(Sound_Options.DESTRUCTION)

        then:
            1 * dyingSound.play()
    }

    def"Should Play collectable sound"(){
        when:
            soundManagerSpy.playSound(Sound_Options.COLLECTABLE)

        then:
            1 * collectableSound.play()
    }

    def "Should Play laser sound"(){
        when:
            soundManagerSpy.playSound(Sound_Options.LASER)

        then:
            1 * laserSound.play()
    }

    def "Should Play menu switch"(){
        when:
            soundManagerSpy.playSound(Sound_Options.MENU_SWITCH)

        then:
            1 * switchOption.play()
    }


    def "should play and then stop all sounds"() {
        when:
            soundManagerSpy.stopAllSounds()

        then:
            1 * laserSound.stop()
            1 * dyingSound.stop()
            1 * switchOption.stop()
            1 * backgroundMusic.stop()
            1 * collectableSound.stop()
            1 * alienShipLowPitch.stop()
            1 * alienShipHighPitch.stop()
    }

    def "should resume playing Music"() {

        when: "Starts playing"
            soundManagerSpy.resumePlayingMusic()

        then:
            1 * backgroundMusic.resumePlaying()
    }

    def "should resume playing AlieShip sound"(){

        when: "Starts playing"
            soundManagerSpy.resumePlayingAlienShipSound()

        then:
            1 * alienShipHighPitch.resumePlaying()
            1 * alienShipLowPitch.resumePlaying()
    }


    def "Should stop music"(){
        when:
            soundManagerSpy.stopSound(Sound_Options.MUSIC)

        then:
            1 * backgroundMusic.stop()
    }

    def "Should stop alien ship high"(){
        when:
            soundManagerSpy.stopSound(Sound_Options.ALIEN_SHIP_HIGH)

        then:
            1 * alienShipHighPitch.stop()
    }

    def "Should stop alien ship low"(){
        when:
            soundManagerSpy.stopSound(Sound_Options.ALIEN_SHIP_LOW)

        then:
            1 * alienShipLowPitch.stop()
    }

    def "Should stop dying sound"(){
        when:
            soundManagerSpy.stopSound(Sound_Options.DESTRUCTION)

        then:
            1 * dyingSound.stop()
    }

    def "Should stop collectable sound"(){
        when:
            soundManagerSpy.stopSound(Sound_Options.COLLECTABLE)

        then:
            1 * collectableSound.stop()
    }

    def "Should stop menu switch"(){
        when:
            soundManagerSpy.stopSound(Sound_Options.MENU_SWITCH)

        then:
            1 * switchOption.stop()
    }

    def "Should stop laser"(){
        when:
            soundManagerSpy.stopSound(Sound_Options.LASER)

        then:
            1 * laserSound.stop()
    }
    


}


