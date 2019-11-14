package threads

/*
 * Computer systems are expected to support multitasking. For example,
 * a personal computer is expected to play music, edit text, featch and
 * and notify email all at the same time. Even a single application such as
 * an audio player has to do multiple tasks at the same time including featching
 * the stream containing the music, decompressing the music and accepting user
 * commands simultaniously. Such software are called concurrent software.
 * many programming languages such as Java and Scala have a built-in support for
 * programming concurrent applications.
 *
 * One of the basic units of execution in concurrent applications are Threads.
 * Threads are the smallest set of instructions that can be executed and managed
 * independently by a computer system.
 *
 *
 * Task: In this exercise we implement a paralleMap using Threads. The task is to
 * start threads that run the function f (passed as first parameter) on each elment of
 * the array as (passed as a second parameter) and produce bs (the return value). Note
 * that you have to create a separete thread for each element of the array as. Also remember
 * to start and joing the thereads. Note also that Scala inherits its low level concurrency
 * mechanism including Threads from Java.
 *
 * Hint: You may use Thread and Runnable. See https://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html.
 */

class Threads {
  /* Starts threads to apply f to each element of array as to produce a new array bs */
  def parallelMap[A, B: scala.reflect.ClassTag](f: A => B, as: Array[A]): Array[B] = {
    //var myThreads: Array[Thread]; // New array of threads
    val argSize: Int = as.length
    var result: Array[B] = new Array[B](argSize); // Same size as our int array

    for (i <- 0 to argSize) {
      val thread = new Thread {
          override def run {
              result(i) = f(as(i));
          }
      }
      thread.start
    }
    result
  }
}
