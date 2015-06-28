import twitter4j._
import scala.util.Random


class LovesYouListener(twitter: Twitter) extends UserStreamAdapter {

  //TODO: Add delta for cleaning up friends
  override def onFollow(follower: User, followee: User) = {
    if (followee.getId == 3153362684L) {
      System.out.println("following " + follower.getScreenName)
      twitter.createFriendship(follower.getId)
    }
  }

  override def onStatus(status: Status) = {
    System.out.println("status" + status)
    for (mention <- status.getUserMentionEntities) {
      lazy val relationship: Relationship = twitter.showFriendship(status.getUser.getScreenName, "_AdaLovesYou")
      if (mention.getId == 3153362684L && relationship.isTargetFollowedBySource) {
        val tweet = new StatusUpdate("@" + status.getUser.getScreenName + getRandomTweet)
        tweet.setInReplyToStatusId(status.getId)
        twitter.updateStatus(tweet)
      }
    }
  }

  def getRandomTweet: String = {
    val rnd = new Random
    Response.tweets(rnd.nextInt(Response.tweets.size))
  }

  override def onException(ex: Exception) = ex.printStackTrace()
}
