package functor.custom

/**
  * Created by siddhant.srivastava on 4/23/18.
  */
sealed trait Maybe[+A]
case class Some[A](v: A) extends Maybe[A]
case object None extends Maybe[Nothing]

object Maybe{
  implicit val optionFunctor = new Functor[Maybe] {
    override def fmap[A, B](fa: Maybe[A])(f: A => B): Maybe[B] = fa match {
      case Some(v) => Some(f.apply(v))
      case _ => None
    }
  }
}
