package pld.sample

import java.awt.{LayoutManager, Color, Graphics}
import javax.swing.{BoxLayout, JPanel, JFrame}

object HelloWorld {
  def main(args: Array[String]) {
    println("Hello, world!")

    var frame : JFrame = new JFrame()
    frame.setSize(250, 250)
    
    val panel: JPanel = new JPanel() {
      override def paint(g: Graphics) = {
        g.setColor(Color.BLUE)
        g.drawRoundRect(10, 10, 180, 180, 50, 50)
        
      }

    }
    frame.getContentPane().add(panel)
    frame.setVisible(true)
    
  }
}