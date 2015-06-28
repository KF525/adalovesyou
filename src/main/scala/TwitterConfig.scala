import com.typesafe.config.Config

class TwitterConfig(config: Config) {
<<<<<<< HEAD
  val consumerKey = sys.env("CONSUMER_KEY") //config.getString("consumerKey")
  val consumerSecret = sys.env("CONSUMER_SECRET") //config.getString("consumerSecret")
  val accessToken = sys.env("ACCESS_TOKEN") //config.getString("accessToken")
  val accessTokenSecret = sys.env("ACCESS_TOKEN_SECRET") //config.getString("accessTokenSecret")
=======
  val consumerKey = System.getenv("CONSUMER_KEY") //config.getString("consumerKey")
  val consumerSecret = System.getenv("CONSUMER_SECRET") //config.getString("consumerSecret")
  val accessToken = System.getenv("ACCESS_TOKEN") //config.getString("accessToken")
  val accessTokenSecret = System.getenv("ACCESS_TOKEN_SECRET") //config.getString("accessTokenSecret")
>>>>>>> 8775204d486e9c4b51a521ae8ee2969a90bfd048

}
