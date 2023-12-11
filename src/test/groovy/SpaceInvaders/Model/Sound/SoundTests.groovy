package SpaceInvaders.Model.Sound

import spock.lang.Specification

import javax.sound.sampled.Clip

class SoundTests extends Specification{

    def clip = Mock(Clip)

    def "Play"(){
        given:
            def sound = new Sound("src/main/resources/sounds/shoot.wav")
        when:
            sound.setSound(clip)
            sound.play()
        then:
            1 * clip.setFramePosition(0)
            1 * clip.start()
    }

    def "Play and is already playing"(){
        given:
            def sound = new Sound("src/main/resources/sounds/shoot.wav")
        when:
            sound.setSound(clip)
            clip.isRunning() >> true
            sound.play()
        then:
            1 * clip.setFramePosition(0)
            1 * clip.start()
            1 * clip.stop()
    }

    def "Play Continuously"(){
        given:
            def sound = new Sound("src/main/resources/sounds/shoot.wav")
        when:
            sound.setSound(clip)
            sound.playContinuously()
        then:
            1 * clip.loop(Clip.LOOP_CONTINUOUSLY)
            1 * clip.setFramePosition(0)
            1 * clip.start()
    }

    def "Stop" (){
        given:
            def sound = new Sound("src/main/resources/sounds/shoot.wav")
        when:
            sound.setSound(clip)
            sound.stop()
        then:
            1 * clip.stop()
    }

    def "isPlaying true"(){
        given:
            def sound = new Sound("src/main/resources/sounds/shoot.wav")
            clip.isRunning() >> true
        when:
            sound.setSound(clip)
        then:
            sound.isPlaying()
    }

    def "isPlaying false"(){
        given:
            def sound = new Sound("src/main/resources/sounds/shoot.wav")
            clip.isRunning() >> false
        when:
            sound.setSound(clip)
        then:
            !sound.isPlaying()
    }

    def "resumePlaying"(){
        given:
            def sound = new Sound("src/main/resources/sounds/shoot.wav")
        when:
            sound.setSound(clip)
            sound.resumePlaying()
        then:
            1 * clip.start()
            1 * clip.loop(_)
    }

    def "getSound"(){
        given:
            def sound = new Sound("src/main/resources/sounds/shoot.wav")
        when:
            sound.setSound(clip)
        then:
            sound.getSound() == clip
    }

    def "SetSound"(){
        given:
            def sound = new Sound("src/main/resources/sounds/shoot.wav")
        when:
            sound.setSound(clip)
        then:
            sound.getSound() == clip
    }

    def "Sound not valid"(){
        when:
            def sound = new Sound("src/main/resources/text/Leaderboard.txt")
        then:
            thrown RuntimeException
    }

}

