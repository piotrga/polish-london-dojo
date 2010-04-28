package pld.gameoflife

import org.junit.Test


//The Rules
//
//For a space that is 'populated':
// - Each cell with one or no neighbors dies, as if by loneliness.
// - Each cell with four or more neighbors dies, as if by overpopulation.
// - Each cell with two or three neighbors survives.
//
//For a space that is 'empty' or 'unpopulated':
// - Each cell with three neighbors becomes populated.

class CellTest {
  @Test
  def cellDiesWhenHas1Neighbour() {
    assert(!isCellAliveForNeighbours(1, List(0, 0, 1, 0, 0, 0, 0, 0)))
  }

  @Test
  def cellSurvivesWhenHas3Neighbours() {
    assert(isCellAliveForNeighbours(1, List(1, 1, 1, 0, 0, 0, 0, 0)))
  }

  @Test
  def cellSurvivesWhenHas2Neighbours() {
    assert(isCellAliveForNeighbours(1, List(1, 1, 0, 0, 0, 0, 0, 0)))
  }

  def isCellAliveForNeighbours(isAlive: Int, neighbours: List[Int]): Boolean = {
    val cell = new Cell(isAlive == 1)
    cell.init(neighbours map (new Cell(_ == 1)))

    cell.evaluateNextState()

    cell.moveToNextState()
    return cell.isCellAlive()
  }

}

class Cell(var isAlive: Boolean) {
  var neighbours: List[Cell] = _
  var nextState = false

  def init(list: List[Cell]) = {
    neighbours = list
  }

  def evaluateNextState() = {
    val aliveCount = neighbours.filter(_.isCellAlive()).length
    nextState = (aliveCount > 1 && aliveCount < 4)

  }

  def moveToNextState() = {
    isAlive = nextState

  }

  def isCellAlive(): Boolean = isAlive

}