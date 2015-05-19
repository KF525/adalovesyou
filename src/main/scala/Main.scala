import twitter4j._
import twitter4j.conf.ConfigurationBuilder
import com.typesafe.config.Config


object Main extends App {

  // val twitterConfig = new TwitterFactory(ConfigFactory.load)
  val config = (new ConfigurationBuilder)
    .setDebugEnabled(true)
    .setOAuthConsumerKey(sys.env("CONSUMER_KEY"))
    .setOAuthConsumerSecret(sys.env("CONSUMER_SECRET"))
    .setOAuthAccessToken(sys.env("ACCESS_TOKEN"))
    .setOAuthAccessTokenSecret(sys.env("ACCESS_TOKEN_SECRET"))
    .build()

  val twitter = new TwitterFactory(config).getInstance
  val stream = new TwitterStreamFactory(config).getInstance

  val statusListener = new StatusListener() {
    def onStatus(status: Status) {println(status.getText)}
    def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
    def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
    def onException(ex: Exception) { ex.printStackTrace() }
    def onScrubGeo(arg0: Long, arg1: Long) {}
    def onStallWarning(warning: StallWarning) {}
  }




//  class NotificationListener(
//                              userId: Long,
//                              screenName: String,
//                              responder: Responder,
//                              twitter: Twitter) extends UserStreamListener {
//
//    override def onFollow(source: User, followedUser: User): Unit = {
//
//    }
//
//    override def onUnfollow(source : User, unfollowedUser: User) = ()
//    override def onBlock(source: User, blockedUser: User) = ()
//    override def onDeletionNotice(directMessageId: Long, userId: Long) = ()
//    override def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) = ()
//    override def onDirectMessage(directMessage: DirectMessage) = ()
//    override def onException(ex: Exception) = ()
//    override def onFavorite(source: User, target: User, favoritedStatus: Status) = ()
//    override def onFriendList(friendIds: Array[Long]) = ()
//    override def onScrubGeo(userId: Long, upToStatusId: Long) = ()
//    override def onStallWarning(warning: StallWarning) = ()
//    override def onTrackLimitationNotice(numberOfLimitedStatuses: Int) = ()
//    override def onUnblock(source: User, unblockedUser: User) = ()
//    override def onUnfavorite(source: User, target: User, unfavoritedStatus: Status) = ()
//    override def onUserListCreation(listOwner: User, list: UserList) = ()
//    override def onUserListDeletion(listOwner: User, list: UserList) = ()
//    override def onUserListMemberAddition(addedMember: User, listOwner: User, list: UserList) = ()
//    override def onUserListMemberDeletion(deletedMember: User, listOwner: User, list: UserList) = ()
//    override def onUserListSubscription(subscriber: User, listOwner: User, list: UserList) = ()
//    override def onUserListUnsubscription(subscriber: User, listOwner: User, list: UserList) = ()
//    override def onUserListUpdate(listOwner: User, list: UserList) = ()
//    override def onUserProfileUpdate(updatedUser: User) = ()
//  }

  //val notificationListener = new NotifcationListener(3153362684L, "Ada", null, twitter)


  val getFollowers: IDs = twitter.getFollowersIDs(3153362684L, -1)
  val getFriends: IDs = twitter.getFriendsIDs(3153362684L, -1)
  val toFollowIDs: Set[Long] = getFollowers.getIDs.toSet.diff(getFriends.getIDs.toSet)
  val toUnFollowIDs: Set[Long] = getFriends.getIDs.toSet.diff(getFollowers.getIDs.toSet)

  val toFollow: Set[Long] = toFollowIDs
  toFollow.foreach { friend => twitter.createFriendship(friend) }

  val toUnfollow: Set[Long] = toUnFollowIDs
  toUnfollow.foreach { friend => twitter.destroyFriendship(friend) }

  stream.addListener(statusListener)
  stream.user()

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




