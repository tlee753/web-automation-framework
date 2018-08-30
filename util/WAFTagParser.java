package util;


public class WAFTagParser {

    private String output;

    public WAFTagParser(String tags) {

        StringBuilder stringBuilder = new StringBuilder();

        String[] tagsArray = tags.trim().split(",");
        for (String tag: tagsArray) {
            tag = tag.replace(".", "_");
            stringBuilder.append("            <class name=\"Test" + tag + "\"/>\n");
        }
        output = stringBuilder.toString();
    }

    public String getOutput() {
        return output;
    }
}
