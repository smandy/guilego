package praxis.guilego

import java.util.concurrent.atomic.AtomicReference
import javax.swing.SwingUtilities

object PraxisSwingUtil {
  def invokeLaterAndWaitForResult[T]( f : => T ) : T = {
    val ref = new AtomicReference[T]()
    SwingUtilities.invokeAndWait( new Runnable() {
      override def run {
        ref.set( f )
      }
    } )
    ref.get()
  }

  def invokeLater(f: => Unit) = SwingUtilities.invokeLater(new Runnable() {
    override def run() = f
  })

  def invokeAndWait(f: => Unit) = SwingUtilities.invokeAndWait(new Runnable() {
    override def run() = f
  })
}