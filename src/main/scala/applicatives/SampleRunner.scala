package applicatives

/**
  * Created by siddhant.srivastava on 4/24/18.
  */
object SampleRunner {

  def main(args: Array[String]) {
    println(runWith(Some("You")))
    println(sum(Some(1).asInstanceOf[Maybe[Int]], Some(2).asInstanceOf[Maybe[Int]]))
  }


  val add: (Int, Int) => Int = _ + _

  def runWith(va: Maybe[String])(implicit fa: Applicative[Maybe]) = {
    fa.ap(va)(Some((a: String) => a + " bad"))
  }

  def sum[F[_]](va:F[Int], vb: F[Int])(implicit fm: Applicative[F]): F[Int] = fm.ap2(add)(va, vb)

}
