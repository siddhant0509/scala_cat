package functor.custom

/**
  * Created by siddhant.srivastava on 4/23/18.
  */
sealed trait List[+A]

case class Cons[+A](x: A, xs: List[A]) extends List[A]

case object Nothing extends List[Nothing]

object List{

  implicit val listFunctor = new Functor[List] {
    override def fmap[A, B](fa: List[A])(f: (A) => B): List[B] = fa match {
      case Cons(x, xs) => Cons(f(x), fmap(xs)(f))
      case Nothing => Nothing
    }
  }

}