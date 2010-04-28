// concepts:
// - actors
// - pattern matching
// - Option (None/Some(x))

// -- enums etc
object Figure extends Enumeration {
  type Figure = Value
  val Paper, Scissors, Stone = Value
}

import Figure._

object Messages {
  case object Play
  case object End
  case class StartMatch(toWins: Int)
  case class Move(figure: Figure)
}

import Messages._

// -- the app
object PaperScissorsStoneApp {
  def main(args: Array[String]) {
    val player1 = new Player(1)
    val player2 = new Player(2)
    val referee = new Referee(player1, player2)
    player1.start()
    player2.start()
    referee.start()
    referee ! StartMatch(args(0) toInt)
  }
}

import scala.actors._

// -- the player
class Player(val id: Int) extends Actor {
  val random = new scala.util.Random()

  def act() {
    loop {
      receive {
        case Play => sender ! Move(chooseFigure())
        case End => exit()
      }
    }
  }
  
  def chooseFigure() = (random.nextInt(3)) match {
    case 0 => Paper
    case 1 => Scissors
    case 2 => Stone
  }
}

// -- the referee
class Referee(val player1: Player, val player2: Player) extends Actor {
  var toWins = 0
  import scala.collection.mutable.Map
  val wins = Map(player1 -> 0, player2 -> 0)
  val currentMove = Map[Player, Option[Figure]](player1 -> None, player2 -> None)
  
  def act() {
    loop {
      receive {
        case StartMatch(toWins) => startMatch(toWins)
        case Move(figure) => recordMove(sender.asInstanceOf[Player], figure)
      }
    }
  }

  def startMatch(toWins: Int) {
    this.toWins = toWins
    startRound()
  }
    
  def startRound() {
    println("round starting")
    currentMove(player1) = None
    currentMove(player2) = None
    player1 ! Play
    player2 ! Play
  }
  
  def recordMove(player: Player, figure: Figure) {
    println("player " + player.id + " played " + figure)
    currentMove(player) = Some(figure)
    if (!currentMove.values.exists(_ == None)) finishRound()
  }
  
  def finishRound() = winnerOfCurrentRound() match {
    case None => recordDraw()
    case Some(player) => recordWin(player)
  }
  
  def winnerOfCurrentRound() = (currentMove(player1).get, currentMove(player2).get) match {
    case (Paper, Paper) => None
    case (Paper, Scissors) => Some(player2)
    case (Paper, Stone) => Some(player1)
    case (Scissors, Paper) => Some(player1)
    case (Scissors, Scissors) => None
    case (Scissors, Stone) => Some(player2)
    case (Stone, Paper) => Some(player2)
    case (Stone, Scissors) => Some(player1)
    case (Stone, Stone) => None
  }

  def recordDraw() {
    println("round ended in a draw")
    startRound()  
  }
   
  def recordWin(player: Player) {
    wins(player) += 1
    println("player " + player.id + " won this round; overall score: " + wins(player1) + ":" + wins(player2))
    if (wins(player) == toWins) {
      println("player " + player.id + " won the match")
      finishGame()
    } else startRound()
  }
  
  def finishGame() {
    player1 ! End
    player2 ! End
    exit()
  }
}
