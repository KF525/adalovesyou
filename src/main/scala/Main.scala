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
  val userStream = new TwitterStreamFactory(config).getInstance
  val statusStream = new TwitterStreamFactory(config).getInstance
  val searchTerms: Array[String] = Array("wow")
  val query = new FilterQuery language Array("en") track searchTerms
  val store = new TweetStore 
  statusStream.addListener(store)
  statusStream.filter(query)
  println("Now listening for statuses...")

  userStream.addListener(new AdaListener(twitter))
  userStream.user()
  println("Now listening to twitter...")
}


// Natural Language APIs http://blog.mashape.com/list-of-25-natural-language-processing-apis/