package lockFree
import instrumentation.monitors.LockFreeMonitor
import atomicReference.SimpleAtomicReference

/*
* One of the main uses of a atomic types is to implement syncronization
* perimitives such as semaphores and mutexes. It is also useul to implement
* lock-free algorithms.
*
* Task: In this exersise we implement a lock-free stack using an atomic reference.
* Assume that you have a class implementing an atomic refrence called
* SimpleAtomicReference with an interface as follows:
*
* class SimpleAtomicReference[V](initValue: V) {
*  protected var value:V
*
*  def compareAndSet(expect: V, update: V): Boolean
*  def get: V
*  def set(newValue: V): Unit
*}
*
* Hint: refer and use compareAndSet(expect: V, update: V): Boolean method from
* past exercises.
*/

/* Implement a lock free stack. */
class LockFreeStack[E](capacity: Int) extends LockFreeMonitor {
  class Node[E](val value: E) {
    var next: Node[E] = null
  }
  val top = new SimpleAtomicReference[Node[E]](null)
  // Do not add other variables

  def push(e: E): Unit = {
    if (top.get == null) {
      top.compareAndSet(null, new Node(e))
    } else {
      var old = top.get
      top.compareAndSet(old, new Node(e))
      top.get.next = old
    }
  }

  def pop(): E = {
    var old = top.get
    if (old != null) {
      top.compareAndSet(old, top.get.next)
    }
    old.value
  }
}
