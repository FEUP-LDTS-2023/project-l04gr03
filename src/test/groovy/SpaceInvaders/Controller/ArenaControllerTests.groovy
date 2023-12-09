package SpaceInvaders.Controller

import SpaceInvaders.Controller.Game.ArenaController
import SpaceInvaders.Controller.Game.ShipController
import SpaceInvaders.Model.Game.Arena
import SpaceInvaders.Model.Game.ArenaModifier
import SpaceInvaders.Model.Game.Element
import SpaceInvaders.Model.Game.RegularGameElements.Alien
import SpaceInvaders.Model.Game.RegularGameElements.CoverWall
import SpaceInvaders.Model.Game.RegularGameElements.Projectile
import SpaceInvaders.Model.Game.RegularGameElements.Ship
import SpaceInvaders.Model.Game.RegularGameElements.Wall
import SpaceInvaders.Model.Position
import spock.lang.Specification

class ArenaControllerTests extends Specification{

    def "Collision between - False"(){
        given:
        def arenaController = Spy(ArenaController.class)
        def element1 = Mock(Element.class)
        def element2 = Mock(Element.class)
        element1.getPosition() >> new Position(24,14)
        element2.getPosition() >> new Position(30,14)
        when:
        boolean isColliding = arenaController.collisionBetween(element1,element2)
        then:
        !isColliding
    }

    def "Collision between - True"(){
        given:
        def arenaController = Spy(ArenaController.class)
        def element1 = Mock(Element.class)
        def element2 = Mock(Element.class)
        element1.getPosition() >> new Position(10,27)
        element2.getPosition() >> new Position(10,27)
        when:
        boolean isColliding = arenaController.collisionBetween(element1,element2)
        then:
        isColliding
    }

    def "ShipCollidesWithAlien - True"(){
      given:
      def arenaController = Spy(ArenaController.class)
      def arena = Mock(Arena.class)
      def arenaModifier = Mock(ArenaModifier.class)
      def ship = Mock(Ship.class)
      def alien1 = Mock(Alien.class)
      def alien2 = Mock(Alien.class)
      def alien3 = Mock(Alien.class)
      def position = Mock(Position.class)
      List<Alien> aliens = new ArrayList<>()
      alien1.getPosition() >> position
      alien2.getPosition() >> position
      alien3.getPosition() >> position
      ship.getPosition() >> position
      aliens.add(alien1)
      aliens.add(alien2)
      aliens.add(alien3)
      arenaController.getModel() >> arena
      arenaController.getArenaModifier() >> arenaModifier
      arena.getShip() >> ship
      arena.getAliens() >> aliens
      when:
      arenaController.collisionBetween(ship, alien1) >> true
      arenaController.collisionBetween(ship, alien2) >> false
      arenaController.collisionBetween(ship, alien3) >> false
      boolean isColliding = arenaController.shipCollidesWithAlien()
      then:
      isColliding
    }


    def "ShipCollidesWithAlien - False"(){
        given:
        def arenaController = Spy(ArenaController.class)
        def arena = Mock(Arena.class)
        def arenaModifier = Mock(ArenaModifier.class)
        def ship = Mock(Ship.class)
        def alien1 = Mock(Alien.class)
        def alien2 = Mock(Alien.class)
        def alien3 = Mock(Alien.class)
        def alien4 = Mock(Alien.class)
        def position = Mock(Position.class)
        List<Alien> aliens = new ArrayList<>()
        alien1.getPosition() >> position
        alien2.getPosition() >> position
        alien3.getPosition() >> position
        alien4.getPosition() >> position
        ship.getPosition() >> position
        aliens.add(alien1)
        aliens.add(alien2)
        aliens.add(alien3)
        aliens.add(alien4)
        arenaController.getModel() >> arena
        arenaController.getArenaModifier() >> arenaModifier
        arena.getShip() >> ship
        arena.getAliens() >> aliens
        when:
        arenaController.collisionBetween(ship,alien1) >> false
        arenaController.collisionBetween(ship,alien2) >> false
        arenaController.collisionBetween(ship, alien3) >> false
        arenaController.collisionBetween(ship, alien4) >> false
        boolean isColliding = arenaController.shipCollidesWithAlien()
        then:
        !isColliding
    }

    def "AlienCollidesWithCoverWalls - True"(){
        given:
        def arenaController = Spy(ArenaController.class)
        def arena = Mock(Arena.class)
        def coverWall1 = Mock(CoverWall.class)
        def coverWall2 = Mock(CoverWall.class)
        def alien1 = Mock(Alien.class)
        def alien2 = Mock(Alien.class)
        def alien3 = Mock(Alien.class)
        def position = Mock(Position.class)
        alien1.getPosition() >> position
        alien2.getPosition() >> position
        alien3.getPosition() >> position
        List<Alien> aliens = new ArrayList<>(Arrays.asList(alien1,alien2,alien3))
        List<CoverWall> coverWalls = new ArrayList<>(Arrays.asList(coverWall1,coverWall2))
        arenaController.getModel() >> arena
        arena.getAliens() >> aliens
        arena.getCoverWalls() >> coverWalls
        when:
        arenaController.collisionBetween(alien1,coverWall1) >> false
        arenaController.collisionBetween(alien1,coverWall2) >> false
        arenaController.collisionBetween(alien2,coverWall1) >> false
        arenaController.collisionBetween(alien2,coverWall2) >> true
        arenaController.collisionBetween(alien3,coverWall1) >> false
        arenaController.collisionBetween(alien3,coverWall2) >> false
        then:
        arenaController.alienCollidesWithCoverWall()
    }

    def "AlienCollidesWithCoverWalls - False"(){
        given:
        def arenaController = Spy(ArenaController.class)
        def arena = Mock(Arena.class)
        def coverWall1 = Mock(CoverWall.class)
        def coverWall2 = Mock(CoverWall.class)
        def alien1 = Mock(Alien.class)
        def alien2 = Mock(Alien.class)
        def alien3 = Mock(Alien.class)
        def position = Mock(Position.class)
        alien1.getPosition() >> position
        alien2.getPosition() >> position
        alien3.getPosition() >> position
        List<Alien> aliens = new ArrayList<>(Arrays.asList(alien1,alien2,alien3))
        List<CoverWall> coverWalls = new ArrayList<>(Arrays.asList(coverWall1,coverWall2))
        arenaController.getModel() >> arena
        arena.getAliens() >> aliens
        arena.getCoverWalls() >> coverWalls
        when:
        arenaController.collisionBetween(alien1,coverWall1) >> false
        arenaController.collisionBetween(alien1,coverWall2) >> false
        arenaController.collisionBetween(alien2,coverWall1) >> false
        arenaController.collisionBetween(alien2,coverWall2) >> false
        arenaController.collisionBetween(alien3,coverWall1) >> false
        arenaController.collisionBetween(alien3,coverWall2) >> false
        then:
        !arenaController.alienCollidesWithCoverWall()
    }
}
