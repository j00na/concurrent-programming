package atomicReference
/* One issue that needs to be noted when implementing concurrent
 * programs is memory consistency and errors associated with it. We
 * say memory consistency error occured when multiple execution units
 * operating on a shared memory have inconsistent view of a given
 * shared variable. There are a lot of reasons why this inconsistanceies
 * happen which include caching and compiler optimizations. The key to
 * avoid memory concistency problem is to understand and insure happens-before
 * relationship such that if a happens-before b, the effects of a will be visible
 * to b.
 *
 * Task: In this exercise we implement a simple atomic reference; a container to a value
 * that may be updated atomically and provides a happen-before relationship when
 * reading and writing a value.
 *
 * Hint: You may use syncronized key word to implement happens-before
*/

class SimpleAtomicReference[V](initValue: V) extends AbstractAtomicReference[V] {
  value = initValue

  def compareAndSet(expect: V, update: V): Boolean = {
    this.synchronized {
      if (expect == value) {
        value = update
        true
      } else {
        false
      }
    }
  }

  def get: V = {
    value
  }

  def set(newValue: V): Unit = {
    value = newValue
  }
}
