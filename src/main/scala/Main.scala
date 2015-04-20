import sun.security.krb5.Credentials
import twitter4j.auth.AccessToken
import twitter4j._
import twitter4j.conf.ConfigurationBuilder

object Main extends App {
  val config = (new ConfigurationBuilder)
    .setDebugEnabled(true)
    .setOAuthConsumerKey(sys.env("CONSUMER_KEY"))
    .setOAuthConsumerSecret(sys.env("CONSUMER_SECRET"))
    .setOAuthAccessToken(sys.env("ACCESS_TOKEN"))
    .setOAuthAccessTokenSecret(sys.env("ACCESS_TOKEN_SECRET"))
    .build()

  val twitter = new TwitterFactory(config).getInstance
  val stream = new TwitterStreamFactory(config).getInstance()

//  private val timeline: ResponseList[Status] = twitter.getHomeTimeline
//  private val myMentions = twitter.getMentionsTimeline
//  twitter.updateStatus("Ada loves you!")
//  Console.println("Hello World: " + timeline.toString + myMentions.toString )

  val listener = new StatusListener() {
    def onStatus(status: Status) {println(status.getText)}
    def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
    def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
    def onException(ex: Exception) {ex.printStackTrace}
    def onScrubGeo(arg0: Long, arg1: Long) {}
    def onStallWarning(warning: StallWarning) {}
  }

  Console.println(twitter.getFriendsIDs(3153362684L).toString())

//  stream.addListener(listener)
//  stream.user()
//  Thread.sleep(6000)
//  stream.cleanUp
//  stream.shutdown


}


//val status: ResponseList[Status] = twitter.getMentionsTimeline




  //TO DO:
  //create a twitter account
  //get credentials and add to .env file
  //get our list of followers
  //listen to twitter (all of twitter or just account's followers/or people who follow account?)
  //listen for specific phrases or hashtag (what should this/these be)
  //respond to users who use those specific phrases/hashtag
  //try to specify type of tweet to send out??
  //create a collection of tweets to select from and tweet to user




