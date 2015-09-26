import com.typesafe.config.ConfigFactory
import slick.driver.PostgresDriver.api._
import twitter4j._
import twitter4j.conf.ConfigurationBuilder

class Users(tag: Tag) extends Table[(Int, String)](tag, "users") {
  def id = column[Int]("id")
  def username = column[String]("username")
  def * = (id, username)
}
val users = TableQuery[Users]


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

  stream.addListener(new AdaListener(twitter))
  stream.user()
  println("Now listening to twitter...")

  val db = Database.forURL(twitterConfig.databaseUrl)
  val setupAction: DBIO[Unit] = DBIO.seq(
    // Create the schema by combining the DDLs for the Suppliers and Coffees
    // tables using the query interfaces
    users.

//    // Insert some suppliers
//    suppliers += (101, "Acme, Inc.", "99 Market Street", "Groundsville", "CA", "95199"),
//    suppliers += ( 49, "Superior Coffee", "1 Party Place", "Mendocino", "CA", "95460"),
//    suppliers += (150, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966")
  )
  db.run(setupAction)
//  db.run(
//
//  )

//  db.run(users.result).map(_.foreach {
//    case (name, supID, price, sales, total) =>
//      println("  " + name + "\t" + supID + "\t" + price + "\t" + sales + "\t" + total)
//  })
//  val query = sql"select ID, FIRST, LAST, AGE from PERSON".as[(Int,String,String,Int)]
//  val people = db.withSession{ implicit session =>
//    query
//  }

//  val a: (dbio.DBIOAction[Nothing, NoStream, Nothing]) => Future[Nothing] = db.run
//    _.foreach {
//    case (name, supID, price, sales, total) =>
//      println("  " + name + "\t" + supID + "\t" + price + "\t" + sales + "\t" + total)
//  })
//  db withSession {
//    implicit session =>
//      val users = TableQuery[Users]
//
//      // SELECT * FROM users
//      users.flatMap { row =>
//        println("user with id " + row._1 + " has username " + row._2)
//      }
//
//      // SELECT * FROM users WHERE username='john'
//      users.filter(_.username === "john").list foreach { row =>
//        println("user whose username is 'john' has id "+row._1 )
//      }
//  }
}


// Natural Language APIs http://blog.mashape.com/list-of-25-natural-language-processing-apis/