package monoid

/**
  * Created by siddhant.srivastava on 4/24/18.
  */
trait Group[A] extends Monoid[A]{
  def inv(v: A): A
}

object Group{

  implicit val intGroup = new Group[Int] {
    override def inv(v: Int): Int = -v
    override def zero: Int = 0
    override def op(f: Int, s: Int): Int = f + s
  }

}
