ThisBuild / scalaVersion := "3.0.2"

name := "Hello"

libraryDependencies += "org.specs2" % "specs2-core_3" % "5.0.0-RC-11"

scalacOptions ++= Seq(
  "-language:implicitConversions"
) 
