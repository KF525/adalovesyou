import com.typesafe.config.Config

class TwitterConfig(config: Config) {
  val consumerKey = config.getString("consumerKey")
  val consumerSecret = config.getString("consumerSecret")
  val accessToken = config.getString("accessToken")
  val accessTokenSecret = config.getString("accessTokenSecret")

}
