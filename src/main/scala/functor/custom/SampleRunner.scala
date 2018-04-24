package functor.custom


/**
  * Created by siddhant.srivastava on 4/24/18.
  */
object SampleRunner {

  def main(args: Array[String]): Unit = {
    println(runWith(Some(1).asInstanceOf[Maybe[Int]]))
    println(runWith(Cons(1, Cons(2, Cons(3, Nothing))).asInstanceOf[List[Int]]))
    val v: List[Maybe[String]] = Cons(Some("S"), Nothing)

    Functor.compose[List, Maybe].fmap(v)(_ + "S1")

  }

  def runWith[F[_]](a: F[Int])(implicit func: Functor[F]) = {
    func.fmap[Int, Int](a)(_ + 1)
  }



}
