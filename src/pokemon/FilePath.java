
package pokemon;


public class FilePath {
    
    public String getFilePath() {
        String defaultPath = FilePath.class.getProtectionDomain().getCodeSource().getLocation().toString();
        int spaceIndex = defaultPath.indexOf("%20");
        while(spaceIndex > 0) {
            String first = defaultPath.substring(0,spaceIndex);
            String second = defaultPath.substring(spaceIndex + 3);
            defaultPath = first + " " + second;
            spaceIndex = defaultPath.indexOf("%20");
        }
        return defaultPath.substring(5, defaultPath.length() - 14);
    }
}
