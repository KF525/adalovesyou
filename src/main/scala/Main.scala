import twitter4j.auth.AccessToken
import twitter4j.{Status, ResponseList, TwitterFactory, Twitter}

object Main extends App {
  val twitter: Twitter = new TwitterFactory().getInstance()
  twitter.setOAuthConsumer(sys.env("CONSUMER_KEY"),
    sys.env("CONSUMER_SECRET"))
  twitter.setOAuthAccessToken(new AccessToken(sys.env("ACCESS_TOKEN"),
    sys.env("ACCESS_TOKEN_SECRET")))
  private val timeline: ResponseList[Status] = twitter.getHomeTimeline()
  twitter.updateStatus("#tweetingfromscala")


  Console.println("Hello World: " + timeline.toString)



  //TO DO:
  //create a twitter account
  //get credentials and add to .env file
  //listen to twitter (all of twitter or just account's followers/or people who follow account?)
  //listen for specific phrases or hashtag (what should this/these be)
  //respond to users who use those specific phrases/hashtag
  //try to specify type of tweet to send out??
  //create a collection of tweets to select from and tweet to user
}