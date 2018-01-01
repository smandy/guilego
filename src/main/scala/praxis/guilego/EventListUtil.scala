package argo.guilego

import ca.odell.glazedlists.{BasicEventList, EventList}

object EventListUtil {
  def acquireLocks[T](obj: EventList[T]): Unit = {
    obj.getReadWriteLock().writeLock.lock()
    obj.getReadWriteLock().readLock.lock()
  }

  def releaseLocks[T](obj: EventList[T]): Unit = {
    obj.getReadWriteLock().readLock.unlock()
    obj.getReadWriteLock().writeLock.unlock()
  }

  def withLockOn[T](obj: BasicEventList[T])(f: => Unit) {
    acquireLocks(obj)
    try {
      f
    } finally {
      releaseLocks(obj)
    }
  }

}
