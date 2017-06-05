package extractors

/**
  * Created by muhammed on 5/6/17.
  */
trait User {
  def name: String
  def score: Int
}

 class FreeUser(val name: String,val score: Int,val prob: Double) extends User
 class PremiumUser(val name: String,val score:Int) extends User
 class RandomUser(val name:String,val score:Int,val prob:Double) extends  User

object  FreeUser{
  def apply(name: String,score:Int,prob:Double) = new FreeUser(name,score,prob)
  def unapply(user: FreeUser): Option[(String,Int,Double)] = Some((user.name,user.score,user.prob))
}

object  PremiumUser{
  def apply(name: String,score:Int) = new PremiumUser(name,score)
  def unapply(user: PremiumUser): Option[(String,Int)] = Some((user.name,user.score))
}

//Demonstrates the usage of Boolean extractor
object RandomUser{
  def apply(name: String,score:Int,prob:Double) = new RandomUser(name,score,prob)
  def unapply(user: RandomUser): Boolean = user.prob > 0.5
}

object Extractors extends  App{

  val freeUser:User = FreeUser("hunaif",21,.8)
  val premiumUser:User = PremiumUser("sekhar",20)
  val randomUser:User = RandomUser("random",21,0.6)

  randomUser match {
    case PremiumUser(name:String,_) => println(name)
    case FreeUser(name:String,_,prob:Double) => println(name + " " + prob)
    case RandomUser() => println("Yoo")
    case _ => println("something else")
  }
}
