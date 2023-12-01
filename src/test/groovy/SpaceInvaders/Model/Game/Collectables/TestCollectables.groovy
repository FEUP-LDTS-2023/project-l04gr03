package SpaceInvaders.Model.Game.Collectables


import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.AlienMode
import SpaceInvaders.Model.Game.RegularGameElements.AttackingElement
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Game.RegularGameElements.ShipMode
import SpaceInvaders.Model.Position
import spock.lang.Specification

class TestCollectables extends Specification {

    def "HealthCollectable"(){
        given:
        Position position = Mock(Position)
        Ship ship = Mock(Ship)
        HealthCollectable healthCollectable = new HealthCollectable(position,ship)
        def spyHealthCollectable = Spy(healthCollectable)
        when:
        spyHealthCollectable.execute()
        then:
        1 * spyHealthCollectable.getAttackingElement().restoreHealth()
    }

    def "DamageCollectable"(){
        given:
        Position position = Mock(Position)
        Ship ship = Mock(Ship)
        int multiplier = 2
        DamageCollectable damageCollectable = new DamageCollectable(position,ship,multiplier)
        def spyDamageCollectable = Spy(damageCollectable)
        int multiplier1 = 10
        DamageCollectable damageCollectable1 = new DamageCollectable(position,ship,multiplier1)
        def spyDamageCollectable1 = Spy(damageCollectable1)
        when:
        spyDamageCollectable.execute()
        spyDamageCollectable1.execute()
        then:
        1 * spyDamageCollectable.getAttackingElement().setShipMode(ShipMode.DAMAGE_2X)
        1 * spyDamageCollectable1.getAttackingElement().setShipMode(ShipMode.DAMAGE_10X)
    }

    def "ScoreCollectable"(){
        given:
        Position position = Mock(Position)
        Alien alien = Mock(Alien)
        int multiplier = 3
        List<Alien> aliens = new ArrayList<>()
        aliens.add(alien)
        aliens.add(alien)
        aliens.add(alien)
        ScoreCollectable scoreCollectable = new ScoreCollectable(position,aliens,multiplier)
        def spyScoreCollectable = Spy(scoreCollectable)
        int multiplier1 = 5
        ScoreCollectable scoreCollectable1 = new ScoreCollectable(position,aliens,multiplier1)
        def spyScoreCollectable1 = Spy(scoreCollectable1)
        when:
        spyScoreCollectable.execute()
        spyScoreCollectable1.execute()
        then:
        1 * spyScoreCollectable.getAttackingElement()
        3 * alien.setAlienMode(AlienMode.SCORE_3X)
        1 * spyScoreCollectable1.getAttackingElement()
        3 * alien.setAlienMode((AlienMode.SCORE_5X))
    }

    def "MachineGunModeCollectable"(){
        given:
        Position position = Mock(Position)
        Ship ship = Mock(Ship)
        MachineGunModeCollectable machineGunModeCollectable = new MachineGunModeCollectable(position,ship)
        def spyMachineGunModeCollectable = Spy(machineGunModeCollectable)
        when:
        spyMachineGunModeCollectable.execute()
        then:
        1 * spyMachineGunModeCollectable.getAttackingElement().setShipMode(ShipMode.MACHINE_GUN_MODE)
    }

    def "GodModeCollectable"(){
        given:
        Position position = Mock(Position)
        Ship ship = Mock(Ship)
        GodModeCollectable godModeCollectable = new GodModeCollectable(position,ship)
        def spyGodModeCollectable = Spy(godModeCollectable)
        when:
        spyGodModeCollectable.execute()
        then:
        1 * spyGodModeCollectable.getAttackingElement().setShipMode(ShipMode.GOD_MODE)
    }
}
