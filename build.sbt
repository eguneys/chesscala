ThisBuild / scalaVersion := "2.13.8"

name := "Hello"

libraryDependencies ++= List(
    "org.specs2" % "specs2-core_2.13" % "4.13.2" % Test,
    "org.specs2" %% "specs2-cats" % "4.13.2" % Test,
    "org.typelevel" %% "cats-core" % "2.7.0"
)


scalacOptions ++= Seq(
  "-explaintypes",
  "-language:implicitConversions",
  "-language:postfixOps"
) 
