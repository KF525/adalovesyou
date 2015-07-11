import com.typesafe.config.ConfigFactory
import twitter4j._
import twitter4j.conf.ConfigurationBuilder


object Main extends App {

  val twitterConfig = new TwitterConfig(ConfigFactory.load)

  val config = (new ConfigurationBuilder)
    .setDebugEnabled(true)
    .setOAuthConsumerKey(twitterConfig.consumerKey)
    .setOAuthConsumerSecret(twitterConfig.consumerSecret)
    .setOAuthAccessToken(twitterConfig.accessToken)
    .setOAuthAccessTokenSecret(twitterConfig.accessTokenSecret)
    .build()

  val twitter = new TwitterFactory(config).getInstance
  val stream = new TwitterStreamFactory(config).getInstance

  stream.addListener(new LovesYouListener(twitter))
  stream.user()
  println("Now listening to twitter...")

}


