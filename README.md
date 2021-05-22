# Virtual Terminal
A Java Program

# Author
MrShiehX </br>
Email Address: bntoylort@outlook.com

# Start Arguments
`-user <user name> -root <root path>`

# Commands
ls: List files </br>
exit: Exit </br>
echo: Echo a text </br>
cd: Change current directory </br>
su: Switch user </br>
co: Create an object </br>
cf: Create a file </br>
mkdir: Create a directory </br>
mkdirs: Create directories </br>
do: Delete an object </br>
df: Delete a file </br>
cp: Copy a file </br>
rn: Rename a file </br>
mv: Move a file </br>

# Make Your Executable Commands
Go to https://github.com/MrShieh-X/virtual-terminal/releases to download the corresponding version of the JAR file, import it into your project as a library file, and create the following function in the command source file:
```java
public void main(String[]args){
     // do something...
}

public void usage(String command){
     System.out.println("my command usage...");
}
```
Compile this file, export it, put it in the `data/classes` directory under the program directory, then modify the file name of the main class file, delete its `.class` suffix. </br>
If you are using JetBrains IDEA, after compiling the file, you can click `Build -> Recompile'xxxxx.java'` to compile your file, and go to the project directory to find the class file in `build/classes/java` .