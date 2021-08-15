1. Install Java
2. Install Maven
3. Make sure that pom.xml contains your version of java in this section:
   <properties>
   <maven.compiler.source>15</maven.compiler.source>
   <maven.compiler.target>15</maven.compiler.target>
   
4. Clone this git repository
5. Open the directory with the repository in the terminal
6. execute command "mvn test"

Test should run

There are a few problems that I could not resolve.
a) The tests run correctly for me from IntelliJ,
but they fail sometimes if I try to run them from terminal.
b) The tests open an additional empty Chrome window,
I could not find the reason for this.

Anyway, I did not have time to investigate these issues,
I hope it is enough.