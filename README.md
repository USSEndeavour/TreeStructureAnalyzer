# TreeStructureAnalyzer

TreeStructureAnalyzer is a Java application designed to analyze and document the structure of a directory. It provides valuable insights into the contents of a directory, including information about individual files, subdirectories, and their last modification timestamps.

## Features

- **Directory Structure Analysis**: Obtain a detailed breakdown of each file and subdirectory within the specified directory.
- **Last Modified Timestamps**: View the last modified timestamps of individual files for time-sensitive analysis.
- **Total Files and Folders**: Get a comprehensive summary of the total number of files and subdirectories in the analyzed directory.

## TreeStructureAnalyzer Structure

The project is structured as follows:

- **`TreeStructureAnalyzer/src/`**: Contains the Java source code for the TreeStructureAnalyzer application.
    - `TreeStructureAnalyzer.java`: The main Java class containing the application logic.
    
- **`TreeStructureAnalyzer/bin/`**:
    - `TreeStructureAnalyzer.class`: the compiled Java class file.
    - `data/`: The default directory where the analysis report (`walkdir.txt`) is saved.

## Usage

### Prerequisites

- Java Development Kit (JDK) installed.

### Running the Application

1. Open a terminal or command prompt.
2. Navigate to the directory containing the compiled Java class files.
3.  - Run the following command to analyze and document the structure of a directory and save the results to the `data/walkdir.txt`:

   ```bash
   java TreeStructureAnalyzer </Full/path_to_directory> 
   ```
   - Run the following command to print the `data/walkdir.txt` to the console:
    ```bash
    java TreeStructureAnalyzer </Full/path_to_walkdir.txt> 
    ```