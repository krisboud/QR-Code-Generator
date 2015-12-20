# QRGenerator

## Running on Command Line
    java -Dfile.encoding=UTF-8 -jar ./target/QRGenerator.jar {options} {URL}
    
### Options
    -size {Integer}
        (optional) The Size of the QR Code.  
        If -size is omitted, 250 is used.
    
    -fore-color {Hex Code}
        (optional) Foreground color for QR code image.  
        If -fore-color is omitted, 000000 is used
        
    -back-color {Hex Code}
        (optional) Background color for QR code image.
        If -back-color is omitted, FFFFFF is used
        
## Compile
Note: Needs Java 1.8+ and Maven to compile

    mvn clean validate compile package
    
## Author
* Kris Boudreau (krisboud)


