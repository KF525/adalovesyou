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

  val twitter: Twitter = new TwitterFactory(config).getInstance
  val stream: TwitterStream = new TwitterStreamFactory(config).getInstance

  stream.addListener(new LovesYouListener)
  stream.sample()
}


