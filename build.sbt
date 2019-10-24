name := "QuickFile"

version := "0.1"

scalaVersion := "2.13.1"

assemblyJarName in assembly := "quickfile.jar"

mainClass in assembly := Some("edu.nyu.dlts.filetools.Main")

libraryDependencies ++= Seq(
  "org.apache.tika" % "tika-core" % "1.22",
  "org.apache.tika" % "tika-parsers" % "1.22",
  "commons-io" % "commons-io" % "2.6",
  "com.github.tototoshi" %% "scala-csv" % "1.3.6",
  "org.apache.pdfbox" % "jbig2-imageio" % "3.0.2"
)
