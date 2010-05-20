package pld.gameoflife

import actors.Actor._
import org.junit.Test
import java.lang.Thread._
import actors.{TIMEOUT, Actor}


case object AwaitingNextTurn
case object NextTurn
case object Alive

class Cell() extends Actor{
  var coordinator: Actor = _
  var neighbours: List[Actor] = _

  def act {
    while(true) {
      var statusCount = 0
      while(statusCount < neighbours.length){
        receive{ case Alive => statusCount += 1 }
      }
      println("c1")
      coordinator ! AwaitingNextTurn
      println("c2")
      receive { case NextTurn => println("cell: received NextTurn") }
      println("c3")
    }
  }

}


class CellTest{

  @Test
  def cellWaitsAfterNeighboursStateReceived(){
    var waitsAfterNeighboursStateReceived = false
    val cell = new Cell
    val neighbour = actor {
      cell ! Alive
      loop { react { case "sent?" => println("sent?"); sender !"sent." }}
    }
    val coordinator = actor{
      receiveWithin(1000) { case AwaitingNextTurn => println("Awaiting "+ (neighbour !? "sent?")); waitsAfterNeighboursStateReceived = ((neighbour !? "sent?") == "sent.") }
    }
    cell.coordinator = coordinator
    cell.neighbours = List(neighbour)
    coordinator.start()
    sleep(100)
    cell.start()
    sleep(100)
    neighbour.start()
    assert(waitsAfterNeighboursStateReceived)
  }

  @Test
  def cellWorksInCycle(){
    var reachedFullCycle = false
    val coordinator = actor{
      receive { case AwaitingNextTurn =>{
        receiveWithin(100) {
          case TIMEOUT => None
          case AwaitingNextTurn => throw new RuntimeException("Should wait for next turn ")
        }
        sender ! NextTurn}
      }
      //println("coordinator: before second receive")
      receiveWithin(100) { case AwaitingNextTurn => reachedFullCycle = true}
    }
    val cell = new Cell()
    cell.neighbours = List()
    cell.coordinator = coordinator
    coordinator.start()
    cell.start()
    Thread.sleep(300)
    assert(reachedFullCycle)
    
  }


}
