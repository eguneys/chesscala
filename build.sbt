ThisBuild / scalaVersion := "3.0.2"

lazy val hello = (project in file("."))
  .settings(
    name := "Hello",
    libraryDependencies += "org.specs2" % "specs2-core_3" % "5.0.0-RC-11",
    resolvers += Resolver.sonatypeRepo("public")
    )
