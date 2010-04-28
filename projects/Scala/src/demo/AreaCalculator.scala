// concepts:
// - functional style: higher-order functions, type aliases
// - testing in REPL

object AreaCalculatorApp {
  def main(args: Array[String]) = println(area(readVertices(args(0))))

  type Vertex = (Double, Double)
  
  def area(vertices: Seq[Vertex]) = triangulate(vertices) map(triangleArea _) sum

  def triangulate(vertices: Seq[Vertex]) = vertices match { case a :: b :: c :: t => t.foldLeft(List((a, b, c)))(nextTriangle _) }
    
  type Triangle = (Vertex, Vertex, Vertex)

  def nextTriangle(prevTriangles: List[Triangle], d: Vertex) = prevTriangles last match { case (a, b, c) => prevTriangles :+ (a, c, d) }
  
  def triangleArea(triangle: Triangle) = triangle match { 
    case (a, b, c) => math.abs(a._1 * b._2 - a._1 * c._2 + b._1 * c._2 - b._1 * a._2 + c._1 * a._2 - c._1 * b._2) / 2 
  }

  def readVertices(path: String) = toVertices(fileContentsAsSeqOfDoubles(path))

  def fileContentsAsSeqOfDoubles(path: String) = (io.Source fromPath(path) mkString) split("\\s") filter(_.length > 0) map(_.toDouble)

  def toVertices(coords: Seq[Double]) = coords grouped(2) map((p: Seq[Double]) => (p(0), p(1))) toSeq
}
