package edu.nyu.dlts.filetools

import java.io.File

import com.github.tototoshi.csv.CSVWriter
import org.apache.commons.io.FilenameUtils
import org.apache.tika.Tika

object Main extends App {
  val tika = new Tika()
  case class ComputerFile(filename: String, ext: String, path: String, size: Long, mime: Option[String])
  def fsRoot = new File(args(0))
  val outputFile = new File(args(1))
  val writer = CSVWriter.open(outputFile)

  iterateFs(fsRoot)

  def iterateFs(file: File): Unit = {
    file.listFiles().foreach { child =>
      if(child.isFile() && child.isHidden) { println(s"Hidden file ${child.getName}, skipping")}
      else if(child.isFile && !child.isHidden) {
        val filename= child.getName
        val name = FilenameUtils.getBaseName(filename)
        val ext = FilenameUtils.getExtension(filename)
        val path = child.getPath
        val size = child.length()
        val mime = getMime(child)
        val computerFile = new ComputerFile(name, ext, path, size, mime)
        writer.writeRow(cfToList(computerFile))
      }

      else if(child.isDirectory) iterateFs(child)
      else println("Here Be Weirdness" + child.getPath)
    }
  }

  def cfToList(computerFile: ComputerFile): List[String] = {
    List(s"'${computerFile.filename}'", s"'${computerFile.ext}'", s"'${computerFile.path}'", s"'${computerFile.size.toString}'", s"'${computerFile.mime.getOrElse("NA")}'")
  }

  def getMime(file: File): Option[String] = { Some(tika.detect(file)) }
}



