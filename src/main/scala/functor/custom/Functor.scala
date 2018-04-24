package functor.custom

/**
  * Created by siddhant.srivastava on 4/23/18.
  */
trait Functor[F[_]] {

  def fmap[A,B](fa: F[A])(f: A => B): F[B]

  def lift[A, B](f: A => B): (F[A] => F[B]) = fa => fmap(fa)(f)

}

object Functor{

  import Maybe.optionFunctor
  import List.listFunctor

  def Functor[A](implicit fa: Functor[A]) = fa

  def compose[M[_], N[_]](implicit mx: Functor[M], nx: Functor[N]): Functor[({type L[A] = M[N[A]]})#L] = new Functor[({type L[A] = M[N[A]]})#L] {
    override def fmap[A, B](fa: M[N[A]])(f: (A) => B): M[N[B]] = mx.fmap(fa)(na => nx.fmap(na)(f))
  }


}
