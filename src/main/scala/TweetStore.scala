import twitter4j._

import scala.util.Random

class TweetStore extends StatusAdapter {
  var myCount = 0
  var myWords: Array[String] = Array("")
  val rand = new Random(System.currentTimeMillis())

  def generateTweet() = {
    var result = ""
    for( x <- 0 to 10 ){
      val random_index = rand.nextInt(myWords.length)
      result += myWords(random_index) + " "
    }
    result
  }
  
  override def onStatus(status: Status) = {
    myCount += 1
    myWords = myWords ++ status.getText.split(" ").filterNot(x => x.contains("@"))
    if(myCount % 100 == 0) {
      println(generateTweet())
    }
  }
}
