package monoid

import monoid.Monoid.MonoidalReducer

/**
  * Created by siddhant.srivastava on 4/24/18.
  */
object SampleRunner {

  def main(args: Array[String]) {
    import Monoid._
    println(Monoids[Int].op(1, Monoids[Int].zero))

    val li = List(1,2,3,4,5)
    println(foldMap(li))

    import ListMonoid._

    println(li.foldMap)

  }

  def foldMap(li: List[Int])(implicit lm: MonoidalReducer[List]) = lm.foldMap(li)

}
