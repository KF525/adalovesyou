import com.typesafe.config.Config

class TwitterConfig(config: Config) {
  val consumerKey = sys.env("CONSUMER_KEY") //config.getString("consumerKey")
  val consumerSecret = sys.env("CONSUMER_SECRET") //config.getString("consumerSecret")
  val accessToken = sys.env("ACCESS_TOKEN") //config.getString("accessToken")
  val accessTokenSecret = sys.env("ACCESS_TOKEN_SECRET") //config.getString("accessTokenSecret")

}
