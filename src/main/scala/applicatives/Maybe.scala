package applicatives

/**
  * Created by siddhant.srivastava on 4/24/18.
  */
trait Maybe[+A]

case class Some[+A](v: A) extends Maybe[A]

case object None extends Maybe[Nothing]


object Maybe{

  implicit val maybeApplicative = new Applicative[Maybe] {

    override def ap[A, B](fa: Maybe[A])(f: Maybe[(A) => B]): Maybe[B] = (fa, f) match {
      case (Some(v), Some(f)) => Some(f.apply(v))
      case _ => None
    }

    override def pure[A](a: => A): Maybe[A] = Some(a)
  }

}
