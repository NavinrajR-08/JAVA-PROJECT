import java.io.*;import java.nio.file.*;import java.util.*;
public class Bench{public static void main(String[] a)throws Exception{
 List<String> us=new ArrayList<>();for(String l:Files.readAllLines(Paths.get("urls.txt")))us.add(l.split("\\|",2)[1]);
 PrintStream o=System.out;System.setOut(new PrintStream(OutputStream.nullOutputStream()));
 for(int i=0;i<20000;i++)for(String u:us)PhishingDetector.analyzeUrl(u);
 int R=100000;long t=System.nanoTime();for(int i=0;i<R;i++)for(String u:us)PhishingDetector.analyzeUrl(u);
 long e=System.nanoTime()-t;System.setOut(o);System.out.printf("avg %.2f microseconds per URL over %d analyses%n",e/1000.0/(R*us.size()),R*us.size());}}
