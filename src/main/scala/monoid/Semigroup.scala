package monoid

/**
  * Created by siddhant.srivastava on 4/24/18.
  */
trait Semigroup[A] {

  def op(f: A, s: A): A

}
