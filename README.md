# Check_IBM_i
Check IBM i service availability

This tool uses a utility in IBM i Access Client Solutions to check available services on an IBM POWER server running IBM i.  The purpose being to monitor for system outages.  It compiles to a JAR file to execute on the Java JVM.  Notice of unavailable service(s) is made via e-mail and/or text message.

The application.conf file can be used to customize this tool for your environment.

The ../target/scala-2.12 contains an executable JAR file.  To use this JAR, open the JAR with your favorite ZIP utility.  Edit the application.conf file with your settings and save it back to the JAR.  Run by changing to the target folder and "java -jar Check_IBM_i-assembly-0.1.jar" (or specify the full path on the java command).
