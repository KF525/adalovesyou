import com.typesafe.config.ConfigFactory
import twitter4j._
import twitter4j.conf.ConfigurationBuilder


object Main extends App {

  println("I am in the Main")
  val twitterConfig = new TwitterConfig(ConfigFactory.load)
  println("have twitter config")

  val config = (new ConfigurationBuilder)
    .setDebugEnabled(true)
    .setOAuthConsumerKey(twitterConfig.consumerKey)
    .setOAuthConsumerSecret(twitterConfig.consumerSecret)
    .setOAuthAccessToken(twitterConfig.accessToken)
    .setOAuthAccessTokenSecret(twitterConfig.accessTokenSecret)
    .build()

  val twitter = new TwitterFactory(config).getInstance
  val stream = new TwitterStreamFactory(config).getInstance
  println("have stream factory")

  stream.addListener(new LovesYouListener(twitter))
  println("added listener")
  stream.user()
  println(".user()")

}


