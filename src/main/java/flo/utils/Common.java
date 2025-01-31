package flo.utils;
import org.openqa.selenium.WebElement;

public class Common {

    public static String getElementLocator(WebElement element) {
        // Get the string representation of the WebElement
        String elementString = element.toString();

        // Remove "Proxy element for: DefaultElementLocator" if it exists
        elementString = elementString.replace("Proxy element for: DefaultElementLocator ", "");
        // Check if the string ends with "]" and remove it if true
        if (elementString.endsWith("]")) {
            elementString = elementString.substring(0, elementString.length() - 1); // Remove the last character
        }
        // Find the index of "->" and extract everything after it
        int index = elementString.indexOf("->");
        if (index != -1) {
            return elementString.substring(index + 3).trim(); // +3 to skip the "->" part
        }
        
        // If "->" is not found, return the full string (or handle as needed)
        return elementString;
    }
}
