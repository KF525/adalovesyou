import com.typesafe.config.Config

class TwitterConfig(config: Config) {
  val consumerKey = System.getenv("CONSUMER_KEY") //config.getString("consumerKey")
  val consumerSecret = System.getenv("CONSUMER_SECRET") //config.getString("consumerSecret")
  val accessToken = System.getenv("ACCESS_TOKEN") //config.getString("accessToken")
  val accessTokenSecret = System.getenv("ACCESS_TOKEN_SECRET") //config.getString("accessTokenSecret")

}
