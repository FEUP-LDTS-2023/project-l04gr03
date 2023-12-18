package SpaceInvaders.Model.Game

import SpaceInvaders.Model.Game.Collectables.DamageCollectable
import SpaceInvaders.Model.Game.Collectables.ScoreCollectable
import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.AlienMode
import SpaceInvaders.Model.Game.RegularGameElements.AlienState
import SpaceInvaders.Model.Game.RegularGameElements.CoverWall
import SpaceInvaders.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Game.RegularGameElements.ShipMode
import SpaceInvaders.Model.Position
import spock.lang.Specification

class TestArenaModifier extends Specification{

    def "RemoveAlien"(){
        given:
            def arena = new Arena(75,30)
            def arenaModifier = new ArenaModifier(arena)

            List<Alien> aliens = new ArrayList<>()
            aliens.add(new Alien(new Position(13,11),30,20,20,AlienState.PASSIVE,0))
            aliens.add(new Alien(new Position(13,13),30,20,20,AlienState.PASSIVE,1))
            aliens.add(new Alien(new Position(13,15),30,20,20,AlienState.ATTACKING,2))
            aliens.add(new Alien(new Position(16,11),30,20,20,AlienState.ATTACKING,0))
            arena.setAliens(aliens);

        when: "removed 1 alien"
            arenaModifier.removeAlien(aliens.get(3))

        then:
            arena.getAliens().size() == 3
            arena.getAliens().get(2).getAlienState() == AlienState.ATTACKING
            arena.getAliens().get(1).getAlienState() == AlienState.PASSIVE
            arena.getAliens().get(0).getAlienState() == AlienState.PASSIVE

        when: "removed 2 aliens"
            arenaModifier.removeAlien(aliens.get(2))

        then:
            arena.getAliens().size() == 2
            arena.getAliens().get(1).getAlienState() == AlienState.ATTACKING
            arena.getAliens().get(0).getAlienState() == AlienState.PASSIVE

        when: "Removed 3 aliens"
            arenaModifier.removeAlien(aliens.get(1))

        then:
            arena.getAliens().size() == 1
            arena.getAliens().get(0).getAlienState() == AlienState.ATTACKING

        when: "Removed 4 aliens"
            arenaModifier.removeAlien(aliens.get(0))

        then:
            arena.getAliens().empty
    }


    def "RemoveAlien - alien not in alien list"(){
        given:
            def arena = new Arena(75,30)
            def alien = new Alien(Mock(Position), 10,10,10, AlienState.PASSIVE,1)
            def arenaModifier = new ArenaModifier(arena)

            List<Alien> aliens = new ArrayList<>()
            aliens.add(new Alien(new Position(13,11),30,20,20,AlienState.PASSIVE,0))
            aliens.add(new Alien(new Position(13,13),30,20,20,AlienState.PASSIVE,1))
            aliens.add(new Alien(new Position(13,15),30,20,20,AlienState.ATTACKING,2))
            aliens.add(new Alien(new Position(16,11),30,20,20,AlienState.ATTACKING,0))

            arena.setAliens(aliens);

        when:
            arenaModifier.removeAlien(alien)

        then:
            aliens.size() == 4
            0 * aliens.remove(_)
    }


    def "RemoveCoverWall"(){
        given:
            def arena = new Arena(75,30)
            def arenaModifier = new ArenaModifier((arena))

            List<CoverWall> coverWalls = new ArrayList<>()
            coverWalls.add(new CoverWall(new Position(15,24),100))
            coverWalls.add(new CoverWall(new Position(45,24),100))

            arena.setCoverWalls(coverWalls)
        when:
            arenaModifier.removeCoverWall(coverWalls.get(0))

        then:
            arena.getCoverWalls().size() == 1
            arena.getCoverWalls().get(0).getPosition().equals(new Position(45,24))
    }

    def "AddProjectile"(){
        given:
            def arena = new Arena(75,30)
            def alien = Mock(Alien)
            def projectile1 = new Projectile(new Position(13,15),alien)
            def projectile2 = new Projectile(new Position(19,15),alien)
            def arenaModifier = new ArenaModifier(arena)

            List<Projectile> projectiles = new ArrayList<>()
            arena.setProjectiles(projectiles)

        when:
            arenaModifier.addProjectile(projectile1)
            arenaModifier.addProjectile(projectile2)

        then:
            arena.getProjectiles().size() == 2
    }

    def "RemoveProjectile"(){
        given:
            def arena = new Arena(75,30)
            List<Projectile> projectiles = new ArrayList<>()
            def alien = Mock(Alien)
            def ship = Mock(Ship)
            def arenaModifier = new ArenaModifier((arena))
            projectiles.add(new Projectile(new Position(13,15),alien))
            projectiles.add(new Projectile(new Position(35,26),ship))
            projectiles.add(new Projectile(new Position(10,10),ship))
            arena.setProjectiles(projectiles)

        when:
            arenaModifier.removeProjectile(projectiles.get(2))
            arenaModifier.removeProjectile(projectiles.get(0))

        then:
            arena.getProjectiles().size() == 1
            arena.getProjectiles().get(0).getPosition().equals(new Position(35,26))
    }

    def "RemoveActiveCollectable"(){
        given:
            def arena = Mock(Arena)
            def arenaModifier = new ArenaModifier(arena)

        when:
            arenaModifier.removeActiveCollectable()

        then:
            1 * arena.setActiveCollectable(null)
    }

    def "ResetShipMode"(){
        given:
            def arena = new Arena(75,30)
            def arenaModifier = new ArenaModifier(arena)
            def ship = Mock(Ship)
            arena.setShip(ship)

        when:
            arenaModifier.resetShipMode()

        then:
            1 * ship.setShipMode(ShipMode.NORMAL_MODE)
    }

    def "ResetAliensMode"() {
        given:
            def arena = new Arena(75, 30)
            def arenaModifier = new ArenaModifier(arena)
            def alien = Mock(Alien)
            List<Alien> aliens = new ArrayList<>()
            arena.setAliens(aliens)

        when:
            aliens.add(alien)
            aliens.add(alien)
            aliens.add(alien)
            aliens.add(alien)
            aliens.add(alien)
            arenaModifier.resetAliensMode()

        then:
            5 * alien.setAlienMode(AlienMode.NORMAL_MODE)
    }

    def "CreateAlienShip movement -1"() {
        given:
            def arena = new Arena(75, 30)
            def arenaModifier = new ArenaModifier((arena))
            def randomMock = Mock(Random)
            arenaModifier.setRandom(randomMock)
            randomMock.nextInt(2) >> 0

        when:
            arenaModifier.createAlienShip()

        then:
            arena.getAlienShip().getMovementDirection() == -1
            arena.getAlienShip().getPosition().equals(new Position(71,6))
            arena.getAlienShip().getHealth() == 50
            arena.getAlienShip().getScore() == 1000
    }

    def "CreateAlienShip movement 0"() {
        given:
            def arena = new Arena(75, 30)
            def arenaModifier = new ArenaModifier((arena))
            def randomMock = Mock(Random)
            arenaModifier.setRandom(randomMock)
            randomMock.nextInt(2) >> 1

        when:
            arenaModifier.createAlienShip()

        then:
            arena.getAlienShip().getMovementDirection() == 1
            arena.getAlienShip().getPosition().equals(new Position(4,6))
            arena.getAlienShip().getHealth() == 50
            arena.getAlienShip().getScore() == 1000
    }

    def "RemoveAlienShip"() {
        given:
            def arena = Mock(Arena)
            def arenaModifier = new ArenaModifier(arena)

        when:
            arenaModifier.removeAlienShip()

        then:
            1 * arena.setAlienShip(null)
    }

    def "create collectable random element 0"(){
        given:
            def arena = Mock(Arena)
            def arenaModifier = new ArenaModifier(arena)
            def randomMock = Mock(Random)
            def position = new Position(3,1)
            def arenaModifierSpy = Spy(arenaModifier)

            arenaModifierSpy.setRandom(randomMock)
            List<Integer> freeColumns = Arrays.asList(3,1)
            arena.getFreeArenaColumns() >> freeColumns
            randomMock.nextInt(2) >> 0

        when:
            arenaModifierSpy.createCollectable()

        then:
            1 * arenaModifierSpy.createCollectableAffectingShip(position)
    }

    def "create collectable random element 1"(){
        given:
            def arena = Mock(Arena)
            def arenaModifier = new ArenaModifier(arena)
            def randomMock = Mock(Random)
            def position = new Position(4,1)
            def arenaModifierSpy = Spy(arenaModifier)

            arenaModifierSpy.setRandom(randomMock)
            List<Integer> freeColumns = Arrays.asList(3,4)
            arena.getFreeArenaColumns() >> freeColumns
            randomMock.nextInt(2) >> 1

        when:
            arenaModifierSpy.createCollectable()
        then:
            1 * arenaModifierSpy.createCollectableAffectingAliens(position)
    }

    def "create collectable affecting Ship"(){
        given:
            def arena = new Arena(74,37)
            def arenaModifier = new ArenaModifier(arena)
            def randomMock = Mock(Random)
            def position = new Position(10,10)

            arenaModifier.setRandom(randomMock)
            randomMock.nextInt(4) >> 1
            randomMock.nextInt(5) >> 2

        when:
            arenaModifier.createCollectableAffectingShip(position)

        then:
            arena.getActiveCollectable().getPosition() == position
            arena.getActiveCollectable().getClass() == DamageCollectable.class
            arena.getActiveCollectable().getMultiplier() == 4
    }

    def "create collectable affecting aliens"(){
        given:
            def arena = new Arena(74,37)
            def arenaModifier = new ArenaModifier(arena)
            def randomMock = Mock(Random)
            def position = new Position(10,10)

            arenaModifier.setRandom(randomMock)
            randomMock.nextInt(5) >> 2

        when:
            arenaModifier.createCollectableAffectingAliens(position)

        then:
            arena.getActiveCollectable().getPosition() == position
            arena.getActiveCollectable().getClass() == ScoreCollectable.class
            arena.getActiveCollectable().getMultiplier() == 4
    }

    def "hasAlienInFront"(){
        given:
            def arena = new Arena(74,37)
            def alien = new Alien(new Position(2,1),10,10,10,AlienState.PASSIVE,1)
            def excludedAlien = new Alien(new Position(3,2),10,10,10,AlienState.PASSIVE,1)
            def otherAlien = new Alien(new Position(2,2),10,10,10,AlienState.PASSIVE,1)
            def arenaModifier = new ArenaModifier(arena)

            List<Alien> aliens = Arrays.asList(alien, otherAlien, excludedAlien)
            arena.setAliens(aliens)

        expect:
            arenaModifier.hasAlienInFront(alien, excludedAlien)

    }

    def "hasAlienInFront false"(){
        given:
            def arena = new Arena(74,37)
            def alien = new Alien(new Position(2,1),10,10,10,AlienState.PASSIVE,1)
            def excludedAlien = new Alien(new Position(3,2),10,10,10,AlienState.PASSIVE,1)
            def otherAlien = new Alien(new Position(4,2),10,10,10,AlienState.PASSIVE,1)
            def arenaModifier = new ArenaModifier(arena)

            List<Alien> aliens = Arrays.asList(alien,otherAlien,  excludedAlien)
            arena.setAliens(aliens)

        expect:
            !arenaModifier.hasAlienInFront(alien, excludedAlien)
    }

}
