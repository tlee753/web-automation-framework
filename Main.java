import org.apache.commons.cli.*;
import org.testng.TestNG;
import util.WAFTagParser;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws Exception {

        Options options = new Options();

        Option input1 = new Option("b", "browser", true, "browser name");
        Option input2 = new Option("w", "width", true, "browser width in pixels");
        Option input3 = new Option("h", "height", true, "browser height in pixels");
        Option input4 = new Option("p", "port", true, "browser port number");
        Option input5 = new Option("u", "url", true,"url of chassis to test");
        Option input6 = new Option("t", "tag", true,"");
//        Option input7 = new Option("s", "smoke", true,"");
//        Option input8 = new Option("e", "example", true,"");

        options.addOption(input1);
        options.addOption(input2);
        options.addOption(input3);
        options.addOption(input4);
        options.addOption(input5);
        options.addOption(input6);
//        options.addOption(input7);
//        options.addOption(input8);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }

        String browser = cmd.getOptionValue("browser") != null ? cmd.getOptionValue("browser") : "chrome";
        int width = cmd.getOptionValue("width") != null ? Integer.parseInt(cmd.getOptionValue("width")) : 1024;
        int height = cmd.getOptionValue("height") != null ? Integer.parseInt(cmd.getOptionValue("height")) : 768;
        int port = cmd.getOptionValue("port") != null ? Integer.parseInt(cmd.getOptionValue("port")) : 0;
        String url = cmd.getOptionValue("url") != null ? "http://" + cmd.getOptionValue("url") + "/" : "";
        String tests;
        if (cmd.getOptionValue("tag") != null) {
            WAFTagParser wafTagParser = new WAFTagParser(cmd.getOptionValue("tag"));
            tests = wafTagParser.getOutput();
        } else {
            WAFTagParser wafTagParser = new WAFTagParser("Example");
            tests = wafTagParser.getOutput();
        }

        PrintWriter writer = new PrintWriter("build.xml", "UTF-8");
        writer.println(
                "<!DOCTYPE suite SYSTEM \"http://testng.org/testng-1.0.dtd\">\n" +
                "<suite name=\"Suite1\" verbose=\"1\" parallel=\"classes\">\n" +
                "    <listeners>\n" +
                "        <listener class-name=\"org.uncommons.reportng.HTMLReporter\"/>\n" +
                "        <listener class-name=\"org.testng.reporters.XMLReporter\"/>\n" +
                "    </listeners>\n" +
                "    <test name=\"Example Tests\" preserve-order=\"true\">\n" +
                "        <parameter name=\"browser\" value=\"" + browser + "\"/>\n" +
                "        <parameter name=\"width\" value=\"" + Integer.toString(width) + "\"/>\n" +
                "        <parameter name=\"height\" value=\"" + Integer.toString(height) + "\"/>\n" +
                "        <parameter name=\"port\" value=\"" + Integer.toString(port) + "\"/>\n" +
                "        <parameter name=\"url\" value=\"" + url + "\"/>\n" +
                "        <classes>\n" +
                             tests +
                "        </classes>\n" +
                "    </test>\n" +
                "</suite>"
        );
        writer.close();

        TestNG testNG = new TestNG();
        List<String> suites = new ArrayList<>();
        suites.add("build.xml");
        testNG.setTestSuites(suites);
        testNG.setUseDefaultListeners(false);
        testNG.run();
    }
}
