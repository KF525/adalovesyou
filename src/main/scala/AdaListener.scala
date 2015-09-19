import twitter4j._
import scala.util.Random


class AdaListener(twitter: Twitter) extends UserStreamAdapter {

  override def onFollow(follower: User, followee: User) = {
    if (followee.getId == 3153362684L) {
      twitter.createFriendship(follower.getId)
    }
    println("following " + follower.getScreenName)
  }

  override def onStatus(status: Status) = {
    for (mention <- status.getUserMentionEntities) {
      lazy val relationship: Relationship = twitter.showFriendship(status.getUser.getScreenName, "_AdaLovesYou")
      if (mention.getId == 3153362684L && relationship.isTargetFollowedBySource) {
        val tweet = new StatusUpdate("@" + status.getUser.getScreenName + ' ' + getRandomTweet)
        tweet.setInReplyToStatusId(status.getId)
        twitter.updateStatus(tweet)
      }
    }
    println("status id:" + status.getId + " status text:" + status.getText + " user mention entities:" + status.getUserMentionEntities)
  }

  def getRandomTweet: String = {
    val rnd = new Random
    Response.tweets(rnd.nextInt(Response.tweets.size))
  }

  override def onException(ex: Exception) = ex.printStackTrace()
}
