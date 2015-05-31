import twitter4j.{Twitter, User, UserStreamAdapter}


class LovesYouListener(twitter: Twitter) extends UserStreamAdapter {


  override def onFollow(follower: User, followee: User) = {
    if (followee.getId == 3153362684L) {
      println("following " + follower.getScreenName)
      twitter.createFriendship(follower.getId)
    }
  }

  override def onException(ex: Exception) = ex.printStackTrace()
}
