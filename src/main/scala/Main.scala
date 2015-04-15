import sun.security.krb5.Credentials
import twitter4j.auth.AccessToken
import twitter4j._
import twitter4j.conf.ConfigurationBuilder

object Main extends App {

  val twitter: Twitter = new TwitterFactory().getInstance()
  twitter.setOAuthConsumer(sys.env("CONSUMER_KEY"),
    sys.env("CONSUMER_SECRET"))
  twitter.setOAuthAccessToken(new AccessToken(sys.env("ACCESS_TOKEN"),
    sys.env("ACCESS_TOKEN_SECRET")))
  val status: ResponseList[Status] = twitter.getMentionsTimeline();
  //private val timeline: ResponseList[Status] = twitter.getHomeTimeline()
  //twitter.updateStatus("#NotABot")

  Console.println("Hello World: " + status.toString)

  def listener = new StatusListener() {
    def onStatus(status: Status) {println(status.getText)}
    def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
    def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
    def onException(ex: Exception) {ex.printStackTrace}
    def onScrubGeo(arg0: Long, arg1: Long) {}
    def onStallWarning(warning: StallWarning) {}
  }

  val twitterStream = new TwitterStreamFactory().getInstance()
  twitterStream.setOAuthConsumer(sys.env("CONSUMER_KEY"),
    sys.env("CONSUMER_SECRET"))
  twitterStream.setOAuthAccessToken(new AccessToken(sys.env("ACCESS_TOKEN"),
    sys.env("ACCESS_TOKEN_SECRET")))
  twitterStream.addListener(listener)
  twitterStream.user()
  Thread.sleep(6000)
  twitterStream.cleanUp
  twitterStream.shutdown
}







  //TO DO:
  //create a twitter account
  //get credentials and add to .env file
  //listen to twitter (all of twitter or just account's followers/or people who follow account?)
  //listen for specific phrases or hashtag (what should this/these be)
  //respond to users who use those specific phrases/hashtag
  //try to specify type of tweet to send out??
  //create a collection of tweets to select from and tweet to user




