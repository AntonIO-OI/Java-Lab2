# Lab 2
### Author: Anton Bur, Group IP-22, Variant #3

---

### Variant Calculations
| Parameter | Formula  | Result |  
|-----------|----------|--------|  
| **C3**    | `3 % 3`  | 0      |  
| **C17**   | `3 % 17` | 3      |  

---

### Task Variants
| Step | Description                                           | Details                                                                                   |  
|------|-------------------------------------------------------|-------------------------------------------------------------------------------------------|  
| **2** | Determine the type of text variables based on **C3**: | `StringBuilder`                                                                           |  
| **3** | Determine the action to perform on the text based on **C17**: | Find and print all unique words of a given length from all interrogative sentences in the text. |  

---

### How to Run the Project

#### Prerequisites
Ensure [Maven](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html) is [installed](https://maven.apache.org/download.cgi) on your computer.

#### Steps to Execute
1. Clone the repository:
   ```bash  
   git clone https://github.com/AntonIO-OI/Java-Lab2
   cd Java-Lab2
   ```  

2. Build the project (this will also run unit tests):
   ```bash  
   mvn package  
   ```  

3. Run the project:
   ```bash  
   mvn clean compile exec:java  
   ```  

4. Run unit tests:
   ```bash  
   mvn test  
   ```  
