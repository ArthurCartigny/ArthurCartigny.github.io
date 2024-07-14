#include <IRremote.h>
#define R 9
#define G 6
#define B 10
#define PIN_RECEPTEUR 2 

int red=155, green=155, blue=155 ;
long temps ;
boolean police;
boolean arcenciel;

IRrecv recepteur(PIN_RECEPTEUR) ;
decode_results data ;

void setup() {
  pinMode(R, OUTPUT) ;
  pinMode(G, OUTPUT) ;
  pinMode(B, OUTPUT) ;

  randomSeed(analogRead(0));

  Serial.begin(9600) ;
  pinMode(PIN_RECEPTEUR, INPUT) ;
  recepteur.enableIRIn();
  recepteur.blink13(true);
  police = false;
  arcenciel = false;
}

void loop() {
  Debut:
  if (recepteur.decode(&data)) {
     resetanim();
    switch (data.value) {
      
      case 0xFFF807 :
      Serial.println("OFF") ;
      red = 0 ; 
      green = 0 ; 
      blue = 0 ;
      break;
      
      case 0xFFB04F :
      Serial.println("ON") ;
      red = 255 ; 
      green = 255 ; 
      blue = 255 ;
      break;
      
      case 0xFF906F :
      Serial.println("lightUP") ;
      red = red+10 ; 
      green = green+10 ; 
      blue = blue+10 ;
      break;

      case 0xFFB847 :
      Serial.println("LightDown") ;
      red = red-10 ; 
      green = green-10 ; 
      blue = blue-10 ;
      break;

      case 0xFF9867 :
      Serial.println("Red") ;
      red = 255 ; 
      green = 0 ; 
      blue = 0;
      break;

      case 0xFFD827 :
      Serial.println("Green") ;
      red = 0 ; 
      green = 255 ; 
      blue = 21 ;
      break;

      case 0xFF8877 :
      Serial.println("Blue") ;
      red = 0 ; 
      green = 0 ; 
      blue = 255 ;
      break;

      case 0xFFA857 :
      Serial.println("White") ;
      red = 255 ; 
      green = 255 ; 
      blue = 255 ;
      break;

      case 0xFFE817 :
      Serial.println("ORANGE FLUO") ;
      red = 255 ; 
      green = 85 ; 
      blue = 0 ;
      break;

      case 0xFF48B7 :
      Serial.println("VERT PASTEL") ;
      red = 0; 
      green = 255 ; 
      blue = 50 ;
      break;

      case 0xFF6897 :
      Serial.println("BLEU OCEAN") ;
      red = 0 ; 
      green = 43 ; 
      blue = 255 ;
      break;

      case 0xFFB24D :
      Serial.println("Flash") ;

      red = random(256); 
      green = random(256); 
      blue = random(256);     
      break;
      
      case 0xFF02FD :
      Serial.println("ORANGE") ;
      red = 255 ; 
      green = 149 ; 
      blue = 0 ;
      break;

      case 0xFF32CD :
      Serial.println("BLEU PASTEL") ;
      red = 0 ; 
      green = 255 ; 
      blue = 213 ;
      break;

      case 0xFF20DF :
      Serial.println("MAUVE") ;
      red = 128 ; 
      green = 0 ; 
      blue = 255 ;
      break;

      case 0xFF00FF :
      Serial.println("STROBE") ;
      police = true;
      temps = millis();  
      break;

      case 0xFF50AF :
      Serial.println("ORANGE PASTEL") ;
      red = 255 ; 
      green = 191 ; 
      blue = 0 ;
      break;

      case 0xFF7887 :
      Serial.println("CYAN") ;
      red = 0 ; 
      green = 170 ; 
      blue = 255 ;
      break;

      case 0xFF708F :
      Serial.println("ROSE FONCE") ;
      red = 255 ; 
      green = 0 ; 
      blue = 149 ;
      break;
      
      case 0xFF58A7 :
      Serial.println("FADE") ;
      arcenciel = true;
      temps = millis();
      
      break;

      case 0xFF38C7 :
      Serial.println("JAUNE") ;
      red = 255 ; 
      green = 255 ; 
      blue = 0 ;
      break;

      case 0xFF28D7 :
      Serial.println("BLEU 1AZUR") ;
      red = 0 ; 
      green = 85 ; 
      blue = 255 ;
      break;

      case 0xFFF00F :
      Serial.println("ROSE") ;
      red = 255 ; 
      green = 0 ; 
      blue = 203 ;
      break;

      case 0xFF30CF :
      Serial.println("SMOOTH") ;
    
      break;


      
      default: 
      break;  
    }
    setColor(red, green, blue) ;
    delay(500);
    recepteur.resume();
  }

  if (police == true)
  {
    animation1();
  }
  if(arcenciel == true)
  {
    animation2();
  }
  
  delay(1);
}

void animation1()
{

  if ( millis() - temps < 500)
  {
    setColor(255, 0, 0);
      Serial.println("Rouge") ;
  }else
  {
    setColor(0, 0, 255);
      Serial.println("bleu") ;
  }
  if ( millis() - temps > 1000)
  {
    temps = millis();
  }    
}

void animation2()
{
   if ( millis() - temps < 1000)
  {
    setColor(255, 0, 0);
      Serial.println("Rouge") ;
  } else 
  if ( millis() - temps < 2000)
  {
    setColor(245, 149, 0);
      Serial.println("orange") ;
  } else
  if ( millis() - temps < 3000)
  {
      setColor(255, 255, 0);
      Serial.println("Jaune") ;
  }else
  if ( millis() - temps < 4000)
  {
      setColor(0, 255, 0);
      Serial.println("Vert") ;
  }else
  if ( millis() - temps < 5000)
  {
      setColor(0, 0, 255);
      Serial.println("Bleu") ;
  }else
  if ( millis() - temps < 6000)
  {
      setColor(128, 0, 255);
      Serial.println("Mauve") ;
  }
  else
  if ( millis() - temps < 7000)
  {
    temps = millis();
  }    
}

void resetanim(){
  police = false;
  arcenciel = false;
  
}
void setColor(int red, int green, int blue) {
  analogWrite(R, red) ;
  analogWrite(G, green) ;
  analogWrite(B, blue) ;
}

