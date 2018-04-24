package applicatives

import functor.custom.Functor

/**
  * Created by siddhant.srivastava on 4/24/18.
  */
trait Applicative[F[_]] extends Functor[F]{

  def pure[A](a: => A): F[A]

  def ap[A,B](fa: F[A])(f: F[A => B]): F[B]

  def <*>[A,B](fa: F[A])(f: F[A => B]) = ap(fa)(f)

  override def fmap[A, B](fa: F[A])(f: (A) => B): F[B] = ap(fa)(pure(f))

  def ap2[A,B,C](f: (A,B) => C)(fa: F[A], fb: F[B]): F[C] = <*>(fb)(fmap(fa)(a => f(a, _: B)))


}


object Applicative{

  case class Per[A, B](v: A, n: B)



}