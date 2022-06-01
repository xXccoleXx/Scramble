# Scramble
Java program that visually encrypts a picture with symmetric encryption    

Utilizes the Java Picture Class found here https://introcs.cs.princeton.edu/java/stdlib/Picture.java.html    

# Instructions:
(get into the appropriate directory first, Duh!)    
Run the Scramble program through the command line as you would any java file.    
The first argument is the path of the selected picture file and the second arguement is the desired name/path of the output picture file.    
To undo the encryption, simply run the encypted photo through the program again. (symmetric encryption!)    

# Warnings:
Must input jpg, jpeg, or png file.    
Only output with png file.    
Do not resize or crop an encrypted photo (ruins symmetric encryption).    
Do not reformat encypted photo to another format such as jpeg/jpg (compression alters the pixels and ruins encryption).    

# Example (Windows Command Line):
javac Scramble.java     
java Scramble inputFileName.jpg outputFileName.png
