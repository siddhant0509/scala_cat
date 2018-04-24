package yoneda

import coyoneda.CoYoneda
import functor.custom.Functor

/**
  * Created by siddhant.srivastava on 4/24/18.
  */
abstract class Yoneda[F[_], A] {
  self =>

    def transformation[B](f: A => B): F[B]

    def map[B](f: A => B): Yoneda[F, B] = new Yoneda[F, B] {
      override def transformation[C](g: (B) => C): F[C] = self.transformation(g.compose(f))
    }

    def run() = transformation(identity)

}


object Yoneda{

  def lift[F[_], A](yoneda: Yoneda[F,A]): CoYoneda[F,A] = new CoYoneda[F,A] {
    override type UnderLyingType = A
    override val underLyingValue: F[A] = yoneda.run()
    override val transformation: A => A = identity
  }

  def toYoneda[F[_], A](va: F[A])(implicit fa: Functor[F]) = new Yoneda[F, A] {
    override def transformation[B](f: (A) => B): F[B] = fa.fmap(va)(f)
  }

  def fromYoneda[F[_], A](yoneda: Yoneda[F,A]): F[A] = yoneda.run()


}







