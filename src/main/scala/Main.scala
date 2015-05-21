import twitter4j._
import twitter4j.conf.ConfigurationBuilder
import com.typesafe.config.{ConfigFactory, Config}


object Main extends App {

  val twitterConfig = new TwitterConfig(ConfigFactory.load)

  val config = (new ConfigurationBuilder)
    .setDebugEnabled(true)
    .setOAuthConsumerKey(twitterConfig.consumerKey)
    .setOAuthConsumerSecret(twitterConfig.consumerSecret)
    .setOAuthAccessToken(twitterConfig.accessToken)
    .setOAuthAccessTokenSecret(twitterConfig.accessTokenSecret)
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

  val userStreamListener = new UserStreamListener {
    def helloWorld() = {
      println("HIIIIIIIIIIIIiiiiiiiiiii")
    }
    println("Class is working")
    override def onFriendList(friendIds: Array[Long]): Unit = ()
    override def onUserListUnsubscription(subscriber: User, listOwner: User, list: UserList): Unit = ()
    override def onBlock(source: User, blockedUser: User): Unit = ()
    override def onUserListSubscription(subscriber: User, listOwner: User, list: UserList): Unit = ()
    override def onFollow(source: User, followedUser: User): Unit = (println("I'm following a person"))
    override def onUserListMemberAddition(addedMember: User, listOwner: User, list: UserList): Unit = ???
    override def onDirectMessage(directMessage: DirectMessage): Unit = ???
    override def onUnblock(source: User, unblockedUser: User): Unit = ???
    override def onUserListUpdate(listOwner: User, list: UserList): Unit = ???
    override def onUnfollow(source: User, unfollowedUser: User): Unit = ???
    override def onUserProfileUpdate(updatedUser: User): Unit = ???
    override def onUserListMemberDeletion(deletedMember: User, listOwner: User, list: UserList): Unit = ???
    override def onUserDeletion(deletedUser: Long): Unit = ???
    override def onDeletionNotice(directMessageId: Long, userId: Long): Unit = ???
    override def onFavorite(source: User, target: User, favoritedStatus: Status): Unit = ???
    override def onUnfavorite(source: User, target: User, unfavoritedStatus: Status): Unit = ???
    override def onUserSuspension(suspendedUser: Long): Unit = ???
    override def onUserListDeletion(listOwner: User, list: UserList): Unit = ???
    override def onUserListCreation(listOwner: User, list: UserList): Unit = ???
    override def onStallWarning(warning: StallWarning): Unit = ???
    override def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice): Unit = ???
    override def onScrubGeo(userId: Long, upToStatusId: Long): Unit = ???
    override def onStatus(status: Status): Unit = ???
    override def onTrackLimitationNotice(numberOfLimitedStatuses: Int): Unit = ???
    override def onException(ex: Exception): Unit = ()
  }

  val getFollowers: IDs = twitter.getFollowersIDs(3153362684L, -1)
  val getFriends: IDs = twitter.getFriendsIDs(3153362684L, -1)
  val toFollowIDs: Set[Long] = getFollowers.getIDs.toSet.diff(getFriends.getIDs.toSet)
  val toUnFollowIDs: Set[Long] = getFriends.getIDs.toSet.diff(getFollowers.getIDs.toSet)

  val toFollow: Set[Long] = toFollowIDs
  toFollow.foreach { friend => twitter.createFriendship(friend) }

  val toUnfollow: Set[Long] = toUnFollowIDs
  toUnfollow.foreach { friend => twitter.destroyFriendship(friend) }

  stream.addListener(userStreamListener)
  //stream.user()
  Thread.sleep(6000)
  stream.cleanUp()
  stream.shutdown()

  //  private val timeline: ResponseList[Status] = twitter.getHomeTimeline
  //  private val myMentions = twitter.getMentionsTimeline
  //  twitter.updateStatus("Ada loves you!")
  //  Console.println("Hello World: " + timeline.toString + myMentions.toString )

}


//val status: ResponseList[Status] = twitter.getMentionsTimeline




  //TO DO:
  //Create a usernotification listener which listens to new follow events.
  //listen to twitter (all of twitter or just account's followers/or people who follow account?)
  //listen for specific phrases or hashtag (what should this/these be)
  //respond to users who use those specific phrases/hashtag
  //try to specify type of tweet to send out??
  //create a collection of tweets to select from and tweet to user




