package SpaceInvaders.Model.Game.Collectables


import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.AlienMode
import SpaceInvaders.Model.Game.RegularGameElements.AttackingElement
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Game.RegularGameElements.ShipMode
import SpaceInvaders.Model.Position
import spock.lang.Specification

class TestCollectables extends Specification {

    def position = Mock(Position)

    def "HealthCollectable"(){
        given:
            def ship = Mock(Ship)
            def healthCollectable = new HealthCollectable(position,ship)
            def spyHealthCollectable = Spy(healthCollectable)

        when:
            spyHealthCollectable.execute()

        then:
            1 * spyHealthCollectable.getAttackingElement().restoreHealth()
    }

    def "DamageCollectable"(){
        given:
            def ship = Mock(Ship)

            int multiplier = 2
            def damageCollectable1 = new DamageCollectable(position,ship,multiplier)
            def spyDamageCollectable1 = Spy(damageCollectable1)

            multiplier = 3
            def damageCollectable2 = new DamageCollectable(position,ship,multiplier)
            def spyDamageCollectable2 = Spy(damageCollectable2)

            multiplier = 4
            def damageCollectable3 = new DamageCollectable(position,ship,multiplier)
            def spyDamageCollectable3 = Spy(damageCollectable3)

            multiplier = 5
            def damageCollectable4 = new DamageCollectable(position,ship,multiplier)
            def spyDamageCollectable4 = Spy(damageCollectable4)

            multiplier = 10
            def damageCollectable5 = new DamageCollectable(position,ship,multiplier)
            def spyDamageCollectable5 = Spy(damageCollectable5)

        when:
            spyDamageCollectable1.execute()
            spyDamageCollectable2.execute()
            spyDamageCollectable3.execute()
            spyDamageCollectable4.execute()
            spyDamageCollectable5.execute()

        then:
            1 * spyDamageCollectable1.getAttackingElement().setShipMode(ShipMode.DAMAGE_2X)
            1 * spyDamageCollectable2.getAttackingElement().setShipMode(ShipMode.DAMAGE_3X)
            1 * spyDamageCollectable3.getAttackingElement().setShipMode(ShipMode.DAMAGE_4X)
            1 * spyDamageCollectable4.getAttackingElement().setShipMode(ShipMode.DAMAGE_5X)
            1 * spyDamageCollectable5.getAttackingElement().setShipMode(ShipMode.DAMAGE_10X)
    }


    def "ScoreCollectable"(){
        given:
            Alien alien = Mock(Alien)
            List<Alien> aliens = new ArrayList<>()
            aliens.add(alien)
            aliens.add(alien)
            aliens.add(alien)

        int multiplier = 2
        def scoreCollectableX2 = new ScoreCollectable(position,aliens,multiplier)
        def spyScoreCollectableX2 = Spy(scoreCollectableX2)

        multiplier = 3
        def scoreCollectableX3 = new ScoreCollectable(position,aliens,multiplier)
        def spyScoreCollectableX3 = Spy(scoreCollectableX3)

        multiplier = 4
        def scoreCollectableX4 = new ScoreCollectable(position,aliens,multiplier)
        def spyScoreCollectableX4 = Spy(scoreCollectableX4)

        multiplier = 5
        def scoreCollectableX5 = new ScoreCollectable(position,aliens,multiplier)
        def spyScoreCollectableX5 = Spy(scoreCollectableX5)

        multiplier = 10
        def scoreCollectableX10 = new ScoreCollectable(position,aliens,multiplier)
        def spyScoreCollectableX10 = Spy(scoreCollectableX10)

        when:
            spyScoreCollectableX2.execute()
            spyScoreCollectableX3.execute()
            spyScoreCollectableX4.execute()
            spyScoreCollectableX5.execute()
            spyScoreCollectableX10.execute()
        then:
            1 * spyScoreCollectableX2.getAttackingElement()
            3 * alien.setAlienMode(AlienMode.SCORE_2X)
            1 * spyScoreCollectableX3.getAttackingElement()
            3 * alien.setAlienMode(AlienMode.SCORE_3X)
            1 * spyScoreCollectableX4.getAttackingElement()
            3 * alien.setAlienMode(AlienMode.SCORE_4X)
            1 * spyScoreCollectableX5.getAttackingElement()
            3 * alien.setAlienMode((AlienMode.SCORE_5X))
            1 * spyScoreCollectableX10.getAttackingElement()
            3 * alien.setAlienMode(AlienMode.SCORE_10X)
    }

    def "MachineGunModeCollectable"(){
        given:
            def ship = Mock(Ship)
            def machineGunModeCollectable = new MachineGunModeCollectable(position,ship)
            def spyMachineGunModeCollectable = Spy(machineGunModeCollectable)

        when:
            spyMachineGunModeCollectable.execute()

        then:
            1 * spyMachineGunModeCollectable.getAttackingElement().setShipMode(ShipMode.MACHINE_GUN_MODE)
    }

    def "GodModeCollectable"(){
        given:
            def ship = Mock(Ship)
            def godModeCollectable = new GodModeCollectable(position,ship)
            def spyGodModeCollectable = Spy(godModeCollectable)

        when:
            spyGodModeCollectable.execute()
        then:
            1 * spyGodModeCollectable.getAttackingElement().setShipMode(ShipMode.GOD_MODE)
    }
}
