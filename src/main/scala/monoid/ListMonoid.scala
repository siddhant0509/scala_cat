package monoid

/**
  * Created by siddhant.srivastava on 4/24/18.
  */
object ListMonoid {

  implicit class ListFoldMap[A](li: List[A])(implicit ma: Monoid[A]){
    def foldMap = li.foldRight(ma.zero)(ma.op)
  }


}
