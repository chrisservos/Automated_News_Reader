package efarmoges_texnitis;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.sun.speech.freetts.Voice;  
import com.sun.speech.freetts.VoiceManager;
 import java.lang.String;
import java.util.Arrays;
import java.util.Scanner;



    
    public class  Automated_news_reader {
 
 private static final String VOICENAME_kevin= "kevin16";
 private String text; // string to speech
 
 public  Automated_news_reader(String text) {
  this.text = text;
 } 

 
 public void speak() {
  Voice voice;
  VoiceManager voiceManager = VoiceManager.getInstance();
  voice = voiceManager.getVoice(VOICENAME_kevin);
  voice.allocate();
  voice.speak(text);
 }
    
 
    public static void main(String[] args) throws FileNotFoundException, IOException {
         int numOfSentences=6;
      
            SimpleSummariser s1=new SimpleSummariser();         //Arxikopoiisi metavlitwn
            SimpleSummariser simple = new SimpleSummariser(); 
            
              StringBuilder stringBuilder = new StringBuilder();
                String line = null;
        
        System.out.println("Which article to read?\n1.)Ancient undersea landslide discovered in Australia"
                + "\n2.)Six Afghan ICRC workers 'killed by Islamic State'"                  //menu epilogwn eidisewn
                + "\n3.)Syria rejects Amnesty report on hangings at Saydnaya prison"
                + "\n4.)Uber hires 'flying car engineer' from Nasa ");
        
        Scanner inte = new Scanner(System.in);
        int numOfNews = inte.nextInt();                 //scan epilogis
      //sti sinexeia anaparagetai i eidisi pou epilegei o xristis afou fortothei apo to analogo arxeio
        //kai afou ginei to summarization
           //sto telos kathe epilogis kanoume extract kapoia keywords gia to keimeno mas
                                                  
        if (numOfNews==1){  
            
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Chris\\Desktop\\Automated_News_Reader\\articles\\Ancient undersea landslide discovered in Australia.txt"));      
               while((line = in.readLine()) != null){
                   stringBuilder.append("\n").append(line); }
                      String finalString = stringBuilder.toString();
                            in.close();                                                                                                                                 
                                 String summary=simple.summarise(finalString,numOfSentences); 
                                     System.out.println(summary);
                                      Automated_news_reader freeTTS = new  Automated_news_reader(summary);
                                        freeTTS.speak();
                                            String keywords=Arrays.toString(s1.getKeywords(finalString, numOfSentences));  
                                            System.out.println("Reading ended,exiting");

            
        }else
            if (numOfNews==2){
                    BufferedReader in2 = new BufferedReader(new FileReader("C:\\Users\\Chris\\Desktop\\Automated_News_Reader\\articles\\Six Afghan ICRC workers 'killed by Islamic State'.txt"));                
                        while((line = in2.readLine()) != null){
                           stringBuilder.append("\n").append(line); }
                                 String finalString2 = stringBuilder.toString();                              
                                        in2.close();
                                           String summary=simple.summarise(finalString2,numOfSentences); 
                                               System.out.println(summary);
                                                    Automated_news_reader freeTTS = new  Automated_news_reader(summary);
                                                       freeTTS.speak();
                                                        String keywords=Arrays.toString(s1.getKeywords(finalString2, numOfSentences));  
                                                        System.out.println("Reading ended,exiting");
            }else
                if(numOfNews==3){
                        BufferedReader in3 = new BufferedReader(new FileReader("C:\\Users\\Chris\\Desktop\\Automated_News_Reader\\articles\\Syria rejects Amnesty report on hangings at Saydnaya prison.txt"));                                        
                                    while((line = in3.readLine()) != null){
                                        stringBuilder.append("\n").append(line); }
                                            String finalString3= stringBuilder.toString();
                                                        in3.close();
                                                            String summary=simple.summarise(finalString3,numOfSentences); 
                                                                    System.out.println(summary);
                                                                       Automated_news_reader freeTTS = new  Automated_news_reader(summary);
                                                                       freeTTS.speak();
                                                                        String keywords=Arrays.toString(s1.getKeywords(finalString3, numOfSentences));  
                                                                        System.out.println("Reading ended,exiting");
                                                                       
                }else
                    if(numOfNews==4){
                            BufferedReader in4 = new BufferedReader(new FileReader("C:\\Users\\Chris\\Desktop\\Automated_News_Reader\\articles\\Uber hires 'flying car engineer' from Nasa.txt"));
                                while((line = in4.readLine()) != null){
                                        stringBuilder.append("\n").append(line); }
                                            String finalString4= stringBuilder.toString();
                                                        in4.close();
                                                            String summary=simple.summarise(finalString4,numOfSentences); 
                                                                    System.out.println(summary);
                                                                       Automated_news_reader freeTTS = new  Automated_news_reader(summary);
                                                                       freeTTS.speak();
                                                                          String keywords=Arrays.toString(s1.getKeywords(finalString4, numOfSentences));  
                                                                          System.out.println("Reading ended,exiting");
                    }else {
                        System.out.println("Invalid number");
                    }
                        
    }   
}