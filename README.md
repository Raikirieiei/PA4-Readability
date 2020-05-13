# Flesch Index Readability 
    By Thornthep Chomchuen
    
# Instruction
    Flesch Index Readability is the application that can read text file from directory and URL,
    Then the application will counting the words,syllables and sentences in those text file, After that 
    this application will caculate Flesch Index using this formula.
                        
                        (206.835 - 84.6*(syllables/words) - 1.015*(words/sentences)); 
    
    Finally the application will calculate readability and show it on the ui text area.

# CSS style sheet
    user need to put CSS style sheet name "UIStyle" inside the same directory of the program to apply
    modified color and font for GUI.
          
# How to use program
    [There is 2 ways to use this program, First one is by running program normally.]
![Program](https://s3-ap-southeast-1.amazonaws.com/img-in-th/94a2c332350a4997379748d86561ae4c.png)    
--This ui using css style sheet to edit color and font.--

    - In the top field you can either type URL,File directory or you can browse the file by clicking "Browse".
    - When you click calculate the program will calculate Flesch Readabilty and show it in the box below 
      as show in the picture.
    - You can click clear to clear all calculated result text and file name box text.
    - on the top left "Help" there is "About" menu that will show author name and version.
    
    [Additionally user can run by command line in terminal.]
      First go to your program directory, Then type this
          
              java Readability -link your/file/path
           
      the -link is the command to execute the application and follow by your file path or URL.

# Requirement JAR to run GUI
    Flesch Index Readability is using UI created by JAVAFX so user will need the JAVAFX Jar to run 
    this application with JAVAFX UI.
    Download link : https://gluonhq.com/products/javafx/
    **JAVAFX Product Depend on your OS.**
          
# UML Diagram
<a href="https://www.mx7.com/view2/C7t6ctx6cTa6wI50" target="_blank"><img border="0" src="https://www.mx7.com/i/142/VGjPMc.jpeg" /></a>

UML Diagram created by LucidChart.

