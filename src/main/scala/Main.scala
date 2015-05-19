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
  val stream = new TwitterStreamFactory(config).getInstance

  val listener = new StatusListener() {
    def onStatus(status: Status) {println(status.getText)}
    def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
    def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
    def onException(ex: Exception) { ex.printStackTrace() }
    def onScrubGeo(arg0: Long, arg1: Long) {}
    def onStallWarning(warning: StallWarning) {}
  }
  
  // gather an array of all followers' IDs of ScalaBot
  //Console.println(twitter.getFollowersIDs(3153362684L, -1).toString())

  // gather an array of all followers general info 
  //Console.println(twitter.getFollowersList(3153362684L, -1).toString())

  //go through friends and push things into toFollow/toUnfollow sequences appropriately
  //sequences that are empty are not broken


  val getFollowers: IDs = twitter.getFollowersIDs(3153362684L, -1)
  val getFriends: IDs = twitter.getFriendsIDs(3153362684L, -1)
  val toFollowIDs: Set[Long] = getFollowers.getIDs.toSet.diff(getFriends.getIDs.toSet)
  val toUnFollowIDs: Set[Long] = getFriends.getIDs.toSet.diff(getFollowers.getIDs.toSet)

  val toFollow: Set[Long] = toFollowIDs
  toFollow.foreach { friend => twitter.createFriendship(friend) }

  val toUnfollow: Set[Long] = toUnFollowIDs
  toUnfollow.foreach { friend => twitter.destroyFriendship(friend) }

//  stream.addListener(listener)
//  stream.user()
//  Thread.sleep(6000)
//  stream.cleanUp()
//  stream.shutdown()

  //  private val timeline: ResponseList[Status] = twitter.getHomeTimeline
  //  private val myMentions = twitter.getMentionsTimeline
  //  twitter.updateStatus("Ada loves you!")
  //  Console.println("Hello World: " + timeline.toString + myMentions.toString )

}


//val status: ResponseList[Status] = twitter.getMentionsTimeline




  //TO DO:
  //listen to twitter (all of twitter or just account's followers/or people who follow account?)
  //listen for specific phrases or hashtag (what should this/these be)
  //respond to users who use those specific phrases/hashtag
  //try to specify type of tweet to send out??
  //create a collection of tweets to select from and tweet to user




