package monoid

/**
  * Created by siddhant.srivastava on 4/24/18.
  */
trait Monoid[A] extends Semigroup[A]{

  def zero: A

}



object Monoid{

  def Monoids[A](implicit mon: Monoid[A]) = mon

  implicit val intSum: Monoid[Int] = new Monoid[Int] {
    override def op(f: Int, s: Int): Int = f + s
    override def zero: Int = 0
  }

  implicit def listMonoid[A] = new Monoid[List[A]] {
    override def op(f: List[A], s: List[A]): List[A] = f ++ s
    override def zero: List[A] = Nil
  }

  implicit def optionMonoid[A] = new Monoid[Option[A]] {
    override def op(f: Option[A], s: Option[A]): Option[A] = f.orElse(s)
    override def zero: Option[A] = None
  }

  trait MonoidalReducer[F[_]]{
    def foldMap[A](fa: F[A])(implicit ma: Monoid[A]): A
  }

  implicit val listMonoidalReducer = new MonoidalReducer[List] {
    override def foldMap[A](fa: List[A])(implicit ma: Monoid[A]): A = fa.foldRight(ma.zero)(ma.op)
  }
  

}
