package coyoneda

import functor.custom.Functor

/**
  * Created by siddhant.srivastava on 4/24/18.
  */

trait CoYoneda[F[_], A]{

  import CoYoneda.toCoYoneda

  type UnderLyingType

  val underLyingValue: F[UnderLyingType]

  val transformation: UnderLyingType => A

  def map[B](f: A => B): CoYoneda[F, B] = {
    toCoYoneda(underLyingValue)(f compose transformation)

  }

  def run(f: Functor[F]) = f.fmap(underLyingValue)(transformation)

}

object CoYoneda {

  def toCoYoneda[F[_], A, B](fa: F[A])(f: A => B): CoYoneda[F, B] = new CoYoneda[F, B] {
    override type UnderLyingType = A
    override val underLyingValue: F[A] = fa
    override val transformation: A => B = f
  }

}
