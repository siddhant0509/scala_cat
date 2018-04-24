package monads

/**
  * Created by siddhant.srivastava on 4/24/18.
  */
trait Maybe[+A]

case class Some[+A](v: A) extends Maybe[A]

case object None extends Maybe[Nothing]

object Maybe{

  implicit val maybeMonad = new Monad[Maybe] {

    override def bind[A, B](ma: Maybe[A])(f: (A) => Maybe[B]): Maybe[B] = ma match {
      case Some(v) => f(v)
      case _ => None
    }

    override def pure[A](a: => A): Maybe[A] = {
      lazy val evA = a
      evA match {
        case null => None
        case _ => Some(evA)
      }
    }
  }
}
