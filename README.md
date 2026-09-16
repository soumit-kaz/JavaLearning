# JavaLearning

Learn Java step by step. Every module has small **lessons** and a set of **problems** with solutions.

## Modules

| # | Module | Problems |
|---|--------|----------|
| 01 | [Hello World](01_HelloWorld) | [list](01_HelloWorld/problems/PROBLEMS.md) |
| 02 | [Data Types](02_DataTypes) | [list](02_DataTypes/problems/PROBLEMS.md) |
| 03 | [Operators](03_Operators) | [list](03_Operators/problems/PROBLEMS.md) |
| 04 | [Conditions (if / switch)](04_Conditions) | [list](04_Conditions/problems/PROBLEMS.md) |
| 05 | [Loops](05_Loops) | [list](05_Loops/problems/PROBLEMS.md) |
| 06 | [Arrays](06_Arrays) | [list](06_Arrays/problems/PROBLEMS.md) |
| 07 | [Multi-Dimensional Arrays](07_MultiDimensionalArrays) | [list](07_MultiDimensionalArrays/problems/PROBLEMS.md) |
| 08 | [Strings](08_Strings) | [list](08_Strings/problems/PROBLEMS.md) |
| 09 | [Static](09_Static) | [list](09_Static/problems/PROBLEMS.md) |
| 10 | [Functions](10_Functions) | [list](10_Functions/problems/PROBLEMS.md) |
| 11 | [OOP](11_OOP) | [list](11_OOP/problems/PROBLEMS.md) |
| 12 | [Overloading](12_Overloading) | [list](12_Overloading/problems/PROBLEMS.md) |
| 13 | [Overriding](13_Overriding) | [list](13_Overriding/problems/PROBLEMS.md) |
| 14 | [Compile-time & Runtime Binding](14_Binding) | [list](14_Binding/problems/PROBLEMS.md) |
| 15 | [Abstraction](15_Abstraction) | [list](15_Abstraction/problems/PROBLEMS.md) |
| 16 | [Interfaces](16_Interfaces) | [list](16_Interfaces/problems/PROBLEMS.md) |
| 17 | [Collections (Java's STL)](17_Collections) | [list](17_Collections/problems/PROBLEMS.md) |
| 18 | [Data Structures](18_DataStructures) | [list](18_DataStructures/PROBLEMS.md) |

Each module folder looks like this:

```
02_DataTypes/
  README.md                  lessons list + key points
  M02L01_Variables.java      lesson 1
  M02L02_....java            lesson 2 ...
  problems/
    PROBLEMS.md              all problems of this module
    P01_SwapVariables/
      M02P01_SwapVariables.java   solution
```

**How to study:** read a lesson, guess the output, then run it. Then open `PROBLEMS.md`, solve each problem yourself, and compare your answer with the solution.

## Install

You need **Java (JDK) 25** and **VS Code**.

### Windows

```powershell
winget install --id EclipseAdoptium.Temurin.25.JDK -e
winget install --id Microsoft.VisualStudioCode -e
```

Or download the JDK from <https://adoptium.net>. During setup, turn on **Set JAVA_HOME**.

### macOS

```bash
brew install --cask temurin@25
brew install --cask visual-studio-code
```

### Linux (Ubuntu / Debian)

```bash
sudo apt install -y wget gpg
wget -qO - https://packages.adoptium.net/artifactory/api/gpg/key/public | gpg --dearmor | sudo tee /etc/apt/trusted.gpg.d/adoptium.gpg > /dev/null
echo "deb https://packages.adoptium.net/artifactory/deb $(. /etc/os-release; echo $VERSION_CODENAME) main" | sudo tee /etc/apt/sources.list.d/adoptium.list
sudo apt update && sudo apt install -y temurin-25-jdk
sudo snap install code --classic
```

On Fedora, use `sudo dnf install java-25-openjdk-devel` instead.

### Check the install

Open a **new** terminal and run:

```bash
java -version
javac -version
```

Both should show version 25.

## VS Code

1. Open this folder in VS Code.
2. Install **Extension Pack for Java** (VS Code suggests it automatically).
3. Open any `.java` file and click **Run** above `main`.

If VS Code uses the wrong Java version, open **Ctrl+Shift+P**, choose **Java: Configure Java Runtime**, and pick 25.

## Run from the terminal

Run one file:

```bash
cd 01_HelloWorld
java M01L01_HelloWorld.java
```

Run everything and check that all problem tests pass:

```bash
./scripts/run-all.sh             # macOS / Linux / Git Bash
.\scripts\run-all.ps1            # Windows PowerShell
```
