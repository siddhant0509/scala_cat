package monads

import applicatives.Applicative

/**
  * Created by siddhant.srivastava on 4/24/18.
  */
trait Monad[M[_]] extends Applicative[M]{

  def bind[A,B](ma: M[A])(f: A => M[B]): M[B]

  def `return`[B](m: M[M[B]]): M[B] = bind(m)(identity)

  override def ap[A, B](ma: M[A])(mf: M[(A) => B]): M[B] = bind(ma)((a: A) => bind(mf)(f => pure(f.apply(a))))

}
