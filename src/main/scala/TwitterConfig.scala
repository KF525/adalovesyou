import com.typesafe.config.Config

class TwitterConfig(config: Config) {
  val consumerKey = scala.util.Properties.envOrElse("CONSUMER_KEY", config.getString("consumerKey"))
  val consumerSecret = scala.util.Properties.envOrElse("CONSUMER_SECRET", config.getString("consumerSecret"))
  val accessToken = scala.util.Properties.envOrElse("ACCESS_TOKEN", config.getString("accessToken"))
  val accessTokenSecret = scala.util.Properties.envOrElse("ACCESS_TOKEN_SECRET", config.getString("accessTokenSecret"))
  val databaseUrl = scala.util.Properties.envOrElse("DATABASE_URL", config.getString("databaseUrl"))
}
