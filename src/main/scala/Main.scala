import com.typesafe.config.ConfigFactory
import twitter4j._
import twitter4j.conf.ConfigurationBuilder


object Main extends App {

    //curl --get 'https://userstream.twitter.com/1.1/user.json' --header 'Authorization: OAuth oauth_consumer_key="YcxG5hGeIbWERFnIjCK8perkf", oauth_nonce="e9f98ba5e414c83951f96394c3b91ed0", oauth_signature="DWJQAN%2F8VYgB09YQQfGpK83Pqyo%3D", oauth_signature_method="HMAC-SHA1",  oauth_timestamp="1433086224", oauth_token="3153362684-oYZw6vLkyrOblMIXgJqyB0lERhxYiFaLclAoNJK", oauth_version="1.0"' --verbose


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

  stream.addListener(new LovesYouListener(twitter))
  stream.user()

}


