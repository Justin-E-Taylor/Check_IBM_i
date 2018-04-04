/** Uses the iACS "ping" utility to check response from IBMi
  *
  * build using "sbt assembly" command from the root folder of the project (ALT-F12 from IntelliJ)
  * Resulting JAR file will be placed in ./target/scala-2.12
  *
  * run by changing to the target folder and "java -jar Check_IBM_i-assembly-0.1.jar"
  * (or specify the full path on the java command)
  */

import com.typesafe.config.ConfigFactory
// see https://gist.github.com/mariussoutier/3436111 for mail.scala
import mail._

import scala.collection.JavaConverters
import scala.language.postfixOps
import scala.sys.process._


object PerformCheck extends App {
  val check = new CheckMethod()
  check.RunCheck()
}


class CheckMethod {
  def RunCheck(): Unit = {
    val conf = ConfigFactory.load
    val iACS = conf.getString("conf.iACS")
    val ibm_system = conf.getString("conf.ibm_system")
    val services_to_test = conf.getString("conf.services_to_test")


    val result = iACS + " /plugin=ping /system=" + ibm_system + " /PORTS=" + services_to_test !!


    if (!SystemUp(result.split("\r\n").toList)) {
      println("System down")

      val senderAddress = conf.getString("conf.senderAddress")
      val senderName = conf.getString("conf.senderName")
      val lSubject = conf.getString("conf.subject")
      val lMessage = conf.getString("conf.message")
      val smtpHost = conf.getString("conf.smtpHost")
      val lSmtpPort = conf.getInt("conf.smtpPort")


      // Send notice via text msg.  21 Mar 18 JET
      val smsRecipients = conf.getStringList("conf.sms_recpients")
      if (!smsRecipients.isEmpty) {
        send a Mail(
          from = (senderAddress, senderName),
          to = JavaConverters.asScalaIteratorConverter(smsRecipients.iterator()).asScala.toSeq,
          subject = lSubject,
          message = lMessage + " See e-mail for details.",
          hostName = smtpHost,
          smtpPort = lSmtpPort
        )
      }


      // Send 2nd notice via e-mail, with full details.  21 Mar 18 JET
      val eMailRecipients = conf.getStringList("conf.eMail_recipients")
      if (!eMailRecipients.isEmpty) {
        send a Mail(
          from = (senderAddress, senderName),
          to = JavaConverters.asScalaIteratorConverter(eMailRecipients.iterator()).asScala.toSeq,
          subject = lSubject,
          message = lMessage + "\r\n" + "\r\n" + result,
          hostName = smtpHost,
          smtpPort = lSmtpPort
        )
      }

    }
  }

  @scala.annotation.tailrec
   final def SystemUp(line: List[String]): Boolean = {
    val hd :: tail = line

    if (hd.contains("Failed")) false
    else if (hd == "Done") true
    else SystemUp(tail)
  }

}