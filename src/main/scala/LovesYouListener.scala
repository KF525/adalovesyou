import twitter4j.{User, UserStreamAdapter}


class LovesYouListener extends UserStreamAdapter {
  override def onFollow(source: User, followedUser: User) = {
    if (followedUser.getId == 3153362684L) {
      println(source.toString)
    }
  }
}
