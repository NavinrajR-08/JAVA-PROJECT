import java.io.*;import java.nio.file.*;import java.util.*;import java.util.regex.*;
public class Harness{public static void main(String[] a)throws Exception{
 PrintStream orig=System.out;StringBuilder csv=new StringBuilder("label,url,score,verdict,flags\n");
 StringBuilder full=new StringBuilder();
 for(String line:Files.readAllLines(Paths.get("urls.txt"))){String[] p=line.split("\\|",2);
  ByteArrayOutputStream b=new ByteArrayOutputStream();System.setOut(new PrintStream(b,true,"UTF-8"));
  PhishingDetector.analyzeUrl(p[1]);System.setOut(orig);String o=b.toString("UTF-8");full.append(o);
  Matcher m=Pattern.compile("Suspicion Score: (\\d+)").matcher(o);m.find();
  String v=o.contains("Likely PHISHING")?"PHISHING":o.contains("Suspicious -")?"SUSPICIOUS":"LEGITIMATE";
  int fl=0;for(String l:o.split("\n"))if(l.startsWith("  - "))fl++;
  csv.append(p[0]+","+p[1]+","+m.group(1)+","+v+","+fl+"\n");}
 Files.writeString(Paths.get("results.csv"),csv.toString());Files.writeString(Paths.get("full.txt"),full.toString());}}
