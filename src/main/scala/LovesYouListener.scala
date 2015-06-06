import twitter4j._
import scala.util.Random


class LovesYouListener(twitter: Twitter) extends UserStreamAdapter {

  override def onFollow(follower: User, followee: User) = {
    if (followee.getId == 3153362684L) {
      println("following " + follower.getScreenName)
      twitter.createFriendship(follower.getId)
    }
  }

  override def onStatus(status: Status) = {
    for (mention <- status.getUserMentionEntities) {
      if (mention.getId == 3153362684L) { //.existsFriendship(status.getUser.getScreenName, mention.getScreenName)) {
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
